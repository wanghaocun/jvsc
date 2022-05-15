package com.example.xxljobdemo;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author wanghaocun
 **/
@Component
@SuppressWarnings("unused")
public class XxlJobDemo {

    private final Logger logger = LoggerFactory.getLogger(XxlJobDemo.class);

    @XxlJob(value = "testXxlJobHandler", init = "init", destroy = "destroy")
    public void testXxlJobHandler() {
        logger.info("test xxl-job doing...");
        XxlJobHelper.log("test xxl-job doing...");
    }

    public void init() {
        logger.info("test xxl-job init...");
        XxlJobHelper.log("test xxl-job init...");
    }

    public void destroy() {
        logger.info("test xxl-job destroy...");
        XxlJobHelper.log("test xxl-job destroy...");
    }

}
