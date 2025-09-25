package com.yunjizhizao.autophone.controller;

import com.yunjizhizao.autophone.service.PhoneService;
import io.swagger.v3.oas.models.annotations.OpenAPI31;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@OpenAPI31
public class DouyinController {

    @Autowired
    private PhoneService phoneService;

    public String autoCtl01() {
        return null;
    }
}
