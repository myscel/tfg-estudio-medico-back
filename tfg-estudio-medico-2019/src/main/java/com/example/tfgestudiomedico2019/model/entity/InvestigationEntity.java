package com.example.tfgestudiomedico2019.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "investigation")
public class InvestigationEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "idsubject")
	private SubjectEntity subject;
	
	@Column(name = "numberinvestigation")
	private Integer numberInvestigation;
	
	@Column(name = "completed")
	private Boolean completed;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SubjectEntity getSubject() {
		return subject;
	}

	public void setSubject(SubjectEntity subject) {
		this.subject = subject;
	}

	public Integer getNumberInvestigation() {
		return numberInvestigation;
	}

	public void setNumberInvestigation(Integer numberInvestigation) {
		this.numberInvestigation = numberInvestigation;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	@Override
	public String toString() {
		return "InvestigationEntity [id=" + id + ", subject=" + subject + ", numberInvestigation=" + numberInvestigation
				+ ", completed=" + completed + "]";
	}
	
	

}
