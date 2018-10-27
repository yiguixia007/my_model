package com.ego.model.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ego
 * @since 2018-10-27 16:07
 *
 * 注解 @RestController 是 @Controller 和 @ResponseBody 注解的结合
 * 注解 @Controller 表明这是一个控制器类
 * 在方法上加注解 @RequestMapping(value = "url", method = RequestMethod.POST)
 * 表示拦截以post请求的发送的url地址,如果不写method默认的话会拦截get和post请求
 * 配合 InternalResourceViewResolver 视图解析器即可以返回页面如：return main跳转到 main.jsp
 * 而不跳转页面,仅需返回JSON数据的话,可配合 @ResponseBody注解。
 **/
@RestController
@RequestMapping("/helloWorld")
public class HelloWorldController {

    /**
     * 注解 @GetMapping 是一个组合注解，
     * 是@RequestMapping(method = RequestMethod.GET)的缩写。
     * 该注解将HTTP Get 映射到 特定的处理方法上。
     *
     * 注解 @RequestMapping是一个用来处理请求地址映射的注解，可用于类或方法上。
     * 用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。
     * 常用属性
     * value    method
     * value:   指定请求的实际地址，指定的地址可以是URI Template 模式
     * method:  指定请求的method类型， GET、POST、PUT、DELETE等；
     */
    @GetMapping("/sayHello")
    public String sayHello(){
        return "Hello World";
    }
}
