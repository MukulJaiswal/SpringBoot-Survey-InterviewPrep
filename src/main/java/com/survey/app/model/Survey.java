package com.survey.app.model;

import java.util.List;

import org.springframework.stereotype.Component;

public class Survey {

	private String id;
	private String title;
	private String desc;
	private List<Question> questions;

	public Survey(String id, String title, String desc, List<Question> question) {
		super();
		this.id = id;
		this.title = title;
		this.desc = desc;
		this.questions = question;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestiosn(List<Question> question) {
		this.questions = question;
	}

	@Override
	public String toString() {
		return "Survey [id=" + id + ", title=" + title + ", desc=" + desc + ", question=" + questions + "]";
	}
}



