package com.mindex.challenge.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

/**
 * 
 * @author Dan
 * 
 * Service implementation for reading ReportingStructure types.
 */
@Service
public class ReportingStructureServiceImpl implements ReportingStructureService{
	
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);
	
    @Autowired
    private EmployeeRepository empoyeeRepository;

    @Override
    public ReportingStructure read(String id) {
        LOG.debug("Reading compensation with id [{}]", id);

        Employee employee = empoyeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return new ReportingStructure(employee);
    }

}
