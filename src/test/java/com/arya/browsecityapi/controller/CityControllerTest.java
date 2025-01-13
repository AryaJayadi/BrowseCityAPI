package com.arya.browsecityapi.controller;

import com.arya.browsecityapi.app.City;
import com.arya.browsecityapi.app.CityScoreWrapper;
import com.arya.browsecityapi.app.service.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private CityService cityService;
    private List<CityScoreWrapper> mockCitySuggestions;

    @BeforeEach
    public void setUp() {
        City city1 = City.builder()
                .id("1")
                .name("London, ON, Canada")
                .ascii("London")
                .altNames(List.of("London", "Londres"))
                .latitude(new BigDecimal("42.98339"))
                .longitude(new BigDecimal("-81.23304"))
                .population(400000L)
                .build();

        City city2 = City.builder()
                .id("2")
                .name("London, KY, USA")
                .ascii("London")
                .altNames(List.of("London", "Londres"))
                .latitude(new BigDecimal("37.12898"))
                .longitude(new BigDecimal("-84.08326"))
                .population(8000L)
                .build();

        City city3 = City.builder()
                .id("3")
                .name("London, OH, USA")
                .ascii("London")
                .altNames(List.of("London", "Londres"))
                .latitude(new BigDecimal("39.88645"))
                .longitude(new BigDecimal("-83.44825"))
                .population(10000L)
                .build();

        mockCitySuggestions = List.of(
                CityScoreWrapper.builder().city(city1).score(0.9).build(),
                CityScoreWrapper.builder().city(city2).score(0.8).build(),
                CityScoreWrapper.builder().city(city3).score(0.7).build()
        );
    }

    @Test
    void testGetCitySuggestions_withValidQuery() throws Exception {
        Mockito.when(cityService.getCitySuggestions("London", new BigDecimal("43.70011"), new BigDecimal("-79.4163")))
                .thenReturn(mockCitySuggestions);

        mockMvc.perform(MockMvcRequestBuilders.get("/city/suggestions")
                        .param("q", "London")
                        .param("latitude", "43.70011")
                        .param("longitude", "-79.4163"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].city.name").value("London, ON, Canada"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].score").value(0.9));
    }

    @Test
    void testGetCitySuggestions_noMatches() throws Exception {
        Mockito.when(cityService.getCitySuggestions("NonexistentCity", null, null))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/city/suggestions")
                        .param("q", "NonexistentCity"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0));
    }

    @Test
    void testGetCitySuggestions_withBlankQuery() throws Exception {
        Mockito.when(cityService.getCitySuggestions("", null, null))
                .thenThrow(new InvalidParameterException("'q' parameter cannot be null"));

        mockMvc.perform(MockMvcRequestBuilders.get("/city/suggestions")
                        .param("q", ""))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
