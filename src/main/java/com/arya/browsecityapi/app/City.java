package com.arya.browsecityapi.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class City {

    private String id;
    private String name;
    private String ascii;
    private List<String> altNames;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Long population;
}
