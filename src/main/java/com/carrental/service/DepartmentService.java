package com.carrental.service;

import com.carrental.model.Department;
import com.carrental.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EntityManager em;

    @Transactional
    public void addDepartment(Department department) {
        departmentRepository.save(department);
//        em.persist(department.getAddress());
//        em.persist(department);
    }

    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

}
