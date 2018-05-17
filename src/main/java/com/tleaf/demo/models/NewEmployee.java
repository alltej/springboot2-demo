package com.tleaf.demo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * @author Allan Tejano
 * 4/23/2018
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewEmployee {

    @NotEmpty
    @Size(min=9, max = 9, message = "Invalid SSN")
    @JsonProperty("ssn")
    private String ssn;

    @NotEmpty
    @JsonProperty("firstName")
    private String firstName;

    @NotEmpty
    @JsonProperty("lastName")
    private String lastName;

    // Formats output date when this DTO is passed through JSON
    @JsonFormat(pattern = "dd/MM/yyyy")
    // Allows dd/MM/yyyy date to be passed into GET request in JSON
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("dateOfBirth")
    private LocalDate dateOfBirth;

    @JsonProperty("departmentId")
    private Integer departmentId;
    @JsonProperty("organizationId")
    private Integer organizationId;
}
