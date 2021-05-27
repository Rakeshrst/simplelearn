package org.bootcamp.simplelearn.repository;

import org.bootcamp.simplelearn.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor,Integer>{

}
