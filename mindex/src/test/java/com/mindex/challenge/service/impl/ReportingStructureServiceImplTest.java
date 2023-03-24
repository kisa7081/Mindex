package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
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

/**
 * 
 * @author Dan
 *
 * Test to read a ReportingStructure type.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    private String reportingStructureIdUrl;

    @Autowired
    private EmployeeService employeeService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        reportingStructureIdUrl = "http://localhost:" + port + "/reportingStructure/{id}";
    }

    @Test
    public void testCreateReadUpdate() {
    	// An employee type is needed for test.
    	Employee testEmployee = employeeService.read("16a596ae-edd3-4847-99fe-c4518e82c86f");
    	assertNotNull(testEmployee);
    	
    	ReportingStructure testReportingStructure = new ReportingStructure(testEmployee);
    	
        // Read check
    	ReportingStructure readReportingStructure = restTemplate.getForEntity(reportingStructureIdUrl, ReportingStructure.class, "16a596ae-edd3-4847-99fe-c4518e82c86f").getBody();
        assertNotNull(readReportingStructure);
        assertReportingStructureEquivalence(testReportingStructure, readReportingStructure);

    }

    /**
     * 
     * @param expected
     * @param actual
     * 
     * Ensures that two ReportingStructure objects are equivalent.
     */
    private static void assertReportingStructureEquivalence(ReportingStructure expected, ReportingStructure actual) {
        assertEquals(expected.getEmployee().getEmployeeId(), actual.getEmployee().getEmployeeId());
        assertEquals(expected.getNumberOfReports(), actual.getNumberOfReports());
    }
}
