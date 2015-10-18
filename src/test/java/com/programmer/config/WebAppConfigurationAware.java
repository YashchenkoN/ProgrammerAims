package com.programmer.config;

/**
 * Created by kolyan on 10/16/15.
 */
/*
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {
        WebAppInitializer.class,
        ApplicationConfig.class,
        AsyncConfig.class,
        JpaConfig.class,
        SecurityConfig.class
})
@ComponentScan(basePackageClasses = Application.class,
        includeFilters = @ComponentScan.Filter({Controller.class, RestController.class, ControllerAdvice.class}),
        useDefaultFilters = false)
//@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
//        TransactionalTestExecutionListener.class})
public class WebTestConfig {

    @Autowired
    private WebApplicationContext wac;
        protected MockMvc mockMvc;
        @Before
        public void before() {
                this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        }
}
*/
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@WebAppConfiguration
@ContextConfiguration(classes = {
        ApplicationConfig.class,
        AsyncConfig.class,
        EmbeddedDataSourceConfig.class,
        JpaConfig.class,
        MailConfig.class,
        SecurityConfig.class,
        WebMvcConfig.class
})
public abstract class WebAppConfigurationAware {

    @Autowired
    protected WebApplicationContext wac;
    protected MockMvc mockMvc;

    @Before
    public void before() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

}

