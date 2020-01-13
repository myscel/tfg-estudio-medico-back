package com.example.tfgestudiomedico2019.model.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "investigation_details")
public class InvestigationEntityDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	
	
	@Column(name = "vitaminD")
	private Float vitaminD;
	@Column(name = "hba1c")
	private Float hba1c;
	@Column(name = "season")
	private String season;
	
	@Column(name = "gender")
	private String gender;
	@Column(name = "studyLevel")
	private String studyLevel;
	@Column(name = "birthDate")
	private Date birthDate;
	@Column(name = "socioeconomicLevel")
	private String socioeconomicLevel;

	@Column(name = "tobacco")
	private Boolean tobacco;
	@Column(name = "riskAlcohol")
	private Boolean riskAlcohol;
	@Column(name = "solarExposure")
	private Float solarExposure;
	@Column(name = "spfCream")
	private Boolean spfCream;
	@Column(name = "spfScore")
	private Float spfScore;
	@Column(name = "exercise")
	private Float exercise;
	
	@Column(name = "dm2")
	private Boolean dm2;
	@Column(name = "glucose")
	private Float glucose;
	@Column(name = "imc")
	private Float imc;
	@Column(name = "obesity")
	private Boolean obesity;
	@Column(name = "tas")
	private Float tas;
	@Column(name = "tad")
	private Float tad;
	@Column(name = "arterialHypertension")
	private Boolean arterialHypertension;
	@Column(name = "cholesterol")
	private Float cholesterol;
	@Column(name = "ldl")
	private Float ldl;
	@Column(name = "hdl")
	private Float hdl;
	@Column(name = "tg")
	private Float tg;
	@Column(name = "dyslipemy")
	private Boolean dyslipemy;
	@Column(name = "creatinine")
	private Float creatinine;
	@Column(name = "glomerular")
	private Float glomerular;
	@Column(name = "kidneyInsufficiency")
	private Boolean kidneyInsufficiency;
	@Column(name = "fototype")
	private String fototype;
	@Column(name = "diabetesTreatment")
	private Boolean diabetesTreatment;
	@Column(name = "vitaminDSupplementation")
	private Boolean vitaminDSupplementation;
}
