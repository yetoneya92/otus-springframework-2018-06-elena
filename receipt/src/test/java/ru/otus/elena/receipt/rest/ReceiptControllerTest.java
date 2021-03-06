package ru.otus.elena.receipt.rest;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ConfigurationProperties("application")
@EnableMongoRepositories
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class ReceiptControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(username = "vasya")
    public void startPageTest() throws Exception {
        MediaType HTML_UTF_8 = new MediaType("text", "html", java.nio.charset.Charset.forName("UTF-8"));
        this.mvc.perform(get("/receipt")).andExpect(status().isOk())
                .andExpect(content().contentType(HTML_UTF_8));
    }

    @Test
    @WithMockUser(username = "vasya")
    public void manualPageTest() throws Exception {
        MediaType HTML_UTF_8 = new MediaType("text", "html", java.nio.charset.Charset.forName("UTF-8"));
        this.mvc.perform(get("/manual")).andExpect(status().isOk())
                .andExpect(content().contentType(HTML_UTF_8));
    }
}
