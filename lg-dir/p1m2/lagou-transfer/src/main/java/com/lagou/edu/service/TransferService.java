package com.lagou.edu.service;

/**
 * @author 应癫
 */
public interface TransferService {

    /**
     * 装藏操作
     *
     * @param fromCardNo String
     * @param toCardNo   String
     * @param money      int
     * @throws Exception Exception
     */
    void transfer(String fromCardNo, String toCardNo, int money) throws Exception;

}
