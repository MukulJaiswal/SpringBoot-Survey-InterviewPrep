package com.survey.app.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.survey.app.model.Question;
import com.survey.app.service.SurveyService;

@RestController // Rest controller is a combination of @Controller and @ResponseBody
//Here we want to get data in JSON format and for that @ResponseBody annotation is responsible.

public class SurveyController {

	@Autowired
	private SurveyService surveyService;

	@GetMapping("surveys/{surveyId}/questions")
	public List<Question> retriveQuestionsFromSurvey(@PathVariable String surveyId) {
		return surveyService.retrieveQuestions(surveyId);
	}

	@GetMapping("surveys/{surveyId}/questions/{questionId}")
	public Question retriveSpecificQuestion(@PathVariable String surveyId, @PathVariable String questionId) {
		return surveyService.retrieveQuestion(surveyId, questionId);
	}

	// /surveys/{surveyId}/questions -
	// http://localhost:8080/surveys/Survey1/questions

	// Here @RequestBody annotation is used because we want Question from the
	// POSTMAN Post request and add here. Basically @RequestBody converts the JSON into Java Object so that it can be added

	@PostMapping("/surveys/{surveyId}/questions")
	public ResponseEntity<Void> addQuestionToSurvey(@PathVariable String surveyId, @RequestBody Question newQuestion) {

		Question question = surveyService.addQuestion(surveyId, newQuestion);

		if (question == null)
			return ResponseEntity.noContent().build();

		// Success - URI of the new resource in Response Header
		// Status - created
		// URI -> /surveys/{surveyId}/questions/{questionId}
		// question.getQuestionId()
		// http://localhost:8080/surveys/Survey1/questions

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(question.getId())
				.toUri();

		System.out.println("location :: " + location);
		// location -
		// http://localhost:8080/surveys/Survey1/questions/empjnp29bc4b4ujr87i0tne5tk

		return ResponseEntity.created(location).build();
	}
}
