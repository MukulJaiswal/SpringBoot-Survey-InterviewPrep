package com.survey.app.controller.Survey;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.survey.app.controller.SurveyController;
import com.survey.app.model.Question;
import com.survey.app.service.SurveyService;

/**
 * It works in three steps:
 * Step:1 - Use Mockito when and define a class and func which we want to test
 * Step:2 - Make a call a service using MockMvc
 * Step:3 - Assert
 * @author Mukul
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(SurveyController.class)
public class SurveyControllerTest {

	@MockBean
	private SurveyService surveyService;

	// MockMvc allows us to make call to the services.
	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser(username = "user1", password = "secret1")//Because Spring Security is used.
	public void retriveDetailsFromQuestion() throws Exception {
		Question mockQuestion = new Question("Question1", "Largest Country in the World", "Russia",
				Arrays.asList("India", "Russia", "United States", "China"));

		// Step:1 - Use Mockito when and define a class and func which we want to test
		Mockito.when(surveyService.retrieveQuestion(Mockito.anyString(), Mockito.anyString())).thenReturn(mockQuestion);

		// Step:2 - Make a call a service

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/surveys/Survey1/questions/Question1")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{\"id\":\"Question1\",\"description\":\"Largest Country in the World\",\"correctAnswer\":\"Russia\"}";

		System.out.println(expected);
		System.out.println(result.getResponse().getContentAsString());

		// Step:3 - Assert

//		JSONAssert.assertEquals(expected, actual, strict);
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

	@Test
	@WithMockUser(username = "user1", password = "secret1")
	public void createSurveyQuestion() throws Exception {
		Question mockQuestion = new Question("1", "Smallest Number", "1", Arrays.asList("1", "2", "3", "4"));  //Input
		
		//Output should be in JSON format like below.
		String questionJson = "{\"description\":\"Smallest Number\",\"correctAnswer\":\"1\",\"options\":[\"1\",\"2\",\"3\",\"4\"]}";  //Output 
		
		Mockito.when(surveyService.addQuestion(Mockito.anyString(), Mockito.any(Question.class)))
				.thenReturn(mockQuestion);

		// Send question as body to /surveys/Survey1/questions
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/surveys/Survey1/questions")
				.accept(MediaType.APPLICATION_JSON).content(questionJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost/surveys/Survey1/questions/1", response.getHeader(HttpHeaders.LOCATION));
	}
}






