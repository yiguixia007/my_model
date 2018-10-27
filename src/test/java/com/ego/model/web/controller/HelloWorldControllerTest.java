package com.ego.model.web.controller;

import com.ego.model.web.BaseControllerTest;
import com.ego.model.web.utility.MockUtility;
import org.junit.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author ego
 * @since 2018-10-27 16:49
 **/
public class HelloWorldControllerTest extends BaseControllerTest{

    @Test
    public void sayHelloTest() throws Exception {
        mockMvc.perform(MockUtility.populateGetBuilder("/helloWorld/sayHello"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("0"));
    }
}
