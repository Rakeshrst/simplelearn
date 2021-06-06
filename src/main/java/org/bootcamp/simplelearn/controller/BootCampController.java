package org.bootcamp.simplelearn.controller;

import java.net.URI;

import org.bootcamp.simplelearn.exception.UserNotFoundException;
import org.bootcamp.simplelearn.model.Instructor;
import org.bootcamp.simplelearn.pojo.BootCampMembers;
import org.bootcamp.simplelearn.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class BootCampController {

	@Autowired
	private InstructorService instructorService;

	private static final String INSTRUCTORNOTVALID="- This instructor is not participating in the bootcamp.Please add him first under any other available instructor";

	@GetMapping("/instructor/{instructorName}")
	public Instructor findMyInstructor(@PathVariable String instructorName) {
		Instructor instructor = instructorService.getInstructorByName(instructorName);
		if (instructor == null) {
			throw new UserNotFoundException(instructorName+ INSTRUCTORNOTVALID);
		}
		return instructorService.getInstructorByName(instructorName);
	}

	// Get All the instructors

	@GetMapping("/instructors")
	public Instructor getAllInstructors() {

		return instructorService.retrieveAllInstructors();
	}

	@PostMapping("/instructor")
	public ResponseEntity<Instructor> saveInstructor(@RequestBody BootCampMembers subordinatePojo) {
		Instructor instructor = new Instructor();
		instructor.setInstructorName(subordinatePojo.getInstructorName());
		Instructor parent = instructorService.getInstructorByName(subordinatePojo.getAdmin());
		if (parent != null) {
			instructor.setParent(parent);
		} else {
			throw new UserNotFoundException(subordinatePojo.getAdmin()+INSTRUCTORNOTVALID);
		}

		instructor = instructorService.saveInstructor(instructor);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/").buildAndExpand(instructor.getInstructorName())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@PostMapping("/instructor/{instructorName}/subordinate")
	public ResponseEntity<Instructor> addSubordinateByName(@PathVariable String instructorName,
			@RequestBody BootCampMembers subordinatePojo) {
		Instructor instructor = instructorService.getInstructorByName(instructorName);
		if (instructor == null) {
			throw new UserNotFoundException(instructorName +INSTRUCTORNOTVALID);
		}
		Instructor subordinate = new Instructor();
		subordinate.setInstructorName(subordinatePojo.getInstructorName());
		subordinate.setParent(instructor);
		subordinate=	instructorService.saveInstructor(subordinate);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/").buildAndExpand(subordinate.getInstructorName())
				.toUri();

		return ResponseEntity.created(location).build();
	}


}
