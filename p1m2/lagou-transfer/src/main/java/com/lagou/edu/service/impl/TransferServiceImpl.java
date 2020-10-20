package com.lagou.edu.service.impl;

import com.lagou.edu.annotation.Autowired;
import com.lagou.edu.annotation.Service;
import com.lagou.edu.annotation.Transactional;
import com.lagou.edu.dao.AccountDao;
import com.lagou.edu.pojo.Account;
import com.lagou.edu.service.TransferService;

/**
 * @author 应癫
 */
@Transactional
@Service("transferService")
@SuppressWarnings("all")
public class TransferServiceImpl implements TransferService {

    /**
     * 通过自定义注解 autowired 实现自动装配
     */
    @Autowired("accountDao")
    private AccountDao accountDao;

    /**
     * 在 BeanFactory 初始化 bean 时，通过反射调用 set 方法传值
     *
     * @param accountDao AccountDao
     */
    @SuppressWarnings("unused")
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void transfer(String fromCardNo, String toCardNo, int money) throws Exception {
        Account from = accountDao.queryAccountByCardNo(fromCardNo);
        Account to = accountDao.queryAccountByCardNo(toCardNo);

        from.setMoney(from.getMoney() - money);
        to.setMoney(to.getMoney() + money);

        accountDao.updateAccountByCardNo(to);
//        int c = 1 / 0;
        accountDao.updateAccountByCardNo(from);
    }

}
