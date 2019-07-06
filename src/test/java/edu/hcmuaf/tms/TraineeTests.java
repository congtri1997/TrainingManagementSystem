package edu.hcmuaf.tms;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.hcmuaf.tms.entity.Course;
import edu.hcmuaf.tms.entity.ProgrammingLanguage;
import edu.hcmuaf.tms.entity.Role;
import edu.hcmuaf.tms.entity.Trainee;
import edu.hcmuaf.tms.form.ImportTraineeAccountDTO;
import edu.hcmuaf.tms.form.TraineeForm;
import edu.hcmuaf.tms.repository.CourseRepository;
import edu.hcmuaf.tms.repository.RoleRepository;
import edu.hcmuaf.tms.repository.TraineeRepository;
import edu.hcmuaf.tms.service.impl.ProgrammingLanguageService;
import edu.hcmuaf.tms.service.impl.TraineeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TraineeTests {
	@Autowired
	private TraineeRepository traineeRepository;

	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private TraineeService TraineeService;

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private ProgrammingLanguageService programmingLanguageService;
	
	@After
	public void after() {
		new Exception();
	}
	@Before
	public void before() {
		
	}
//	@Test
//	@Transactional
//	public void isUsernameAlreadyExistTest() {
//		boolean result = TraineeService.isUsernameAlreadyExist("trainee1");
//		assertEquals("ok", result, true);
//	}
	
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
	@Test
	@Transactional
	public void updateTraineeTest() {
//		LocalDate date = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		TraineeForm trainee = new TraineeForm();
        trainee.setId(0L);
        trainee.setUserName("update");
        trainee.setFirstName("update");
        trainee.setLastName("update");
        trainee.setPassword("12345");
        trainee.setBirthDate("07/05/1996");
        trainee.setScoreOfToeic("500");
        trainee.setEducation("DHNL");
        trainee.setAddress("address");
        trainee.setDetailsOfExp("5 nam");
        trainee.setProgrammingLanguage(programmingLanguageService.findByName("java"));
        TraineeService.addTrainee(trainee);
        TraineeForm traineeForm = new TraineeForm();
        Trainee traineeInsert = traineeRepository.findByUserName("update");
        traineeForm = TraineeForm.toDTO(traineeInsert);
		traineeForm.setFirstName("thang");
		traineeForm.setDepartment("DTL");
		TraineeService.updateTrainee(traineeForm);
		Trainee traineeEdit = traineeRepository.findByUserName("update");
		assertEquals("thang", traineeEdit.getFirstName());
		assertEquals("DTL", traineeEdit.getDepartment());
		
	}
	
//	@Test
//	public void listAllTest() {
//		List<Trainee> list = TraineeService.listAll();
//		assertEquals(list.size(), 10);
//	}
	@Test
	public void getOneNullTest() {
		Trainee traineeGet = TraineeService.getOne(100L);
		assertEquals(traineeGet, null);
	}
	
//	@Test
//	@Transactional
//	public void findTraineeNotInACourseTest() {
//		Course course = new Course(0, "English");
//		Course courseInsert = courseRepository.save(course);
//		List<Trainee> list = TraineeService.findTraineeNotInACourse(courseInsert.getId());
//		assertEquals(list.size(), 10);
//	}
	
	@Test
	@Transactional
	public void findTraineeInACourseTest() {
		Course course = new Course(0, "English");
		Course courseInsert = courseRepository.save(course);
		List<Trainee> list = TraineeService.findTraineeInACourse(courseInsert.getId());
		assertEquals(list.size(), 0);
	}
	@Test
	@Transactional
	public void importAccounTest() {
		ImportTraineeAccountDTO inport = new ImportTraineeAccountDTO();
		List<TraineeForm> list = new ArrayList<>();
		for (long i = 1; i <= 10; i++) {
            TraineeForm trainee = new TraineeForm();
            trainee.setId(i);
            trainee.setUserName("import"+i);
            trainee.setFirstName("trainee"+i);
            trainee.setLastName("trainee"+i);
            trainee.setPassword("12345");
            trainee.setBirthDate(null);
            trainee.setScoreOfToeic("500");
            trainee.setEducation("DHNL");
            trainee.setAddress("address"+i);
            trainee.setDetailsOfExp("5 nam");
            trainee.setProgrammingLanguage(null);
            list.add(trainee);
		}
		inport.setTraineeForms(list);
		TraineeService.importAccount(inport);
		List<Trainee> listTrainee = TraineeService.listAll();
		int count = 0;
		for(Trainee t:listTrainee) {
			if(t.getUserName().startsWith("import")) {
				count++;
			}
		}
		assertEquals(count,10);
		
	}

}