package com.lianzhu.hanlphub.service;

import com.lianzhu.hanlphub.common.BaseParam;
import com.lianzhu.hanlphub.common.BaseResult;

/**
 * @author wanghc
 * @since 2020-07-28
 **/
public interface HanlpService {

    /**
     * Hanlp 通用接口
     *
     * @param baseParam RequestParam
     * @return BaseResult
     */
    BaseResult<Object> hanlpCommonApi(BaseParam baseParam);

}
