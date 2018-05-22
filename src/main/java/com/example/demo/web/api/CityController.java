package com.example.demo.web.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<City> getCities(){
        City moscow = new City(1, "Moscow");
        City stpetersburg = new City(2, "St. Petersburg");
        City belgorod = new City(2, "Belgorod");
        
        return Arrays.asList(moscow,stpetersburg,belgorod);
    }
    
    @PreAuthorize("permitAll()")
    @GetMapping("/default")
    public City getDefaultCity(){
        City city = new City(1,"Default city");
        return city;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class City {
        private Integer id;
        private String name;
    }
}
