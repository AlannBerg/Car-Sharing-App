package com.example.CarRentalAplication.Controlers;

import com.example.CarRentalAplication.Security.MyUserDetailService;
import com.example.CarRentalAplication.Services.LocalizationService;
import com.example.CarRentalAplication.contract.LocalizationDTO;
import com.example.CarRentalAplication.models.Localization;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = LocalizationControler.class)
@AutoConfigureMockMvc(addFilters = false)
class LocalizationControlerTest{

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MyUserDetailService myUserDetailService;
    @MockBean
    LocalizationService localizationService;

    @Test
    void getLocalizations() throws Exception {
        String uri = "/localization/getLocalizations";

        List<LocalizationDTO> localizationDTOList =  List.of(
                new LocalizationDTO("1","1","1","1"),
                new LocalizationDTO("1","1","1","1"));

        when(localizationService.getLocalizations()).thenReturn(localizationDTOList);

        RequestBuilder request = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE);
        String result = mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertEquals("[{\"city\":\"1\",\"postalCode\":\"1\",\"street\":\"1\",\"localNumber\":\"1\"},{\"city\":\"1\",\"postalCode\":\"1\",\"street\":\"1\",\"localNumber\":\"1\"}]",
                result);
    }
}