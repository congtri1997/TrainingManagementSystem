package edu.hcmuaf.tms;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.transaction.Transaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.hcmuaf.tms.entity.ProgrammingLanguage;
import edu.hcmuaf.tms.entity.Role;
import edu.hcmuaf.tms.entity.Trainee;
import edu.hcmuaf.tms.form.TraineeForm;
import edu.hcmuaf.tms.repository.RoleRepository;
import edu.hcmuaf.tms.repository.TraineeRepository;
import edu.hcmuaf.tms.service.impl.ProgrammingLanguageService;
import edu.hcmuaf.tms.service.impl.TraineeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrainingManagmentSystemApplicationTests {
	@Autowired
	private TraineeRepository traineeRepository;

	@Autowired
	private TraineeService TraineeService;

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private ProgrammingLanguageService programmingLanguageService;
	
	@After
	public void after() {
		new Exception();
	}
	@Before
	public void before() {
		
	}
	@Test
	public void isUsernameAlreadyExistTest() {
		boolean result = TraineeService.isUsernameAlreadyExist("trainee1");
		assertEquals("ok", result, true);
	}
	
	@Test
	@Transactional
	public void addTraineeTest() throws Exception{
		Role role = roleRepository.findByName("ROLE_TRAINEE");
		ProgrammingLanguage programmingLanguage = programmingLanguageService.findByName("Java");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Trainee trainee = new Trainee(0,"username","password",true,role,"firstName","lastName",LocalDate.parse("20/11/2019", formatter),"dhnl",programmingLanguage,500,"abc","phongban","diachi");
		TraineeForm traineeForm = TraineeForm.toDTO(trainee);
		traineeForm.setPassword("password");
		TraineeService.addTrainee(traineeForm);
		
		boolean result = TraineeService.isUsernameAlreadyExist("username");
		assertEquals("ok",result,true);
		
	}
	@Test
	@Transactional
	public void addTraineeForgetBirthDateIsNullTest() throws Exception{
		Role role = roleRepository.findByName("ROLE_TRAINEE");
		ProgrammingLanguage programmingLanguage = programmingLanguageService.findByName("Java");
		Trainee trainee = new Trainee(0,"username","password",true,role,"firstName","lastName",null,"dhnl",programmingLanguage,500,"abc","phongban","diachi");
		TraineeForm traineeForm = TraineeForm.toDTO(trainee);
		traineeForm.setPassword("password");
		TraineeService.addTrainee(traineeForm);
		
		boolean result = TraineeService.isUsernameAlreadyExist("username");
		assertEquals("ok",result,true);
	}
	@Test
	@Transactional
	public void addTraineeForgetScoreOfToeicIsNullTest() throws Exception{
		Role role = roleRepository.findByName("ROLE_TRAINEE");
		ProgrammingLanguage programmingLanguage = programmingLanguageService.findByName("Java");
		Trainee trainee = new Trainee(0,"username","password",true,role,"firstName","lastName",null,"dhnl",programmingLanguage,0,"abc","phongban","diachi");
		TraineeForm traineeForm = TraineeForm.toDTO(trainee);
		traineeForm.setPassword("password");
		traineeForm.setScoreOfToeic(null);
		TraineeService.addTrainee(traineeForm);
		
		boolean result = TraineeService.isUsernameAlreadyExist("username");
		assertEquals("ok",result,true);
	}
	@Test
	@Transactional
	public void addTraineeForgetProgrammingLanguageIsNullTest() throws Exception{
		Role role = roleRepository.findByName("ROLE_TRAINEE");
		ProgrammingLanguage programmingLanguage = programmingLanguageService.findByName("Java");
		Trainee trainee = new Trainee(0,"username","password",true,role,"firstName","lastName",null,"dhnl",programmingLanguage,0,"abc","phongban","diachi");
		TraineeForm traineeForm = TraineeForm.toDTO(trainee);
		traineeForm.setPassword("password");
		traineeForm.setScoreOfToeic(null);
		traineeForm.setProgrammingLanguage(null);
		TraineeService.addTrainee(traineeForm);
		
		boolean result = TraineeService.isUsernameAlreadyExist("username");
		assertEquals("ok",result,true);
	}
	@Test
	public void addTraineeTestForNull() {
		List<Trainee> befor = traineeRepository.findAll();
		TraineeService.addTrainee(null);
		List<Trainee> after = traineeRepository.findAll();
		assertEquals("ok", befor.size(), after.size());
	}

}
