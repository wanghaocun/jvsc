package com.lianzhu.hanlphub.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lianzhu.hanlphub.common.BaseParam;
import com.lianzhu.hanlphub.common.BaseResult;
import com.lianzhu.hanlphub.common.HanlpCommonApi;
import com.lianzhu.hanlphub.common.NamedThreadFactory;
import com.lianzhu.hanlphub.service.HanlpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author wanghc
 * @since 2020-07-28
 **/
@Service
public class HanlpServiceImpl implements HanlpService {

    private final StringRedisTemplate redisTemplate;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public HanlpServiceImpl(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * API 调用次数统计
     */
    public static final String DAY_CALLS_AMOUNT = "hanlp_api_statistics:day_calls_amount:";
    public static final String MONTH_CALLS_AMOUNT = "hanlp_api_statistics:month_calls_amount:";
    public static final String YEAR_CALLS_AMOUNT = "hanlp_api_statistics:year_calls_amount:";
    public static final String TOTAL_CALLS_AMOUNT = "hanlp_api_statistics:total_calls_amount";

    /**
     * Hanlp 常用 API 方法名称
     */
    private static final List<String> HANLP_COMMON_API = new ArrayList<>();

    /**
     * 线程命名工厂
     */
    public static final ThreadFactory NAMED_THREAD_FACTORY = new NamedThreadFactory("hanlp");

    /**
     * 用于执行转发数据的线程池
     */
    private static final ExecutorService HANLP_THREAD_POOL = new ThreadPoolExecutor(
            10,
            20,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(102400),
            NAMED_THREAD_FACTORY,
            new ThreadPoolExecutor.DiscardOldestPolicy()
    );

    // 初始化 HANLP_COMMON_API
    static {
        Class<?> aClass = HanlpCommonApi.class;
        Arrays.stream(aClass.getDeclaredMethods()).forEach(method -> HANLP_COMMON_API.add(method.getName()));
    }

    @Override
    public BaseResult<Object> hanlpCommonApi(BaseParam baseParam) {
        try {
            String methodName = baseParam.getMethod();
            boolean nonMethod = !HANLP_COMMON_API.contains(methodName);
            if (nonMethod) {
                return BaseResult.error("method not exist: " + methodName);
            }

            // 提交任务到线程池
            Future<Object> future = HANLP_THREAD_POOL.submit(new CommonApiThread(baseParam));

            return BaseResult.success(future.get());
        } catch (Exception e) {
            logger.error("hanlp common api failed: {}", e.getMessage(), e);
            return BaseResult.error(e.getMessage());
        }
    }

    /**
     * 记录 API 调用次数
     */
    private void recordApiCallsAmount() {
        String year = String.valueOf(LocalDate.now().getYear());
        String yearMonth = year + "-" + LocalDate.now().getMonthValue();
        redisTemplate.opsForValue().increment(TOTAL_CALLS_AMOUNT);
        redisTemplate.opsForValue().increment(YEAR_CALLS_AMOUNT + year);
        redisTemplate.opsForValue().increment(MONTH_CALLS_AMOUNT + yearMonth);
        redisTemplate.opsForValue().increment(DAY_CALLS_AMOUNT + LocalDate.now());
    }

    /**
     * 获取 key 的值，若没有则赋值 "0"，以预防接口未被调用时，Redis 缺少 key
     *
     * @param key String
     * @return String
     */
    private String getKey(String key) {
        Boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey != null && hasKey) {
            return redisTemplate.opsForValue().get(key);
        }

        String zero = "0";
        redisTemplate.opsForValue().set(key, zero);
        return zero;
    }

    /**
     * 设置 redis 昨日统计数据的有效时间 (1月)
     */
    @Scheduled(cron = "0 0 0 * * ?")
    private void dayStatisticsCrontab() {
        // avoid missing redis keys
        String year = String.valueOf(LocalDate.now().getYear());
        String yearMonth = year + "-" + LocalDate.now().getMonthValue();
        String yesterday = DAY_CALLS_AMOUNT + LocalDate.now().plusDays(-1);
        String totalCount = getKey(TOTAL_CALLS_AMOUNT);
        String yearCount = getKey(YEAR_CALLS_AMOUNT + year);
        String monthCount = getKey(MONTH_CALLS_AMOUNT + yearMonth);
        String yesterdayCount = getKey(yesterday);

        // yesterday statistics ttl
        redisTemplate.opsForValue().set(yesterday, yesterdayCount, 30, TimeUnit.DAYS);
        logger.info("[{}] set day statistics ttl (30 days) done", LocalDate.now().plusDays(-1));

        // show all amount
        logger.info("[{}] api calls amount by the day: {}", LocalDate.now(), yesterdayCount);
        logger.info("[{}] api calls amount by the month: {}", yearMonth, monthCount);
        logger.info("[{}] api calls amount by the year: {}", year, yearCount);
        logger.info("total api calls amount by now: {}", totalCount);
    }

    /**
     * 设置 redis 上月统计数据的有效时间 (1年)
     */
    @Scheduled(cron = "1 1 1 1 * ?")
    private void monthStatisticsCrontab() {
        LocalDate preDay = LocalDate.now().plusDays(-1);
        String lastMonth = preDay.getYear() + "-" + preDay.getMonthValue();
        String monthCount = getKey(MONTH_CALLS_AMOUNT + lastMonth);
        // last month statistics ttl
        redisTemplate.opsForValue().set(MONTH_CALLS_AMOUNT + lastMonth, monthCount, 365, TimeUnit.DAYS);
        logger.info("[{}] month statistics ttl (365 days) done", lastMonth);
    }

    /**
     * 处理接口请求的线程
     */
    private class CommonApiThread implements Callable<Object> {

        private final BaseParam baseParam;

        private CommonApiThread(BaseParam baseParam) {
            this.baseParam = baseParam;
        }

        private final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public Object call() {
            try {
                logger.debug("base params: {}", objectMapper.writeValueAsString(baseParam));
                String methodName = baseParam.getMethod();
                String content = baseParam.getContent();
                Integer size = baseParam.getSize();
                Integer type = baseParam.getType();
                String keyword = baseParam.getKeyword();

                // 构造可变参数
                Object[] args = new Object[]{size, type, keyword};
                logger.debug("args: {}", Arrays.toString(args));
                Class<?> clazz = HanlpCommonApi.class;
                Object instance = clazz.newInstance();
                Method method = clazz.getMethod(methodName, String.class, Object[].class);
                Object result = method.invoke(instance, content, args);
                logger.debug("hanlp ret: {}", objectMapper.writeValueAsString(result));

                // 增加 API 调用次数
                recordApiCallsAmount();

                return result;
            } catch (Exception e) {
                logger.error("hanlp common api failed: {}", e.getMessage(), e);
                return null;
            }
        }
    }

}
