package com.carrental.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String domain;

    @ManyToOne
    private Address address;

    @ManyToOne
    private Employee owner;
    private String logotype;

//    @ElementCollection(targetClass = Department.class)
//    private List<Department> departmentList;

}
