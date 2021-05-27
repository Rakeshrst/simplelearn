package org.bootcamp.simplelearn.service;

import java.util.List;

import org.bootcamp.simplelearn.model.Instructor;
import org.bootcamp.simplelearn.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InstructorService {

	@Autowired
	private InstructorRepository instructorRepo;
	
	public List<Instructor> retrieveAllInstructors(){
		
		return instructorRepo.findAll();
	}
	
	public Instructor saveInstructor(Instructor instructor){
		
		instructor= instructorRepo.save(instructor);
		
		return instructor;
	}
	
	public Instructor getInstructorDetails(int id){
		
		Instructor instructor= instructorRepo.findById(id).get();
		
		return instructor;
	}
	
	
	
	public void addSubordinate(Instructor instructor,Instructor subordinate) {
		instructor.setSubordinate(subordinate);
		instructorRepo.save(instructor);
	}
	
}
