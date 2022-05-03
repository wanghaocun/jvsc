package com.lagou.edu.dao;

import com.lagou.edu.pojo.Account;

/**
 * @author 应癫
 */
public interface AccountDao {

    /**
     * 根据卡号查询账户信息
     *
     * @param cardNo String
     * @return Account
     * @throws Exception Exception
     */
    Account queryAccountByCardNo(String cardNo) throws Exception;

    /**
     * 根据卡号更新账户信息
     *
     * @param account Account
     * @throws Exception Exception
     */
    void updateAccountByCardNo(Account account) throws Exception;

}
