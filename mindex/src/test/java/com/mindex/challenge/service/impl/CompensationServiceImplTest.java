package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

/**
 * 
 * @author Dan
 *
 * Tests to read and create a Compensation type.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String compensationUrl;
    private String compensationIdUrl;

    @Autowired
    private EmployeeService employeeService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        compensationUrl = "http://localhost:" + port + "/compensation";
        compensationIdUrl = "http://localhost:" + port + "/compensation/{id}";
    }

    @Test
    public void testCreateReadUpdate() {
    	// Set fields to be used to create a test compensation - using 
    	// John Lennon employee for test with employeeId = 16a596ae-edd3-4847-99fe-c4518e82c86f.
        Employee testEmployee = employeeService.read("16a596ae-edd3-4847-99fe-c4518e82c86f");
        double salary = 1.0;
        Date effectiveDate = new Date();
        
        // create and persist the test compensation object
        Compensation testCompensation = new Compensation();
        testCompensation.setEmployee(testEmployee);
        testCompensation.setSalary(salary);
        testCompensation.setEffectiveDate(effectiveDate);
        
        // Create check
        Compensation createdCompensation = restTemplate.postForEntity(compensationUrl, testCompensation, Compensation.class).getBody();
        assertNotNull(createdCompensation);
        assertCompensationEquivalence(testCompensation, createdCompensation);


        // Read check
        Compensation readCompensation = restTemplate.getForEntity(compensationIdUrl, Compensation.class, createdCompensation.getEmployee().getEmployeeId()).getBody();
        assertNotNull(readCompensation);
        assertCompensationEquivalence(createdCompensation, readCompensation);

    }

    /**
     * 
     * @param expected
     * @param actual
     * 
     * Ensures that two Compensation objects are equivalent.
     */
    private static void assertCompensationEquivalence(Compensation expected, Compensation actual) {
        assertEquals(expected.getEmployee().getEmployeeId(), actual.getEmployee().getEmployeeId());
        assertEquals(expected.getSalary(), actual.getSalary(), 0.0);
        assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());
    }
}
