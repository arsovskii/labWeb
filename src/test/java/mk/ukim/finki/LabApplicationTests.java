package mk.ukim.finki;

import mk.ukim.finki.wp.LabApplication;
import mk.ukim.finki.wp.web.controller.CourseController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@ActiveProfiles("test")
@SpringBootTest(classes= LabApplication.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration
class LabApplicationTests {

    MockMvc mockMvc;

    @BeforeEach
    public void setup(WebApplicationContext wac){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .dispatchOptions(true)
                .apply(springSecurity())
                .build();
    }
    @Test
    void contextLoads() {
    }

    @Test
    public void testGetCoursesList() throws Exception {
        MockHttpServletRequestBuilder courseRequest = MockMvcRequestBuilders.get("/courses");
        this.mockMvc.perform(courseRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("courses"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("teachers"))
                .andExpect(MockMvcResultMatchers.view().name("listCourses"));

    }
    //dva integraciski testovi, za uspesen login dali pravi redirect
    // ne uspesen login, redirect kon sebe si
    @Test
    public void testSuccessfulLogin() throws Exception{

        MockHttpServletRequestBuilder loginRequest = MockMvcRequestBuilders.post("/login")
                .param("username","admin")
                .param("password","admin");
        this.mockMvc.perform(loginRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/courses"));

    }

    @Test
    public void testUnsuccessfulLogin() throws Exception{

        MockHttpServletRequestBuilder loginRequest = MockMvcRequestBuilders.post("/login")
                .param("username","fake")
                .param("password","fake");
        this.mockMvc.perform(loginRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));


    }
}
