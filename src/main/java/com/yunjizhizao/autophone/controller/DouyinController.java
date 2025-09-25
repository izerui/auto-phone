package com.yunjizhizao.autophone.controller;

import com.volcengine.model.acep.DetailPodRes;
import com.yunjizhizao.autophone.service.DouyinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.models.annotations.OpenAPI31;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@OpenAPI31
public class DouyinController {

    @Autowired
    private DouyinService douyinService;

    @Operation(summary = "获取实例详情")
    @GetMapping("/pod-detail")
    public DetailPodRes podDetail(@RequestParam(defaultValue = "1965298718945054720") String productId,
                                  @RequestParam(defaultValue = "7547992861502544679") String podId) throws Exception {
        DetailPodRes detail = douyinService.getDetail(productId, podId);
        return detail;
    }
}
