package com.yunjizhizao.autophone.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class CommandRequestDto {

    @Schema(title = "业务ID", example = "\"1965298718945054720\"")
    private String productId;

    @Schema(title = "实例ID集合", example = "[\"7547992861502544679\"]")
    private List<String> podIds;

    @Schema(title = "命令", example = "am start -n com.ss.android.ugc.aweme/.splash.SplashActivity")
    private String command;
}
