package edu.hcmuaf.tms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.hcmuaf.tms.repository.EnrollmentRepository;
@Transactional
@Service
public class EnrollmentService {
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	
	public void removeTraineeFromCourse(long courseID,long traineeID) {
		enrollmentRepository.delete(enrollmentRepository.getEnrollment(courseID, traineeID));
	}
	
}
