package edu.hcmuaf.tms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.hcmuaf.tms.entity.Course;
import edu.hcmuaf.tms.entity.Enrollment;
import edu.hcmuaf.tms.entity.Trainee;
import edu.hcmuaf.tms.form.AddTraineeIntoACourseDTO;
import edu.hcmuaf.tms.repository.CourseRepository;
import edu.hcmuaf.tms.repository.EnrollmentRepository;
import edu.hcmuaf.tms.repository.TraineeRepository;

@Service
@Transactional
public class AddTraineeIntoACourseService {

	@Autowired
	private TraineeRepository traineeRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private EnrollmentRepository enrollmentRepository;

	public void addTraineeIntoACourse(AddTraineeIntoACourseDTO addTraineeIntoACourseDTO) {
		if (addTraineeIntoACourseDTO.getCourseID() != null
				&& courseRepository.existsById(addTraineeIntoACourseDTO.getCourseID())) {
			for (Long traineeID : addTraineeIntoACourseDTO.getTraineeIDs()) {
				if (traineeID != null && traineeRepository.existsById(traineeID)) {
					if (!enrollmentRepository.isEnrollmentAlreadyExist(addTraineeIntoACourseDTO.getCourseID(),
							traineeID)) {
						enrollmentRepository
								.save(new Enrollment(courseRepository.getOne(addTraineeIntoACourseDTO.getCourseID()),
										traineeRepository.getOne(traineeID)));

					}
				}

			}
		}
	}

}
