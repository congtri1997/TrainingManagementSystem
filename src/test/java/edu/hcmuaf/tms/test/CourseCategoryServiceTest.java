package edu.hcmuaf.tms.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import edu.hcmuaf.tms.entity.CourseCategory;
import edu.hcmuaf.tms.form.CourseCategoryForm;
import edu.hcmuaf.tms.service.impl.CourseCategoryService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CourseCategoryServiceTest {
	
	@MockBean
	CourseCategoryService courseCategoryService;
	
	

	@Test
	public void testAddCourseCategory() {
		CourseCategoryForm courseCategoryForm = new CourseCategoryForm();
		courseCategoryForm.setName("A");
		courseCategoryForm.setId(1000L);
		courseCategoryService.addCourseCategory(courseCategoryForm);
		
		CourseCategory courseCategory = courseCategoryService.getOne(1000L);
		assertEquals(courseCategoryForm.getName(),courseCategory.getName());
	}
}
