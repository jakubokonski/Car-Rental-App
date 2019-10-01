package com.carrental.model;

import com.carrental.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "{poleWymagane}")
    private String name;

    @NotEmpty(message = "{poleWymagane}")
    private String surname;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @ManyToOne(cascade = CascadeType.ALL)
    private Department department;

}
