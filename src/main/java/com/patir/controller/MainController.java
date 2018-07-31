package com.patir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author GenshenWang.nomico
 * @date 2018/3/7.
 */
@Controller
@RequestMapping(value = "/patir")
public class MainController {

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(){
        return "main";
    }

}
