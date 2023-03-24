package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

/**
 * 
 * @author Dan
 *
 * Interface for the CompensationService implementation that allows for
 * creating and reading actions.  
 * Spring autowires this for us.
 */
public interface CompensationService {
	Compensation create(Compensation compensation);
	Compensation read(String id);
}
