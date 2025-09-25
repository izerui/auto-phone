package com.yunjizhizao.autophone.controller;

import com.volcengine.model.acep.DetailPodRes;
import com.volcengine.model.acep.RunSyncCommandRes;
import com.volcengine.model.acep.ScreenShotRes;
import com.yunjizhizao.autophone.controller.dto.CommandRequestDto;
import com.yunjizhizao.autophone.service.PhoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.models.annotations.OpenAPI31;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@OpenAPI31
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @Operation(summary = "获取实例详情")
    @GetMapping("/pod-detail")
    public DetailPodRes podDetail(@Parameter(name = "productId", example = "\"1965298718945054720\"", description = "业务ID") String productId,
                                  @Parameter(name = "podId", example = "\"7547992861502544679\"") String podId) throws Exception {
        DetailPodRes detail = phoneService.getDetail(productId, podId);
        return detail;
    }

    @Operation(summary = "获取实例截图")
    @GetMapping("/pod-screen-shot")
    public ScreenShotRes getScreenShot(@Parameter(name = "productId", example = "\"1965298718945054720\"", description = "业务ID") String productId,
                                       @Parameter(name = "podId", example = "\"7547992861502544679\"") String podId) throws Exception {
        ScreenShotRes shotRes = phoneService.getScreenShot(productId, podId);
        return shotRes;
    }

    @Operation(summary = "执行单个命令")
    @PostMapping("/pod-run-command")
    public RunSyncCommandRes runSyncCommand(@RequestBody CommandRequestDto body) throws Exception {
        RunSyncCommandRes res = phoneService.runSyncCommand(body.getProductId(), body.getPodIds(), body.getCommand());
        return res;
    }


}
