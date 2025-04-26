package com.cao.caoaiagent.controller;

import com.cao.caoaiagent.common.BaseResponse;
import com.cao.caoaiagent.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
@Slf4j
public class HealthController {

    @GetMapping
    public BaseResponse<String> healthCheck() {
        log.info("ok");
        return ResultUtils.success("ok");
    }
}
