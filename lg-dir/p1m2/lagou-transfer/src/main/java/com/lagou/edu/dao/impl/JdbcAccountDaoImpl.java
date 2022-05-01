package com.lagou.edu.dao.impl;

import com.lagou.edu.annotation.Autowired;
import com.lagou.edu.annotation.Repository;
import com.lagou.edu.dao.AccountDao;
import com.lagou.edu.pojo.Account;
import com.lagou.edu.util.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author 应癫
 */
@Repository("accountDao")
@SuppressWarnings("unused")
public class JdbcAccountDaoImpl implements AccountDao {

    /**
     * 通过自定义注解 autowired 实现自动装配
     */
    @Autowired("connectionUtils")
    private ConnectionUtils connectionUtils;

    /**
     * 在 BeanFactory 初始化 bean 时，通过反射调用 set 方法传值
     *
     * @param connectionUtils ConnectionUtils
     */
    @SuppressWarnings("unused")
    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    @Override
    public Account queryAccountByCardNo(String cardNo) throws Exception {
        // 从当前线程中获取绑定的connection连接
        Connection con = connectionUtils.getCurrentThreadConn();
        String sql = "select * from account where cardNo=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, cardNo);
        ResultSet resultSet = preparedStatement.executeQuery();

        Account account = new Account();
        while (resultSet.next()) {
            account.setCardNo(resultSet.getString("cardNo"));
            account.setName(resultSet.getString("name"));
            account.setMoney(resultSet.getInt("money"));
        }

        resultSet.close();
        preparedStatement.close();

        return account;
    }

    @Override
    public void updateAccountByCardNo(Account account) throws Exception {
        // 从当前线程中获取绑定的connection连接
        Connection con = connectionUtils.getCurrentThreadConn();
        String sql = "update account set money=? where cardNo=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, account.getMoney());
        preparedStatement.setString(2, account.getCardNo());
        int i = preparedStatement.executeUpdate();

        preparedStatement.close();

    }

}
