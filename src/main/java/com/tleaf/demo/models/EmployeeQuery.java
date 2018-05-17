package com.tleaf.demo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @author Allan Tejano
 * 4/23/2018
 */
@Data
@Builder
@AllArgsConstructor
public class EmployeeQuery {

    public EmployeeQuery() {
    }

    @JsonProperty("employeeId")
    private Integer employeeId;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    // Formats output date when this DTO is passed through JSON
    @JsonFormat(pattern = "dd/MM/yyyy")
    // Allows dd/MM/yyyy date to be passed into GET request in JSON
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;


    @JsonProperty("departmentId")
    private Integer departmentId;
    @JsonProperty("organizationId")
    private Integer organizationId;

}
