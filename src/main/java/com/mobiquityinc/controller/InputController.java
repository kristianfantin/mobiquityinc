package com.mobiquityinc.controller;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.Packer;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Api(tags = { "inputController" })
@RestController
@RequestMapping("/api")
public class InputController {

    @GetMapping
    public String getData(HttpServletRequest request, @RequestParam String filename) throws APIException, IOException {
        return Packer.pack(filename);
    }

}
