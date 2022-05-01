package com.lianzhu.hanlphub.controller;

import com.lianzhu.hanlphub.common.BaseParam;
import com.lianzhu.hanlphub.common.BaseResult;
import com.lianzhu.hanlphub.service.HanlpService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanghc
 * @since 2020-07-28
 **/
@RestController
@RequestMapping(value = "/common")
public class HanlpController {

    private final HanlpService hanlpService;

    public HanlpController(HanlpService hanlpService) {
        this.hanlpService = hanlpService;
    }

    @PostMapping("/api")
    public BaseResult<Object> hanlpCommonApi(@RequestBody @Validated BaseParam baseParam) {
        return hanlpService.hanlpCommonApi(baseParam);
    }

}
