package org.liuep.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTest {

    @RequestMapping("/test")
    public String getPayment(){
        return "测试成功";
    }
}
