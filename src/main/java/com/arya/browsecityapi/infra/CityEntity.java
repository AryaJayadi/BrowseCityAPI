package com.arya.browsecityapi.infra;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cities")
public class CityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String ascii;

    @Column(
        name = "alt_name",
        length = 1024
    )
    private String altName;

    private BigDecimal lat;
    @Column(name = "long")
    private BigDecimal lon;

    @Column(name = "feat_class")
    private String featClass;

    @Column(name = "feat_code")
    private String featCode;

    private String country;

    private String cc2;

    private String admin1;
    private String admin2;
    private String admin3;
    private String admin4;

    private Long population;

    private Integer elevation;

    private Integer dem;

    private String tz;

    @Column(name = "modified_at")
    private LocalDate modifiedAt;
}
