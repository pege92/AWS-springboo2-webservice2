package com.sungmin.practice1.springboot.web;

import com.sungmin.practice1.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) //테스트를 진행할때 Junit에 내장된 실행자 외에 다른 실행자를 실행 스프링부트테스트와 Junit 사이에 연결자

//스프링테스트 중 Web에 집중한 어노테이션인데 어노테이션이 멀까?
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        }
)

public class HelloControllerTest {
    @Autowired   //스프링이 관리하는 Bean을 주입 받는다
    private MockMvc mvc;  //웹API를 테스트 할때 사용 스프링 MVC 의 시작점


    @WithMockUser(roles="USER")
    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello")) //MockMVC를 통해 /hello주소로 HTTP GET요청
                .andExpect(status().isOk()) //HTTP header의 status를 검증 OK 즉 200인지 아닌지
                .andExpect(content().string(hello));

    }

    @WithMockUser(roles="USER")
    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                    get("/hello/dto")
                .param("name",name)
                .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));

    }
}
