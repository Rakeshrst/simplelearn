package org.bootcamp.simplelearn.service;

import org.bootcamp.simplelearn.model.Instructor;
import org.bootcamp.simplelearn.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InstructorServiceImpl implements  InstructorService{

	@Autowired
	private InstructorRepository instructorRepo;

	public Instructor retrieveAllInstructors() {
		return instructorRepo.findById(Instructor.ROOT_ID).orElse(null);
	}

	public Instructor saveInstructor(Instructor instructor) {
		instructor = instructorRepo.save(instructor);
		return instructor;
	}

	public Instructor getInstructorDetails(int id) {
		return instructorRepo.findById(id).orElse(null);
	}

	public Instructor getInstructorByName(String instructorName) {
		return instructorRepo.findByInstructorName(instructorName);
	}
}
