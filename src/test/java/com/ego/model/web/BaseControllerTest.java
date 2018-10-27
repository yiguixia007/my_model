package com.ego.model.web;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author ego
 * @since 2018-10-27 16:31
 * 注解 @RunWith 是一个运行器
 * 注解 @RunWith(SpringRunner.class) 让测试运行于Spring测试环境
 * 注解 @RunWith:指定使用的单元测试执行类 SpringRunner.class 继承自 SpringJUnit4ClassRunner.class
 * 使用了Spring的SpringJUnit4ClassRunner，以便在测试开始的时候自动创建Spring的应用上下文。
 *
 * 注解 @AutoConfigureMockMvc，该注解表示启动测试的时候自动注入 MockMvc ,而这个 MockMvc 有以下几个基本的方法:
 * perform:     执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理。
 * andExpect:   添加RequestMatcher验证规则，验证控制器执行完成后结果是否正确
 * andDo:       添加ResultHandler结果处理器，比如调试时打印结果到控制台
 * andReturn:   最后返回相应的MvcResult,然后进行自定义验证/进行下一步的异步处理
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public abstract class BaseControllerTest {

    @Autowired
    protected MockMvc mockMvc;
}
