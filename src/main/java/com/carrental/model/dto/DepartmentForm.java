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
public class DepartmentForm {

    @NotEmpty(message = "{poleWymagane}")
    private String name;

    @NotEmpty(message = "{poleWymagane}")
    private String street;

    @NotEmpty(message = "{poleWymagane}")
    private String buildingNumber;

    @NotEmpty(message = "{poleWymagane}")
    private String city;

}
