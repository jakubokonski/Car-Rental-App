package com.carrental.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GettingStartedForm {

    @NotEmpty(message = "{poleWymagane}")
    private String name;
    @NotEmpty(message = "{poleWymagane}")
    private String surname;

    @NotEmpty(message = "{poleWymagane}")
    private String companyName;
    @NotEmpty(message = "{poleWymagane}")
    private String domain;

    @NotEmpty(message = "{poleWymagane}")
    private String street;
    @NotEmpty(message = "{poleWymagane}")
    private String buildingNumber;
    @NotEmpty(message = "{poleWymagane}")
    private String city;

}
