package com.carrental.model.dto;

import com.carrental.model.Department;
import com.carrental.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    @NotEmpty(message = "{poleWymagane}")
    private String name;

    @NotEmpty(message = "{poleWymagane}")
    private String surname;

    private Role role;
    private Department department;

    private List<Department> departments;

}
