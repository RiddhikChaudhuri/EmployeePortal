package com.registration.employee.controller.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.registration.employee.controller.EmployeeRestController;
import com.registration.employee.entity.Employee;
import com.registration.employee.service.EmployeeService;


@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeRestController.class, secure = false)
public class EmployeeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmployeeService employeeService;
	
	Employee employee = new Employee(1,"Riddhik","Chaudhuri","Male",LocalDate.of(1993,9,29),"IT");
	
	ArrayList<Employee> employeeList = new ArrayList<Employee>();
	
	
	
	@Test
	public void getListofEmployeesTest() throws Exception {
		
		employeeList.add(employee);

		Mockito.when(
				employeeService.getListOfEmployees()).thenReturn(employeeList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/listofEmployees").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "[{\"firstName\":\"Riddhik\",\"lastName\":\"Chaudhuri\",\"gender\":\"Male\",\"dateOfBirth\":\"1993-09-29\",\"department\":\"IT\",\"id\":1}]";


		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	

}
