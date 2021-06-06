package org.bootcamp.simplelearn.repository;

import java.util.List;

import org.bootcamp.simplelearn.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Integer>{
	
	@Query(value = "select n from Instructor n inner join n.parent p where p.id = :parentId")
	List<Instructor> findInstructorByParentId(@Param("parentId") int parentId);
	

	Instructor findByInstructorName(String instructorName);
}
