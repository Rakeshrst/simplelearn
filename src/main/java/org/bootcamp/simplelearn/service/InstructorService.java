package org.bootcamp.simplelearn.service;

import org.bootcamp.simplelearn.model.Instructor;
import org.springframework.stereotype.Component;

@Component
public interface InstructorService {

	public Instructor retrieveAllInstructors() ;
	public Instructor getInstructorDetails(int id);
	public Instructor getInstructorByName(String instructorName);
	public Instructor saveInstructor(Instructor instructor);
	
}
