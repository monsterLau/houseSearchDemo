package com.lau.houseSearchDemo;

import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/*
* 测试分页
* */
public class MvcTest {

    @Autowired
    WebApplicationContext context;

    MockMvc mockMvc;

    public void initMoKcMnc() {
        mockMvc = MockMvcBuilders.standaloneSetup(context).build();
    }

    @Test
    public void testPage() throws Exception {
       MvcResult result= mockMvc.perform(MockMvcRequestBuilders.get("/allHouse").param("pn", "1")).andReturn();

        MockHttpServletRequest request=result.getRequest();
        PageInfo pi= (PageInfo) request.getAttribute("pageInfo");
        System.out.println("dangqianyema:"+pi.getPageNum());
    }
}
