package com.carrental.model.dto;

import com.carrental.model.util.UniqueEmail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientForm {

    @NotEmpty(message = "{poleWymagane}")
    private String name;
    @NotEmpty(message = "{poleWymagane}")
    private String surname;

    @Email(message = "Podaj poprawny email")
    @NotEmpty(message = "{poleWymagane}")
    @UniqueEmail
    private String email;
    @NotEmpty(message = "{poleWymagane}")
    private String password;

    @NotEmpty(message = "{poleWymagane}")
    private String street;
    @NotEmpty(message = "{poleWymagane}")
    private String buildingNumber;
    @NotEmpty(message = "{poleWymagane}")
    private String city;



}
