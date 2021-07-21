package io.hala.etagtemplate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureMockMvc
@Transactional
@SpringBootTest
public class BookControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @BeforeEach
  void registBook() throws Exception {
    mockMvc.perform(post("/book")
        .content(
            "{\"title\": \"test\", \n\"description\": \"testcontent\", \n\"author\" : \"testAuthor\"}")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isCreated())
        .andReturn();
  }

  @Test
  void cacheTestIfNotModified() throws Exception {
    MvcResult mvcResult = mockMvc.perform(get("/book/cache"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(header().exists("ETag"))
        .andReturn();

    String etagValue = mvcResult.getResponse().getHeader("ETag");

    mvcResult = mockMvc.perform(get("/book/cache").header("If-None-Match", etagValue))
        .andDo(print())
        .andExpect(status().isNotModified())
        .andExpect(header().exists("ETag"))
        .andReturn();
  }

  @Test
  void cacheTestIfModified() throws Exception {
    MvcResult mvcResult = mockMvc.perform(get("/book/cache"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(header().exists("ETag"))
        .andReturn();

    String etagValue = mvcResult.getResponse().getHeader("ETag");

    updateBook();

    mvcResult = mockMvc.perform(get("/book/cache").header("If-None-Match", etagValue))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(header().exists("ETag"))
        .andReturn();
  }

  private void updateBook() throws Exception {
    mockMvc.perform(put("/book/1")
        .content(
            "{\"title\": \"test1\", \n\"description\": \"testcontent\", \n\"author\" : \"testAuthor\"}")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
  }
}
