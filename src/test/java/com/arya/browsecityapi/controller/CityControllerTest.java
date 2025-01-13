package com.arya.browsecityapi.controller;

import com.arya.browsecityapi.api.CityController;
import com.arya.browsecityapi.api.mapper.ICityApiMapper;
import com.arya.browsecityapi.api.model.SuggestCityApiResponse;
import com.arya.browsecityapi.api.response.RestExceptionHandler;
import com.arya.browsecityapi.api.response.SuccessResponse;
import com.arya.browsecityapi.app.City;
import com.arya.browsecityapi.app.CityScoreWrapper;
import com.arya.browsecityapi.app.service.ICityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class CityControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ICityService service;

    @Mock
    private ICityApiMapper mapper;

    @InjectMocks
    private CityController cityController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(cityController)
                .setControllerAdvice(new RestExceptionHandler()) // Set up exception handler
                .build();
    }

    @Test
    public void c() throws Exception {
        String q = "Lon";
        BigDecimal latitude = BigDecimal.valueOf(51.5074);
        BigDecimal longitude = BigDecimal.valueOf(-0.1278);

        City london = City.builder()
                .id("1")
                .name("London")
                .ascii("London")
                .altNames(List.of("Londinium"))
                .latitude(BigDecimal.valueOf(51.5074))
                .longitude(BigDecimal.valueOf(-0.1278))
                .population(8982000L)
                .build();

        City losAngeles = City.builder()
                .id("2")
                .name("Los Angeles")
                .ascii("Los Angeles")
                .altNames(List.of("LA"))
                .latitude(BigDecimal.valueOf(34.0522))
                .longitude(BigDecimal.valueOf(-118.2437))
                .population(3979576L)
                .build();

        SuggestCityApiResponse response1 = SuggestCityApiResponse.builder()
                .name("London")
                .latitude("51.5074")
                .longitude("-0.1278")
                .score(0.9)
                .build();

        SuggestCityApiResponse response2 = SuggestCityApiResponse.builder()
                .name("Los Angeles")
                .latitude("34.0522")
                .longitude("-118.2437")
                .score(0.85)
                .build();

        List<SuggestCityApiResponse> mappedResponse = List.of(response1, response2);

        CityScoreWrapper wrapper1 = CityScoreWrapper.builder()
                .city(london)
                .score(0.9)
                .build();

        CityScoreWrapper wrapper2 = CityScoreWrapper.builder()
                .city(losAngeles)
                .score(0.85)
                .build();

        List<CityScoreWrapper> wrappers = List.of(wrapper1, wrapper2);

        when(service.getCitySuggestions(q, latitude, longitude)).thenReturn(wrappers);

        mockMvc.perform(get("/city/suggestions")
                        .param("q", q)
                        .param("latitude", latitude.toString())
                        .param("longitude", longitude.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.suggestions").isArray())
                .andExpect(jsonPath("$.suggestions[0].name").value("London"))
                .andExpect(jsonPath("$.suggestions[0].latitude").value("51.5074"))
                .andExpect(jsonPath("$.suggestions[0].longitude").value("-0.1278"))
                .andExpect(jsonPath("$.suggestions[0].score").value(0.9))
                .andExpect(jsonPath("$.suggestions[1].name").value("Los Angeles"))
                .andExpect(jsonPath("$.suggestions[1].latitude").value("34.0522"))
                .andExpect(jsonPath("$.suggestions[1].longitude").value("-118.2437"))
                .andExpect(jsonPath("$.suggestions[1].score").value(0.85));
    }

    @Test
    public void testGetSuggestions_RequiredParameterNotFound() throws Exception {
        mockMvc.perform(get("/city/suggestions")
                        .param("latitude", "51.5074")
                        .param("longitude", "-0.1278"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.errorMessage").value("Required Parameter Not Found!"))
                .andExpect(jsonPath("$.timestamp").isNotEmpty());
    }

    @Test
    public void testGetSuggestions_MissingRequiredParameter() throws Exception {
        mockMvc.perform(get("/city/suggestions")
                        .param("q", "")
                        .param("latitude", "51.5074")
                        .param("longitude", "-0.1278"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.errorMessage").value("Required Parameter Not Found!"))
                .andExpect(jsonPath("$.timestamp").isNotEmpty());
    }

    @Test
    public void testGetSuggestions_ServerError() throws Exception {
        String q = "Lon";
        BigDecimal latitude = BigDecimal.valueOf(51.5074);
        BigDecimal longitude = BigDecimal.valueOf(-0.1278);

        when(service.getCitySuggestions(q, latitude, longitude)).thenThrow(new RuntimeException("Service failure"));

        mockMvc.perform(get("/city/suggestions")
                        .param("q", q)
                        .param("latitude", latitude.toString())
                        .param("longitude", longitude.toString()))
                .andExpect(status().isInternalServerError());
    }
}
