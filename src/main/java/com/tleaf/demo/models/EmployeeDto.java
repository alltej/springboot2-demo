package com.tleaf.demo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @author Allan Tejano
 * 4/20/2018
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    @JsonProperty("employeeId")
    private Integer employeeId;

    @JsonProperty("organization")
    private String organization;
    
    @JsonProperty("department")
    private String department;

    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("dateOfBirth" )
    private LocalDate dateOfBirth;

}
