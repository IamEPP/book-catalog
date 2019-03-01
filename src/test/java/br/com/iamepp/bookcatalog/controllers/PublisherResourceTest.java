package br.com.iamepp.bookcatalog.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import br.com.iamepp.bookcatalog.services.PublisherService;
import br.com.iamepp.bookcatalog.services.dtos.PublisherDTO;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.nio.charset.StandardCharsets;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(PublisherResource.class)
public class PublisherResourceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PublisherService publisherService;

    public static final MediaType APPLICATION_JSON_UTF8 =
            new MediaType(MediaType.APPLICATION_JSON.getType(),
                    MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    @Test
    public void mustAddANewValidPublisher() throws Exception {
        var publisher = new PublisherDTO("Contoso", "5th avenue, 33");
        given(publisherService.save(publisher))
                .willReturn(new PublisherDTO(1l, "Contoso", "5th avenue, 33"));
        var content = new ObjectMapper().writeValueAsString(publisher);

        mvc.perform(post("/publisher").content(content)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists());;
    }

    @Test
    public void mustRegectNewNamelessPublisher() throws Exception {
        var publisher = new PublisherDTO(null, "5th avenue, 33");
        given(publisherService.save(publisher))
                .willReturn(new PublisherDTO(1l, "Contoso", "5th avenue, 33"));

        var content = new ObjectMapper().writeValueAsString(publisher);

        mvc.perform(post("/publisher").content(content)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest());
    }


    @Test
    public void testRegisterValid() throws Exception {

    }
}
