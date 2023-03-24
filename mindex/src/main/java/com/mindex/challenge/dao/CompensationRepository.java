package com.mindex.challenge.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;

/**
 * 
 * @author Dan
 * 
 * Repository for Compensation types.  Searching is done
 * by querying by employee.
 */
@Repository
public interface CompensationRepository extends MongoRepository<Compensation, String>{
	Compensation findByEmployee(Employee employee);
}
