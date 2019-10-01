package com.carrental.service;

import com.carrental.model.Company;
import com.carrental.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public void saveCompany(Company company) {
        companyRepository.save(company);
    }

}
