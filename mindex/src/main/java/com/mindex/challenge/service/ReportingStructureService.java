package com.mindex.challenge.service;

import com.mindex.challenge.data.ReportingStructure;

/**
 * 
 * @author Dan
 *
 * Interface for the ReportingStructureService implementation that allows reading.  
 * Spring autowires this for us. 
 */
public interface ReportingStructureService {
	ReportingStructure read(String id);
	
}
