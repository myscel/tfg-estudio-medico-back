package com.example.tfgestudiomedico2019.model.rest.investigation;

import java.sql.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * Investigation details dto with all the information to register an investigation detail.
 *
 */
public class InvestigationDetailsToRegisterDto {
	@ApiModelProperty(value = "The vitaminD  of the subject", example = "100", dataType = "java.lang.Float")
	private Float vitaminD;
	@ApiModelProperty(value = "The hba1c % of the subject", example = "23", dataType = "java.lang.Float")
	private Float hba1c;
	@ApiModelProperty(value = "The season  of the year at this moment", allowableValues="verano, invierno", example = "verano", dataType = "java.lang.Float")
	private String season;
	
	
	@ApiModelProperty(value = "The gender of the subject", example = "mujer", allowableValues="hombre, mujer", dataType = "java.lang.String")
	private String gender;
	@ApiModelProperty(value = "The study level  of the subject", example = "Primaria", dataType = "java.lang.String")
	private String studyLevel;
	@ApiModelProperty(value = "The birthDate  of the subject", example = "1995-08-06", dataType = "Date")
	private Date birthDate;
	@ApiModelProperty(value = "The socioeconomic level in euros of the subject per month", example = "1001-200", dataType = "java.lang.String")
	private String socioeconomicLevel;

	@ApiModelProperty(value = "If the subject consumes tobacco", example = "true", dataType = "boolean")
	private Boolean tobacco;
	@ApiModelProperty(value = "If the subject has risk of drinking alcohol", example = "false", dataType = "boolean")
	private Boolean riskalcohol;
	@ApiModelProperty(value = "The solar exposure time per day of the subject in minutes", example = "500", dataType = "java.lang.Float")
	private Float solarExposure;
	@ApiModelProperty(value = "If the subject uses spfCream", example = "true", dataType = "boolean")
	private Boolean spfCream;
	@ApiModelProperty(value = "Spf score of the subject", example = "1", dataType = "java.lang.Integer")
	private Float spfScore;
	@ApiModelProperty(value = "The vitaminD  of the subject", example = "25", dataType = "java.lang.Float")
	private Float exercise;
	
	@ApiModelProperty(value = "If the subject has a right control of dm2", example = "true", dataType = "boolean")
	private Boolean dm2;
	@ApiModelProperty(value = "The glucose level in blood of the subject", example = "320", dataType = "java.lang.Float")
	private Float glucose;
	@ApiModelProperty(value = "The imc level in blood of the subject", example = "15", dataType = "java.lang.Float")
	private Float imc;
	@ApiModelProperty(value = "If the subject has obesity", example = "false", dataType = "boolean")
	private Boolean obesity;
	@ApiModelProperty(value = "The tas level in blood of the subject", example = "30", dataType = "java.lang.Float")
	private Float tas;
	@ApiModelProperty(value = "The tad level in blood of the subject", example = "40", dataType = "java.lang.Float")
	private Float tad;
	@ApiModelProperty(value = "If the subject has arterial hypertension", example = "true", dataType = "boolean")
	private Boolean arterialHypertension;
	@ApiModelProperty(value = "The cholesterol level in blood of the subject", example = "800", dataType = "java.lang.Float")
	private Float cholesterol;
	@ApiModelProperty(value = "The ldl level in blood of the subject", example = "300", dataType = "java.lang.Float")
	private Float ldl;
	@ApiModelProperty(value = "The hdl level in blood of the subject", example = "200", dataType = "java.lang.Float")
	private Float hdl;
	@ApiModelProperty(value = "The tg level in blood of the subject", example = "2000", dataType = "java.lang.Float")
	private Float tg;
	@ApiModelProperty(value = "If the subject has dyslipemy", example = "false", dataType = "boolean")
	private Boolean dyslipemy;
	@ApiModelProperty(value = "The creatinine level in blood of the subject", example = "15", dataType = "java.lang.Float")
	private Float creatinine;
	@ApiModelProperty(value = "The glomerular level in blood of the subject", example = "150", dataType = "java.lang.Float")
	private Float glomerular;
	@ApiModelProperty(value = "If the subject has kidney insufficiency", example = "false", dataType = "boolean")
	private Boolean kidneyInsufficiency;
	@ApiModelProperty(value = "The fototype of the subject", example = "II", dataType = "java.lang.String")
	private String fototype;
	@ApiModelProperty(value = "If the subject has a diabetes treatment", example = "true", dataType = "boolean")
	private Boolean diabetesTreatment;
	@ApiModelProperty(value = "If the subject consumes a vitamin D supplementation", example = "false", dataType = "boolean")
	private Boolean vitaminDSupplementation;
	
	
	@ApiModelProperty(value = "The investigation number", example = "2", dataType = "java.lang.Integer")
	private Integer numberInvestigation;
	@ApiModelProperty(value = "The id of the subject", example = "23", dataType = "java.lang.Integer")
	private Integer idSubject;
	@ApiModelProperty(value = "The investigation date of the appointment", example = "2020-22-01", dataType = "Date")
	private Date investigationDate;


	public Float getVitaminD() {
		return vitaminD;
	}
	public void setVitaminD(Float vitaminD) {
		this.vitaminD = vitaminD;
	}
	public Float getHba1c() {
		return hba1c;
	}
	public void setHba1c(Float hba1c) {
		this.hba1c = hba1c;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getStudyLevel() {
		return studyLevel;
	}
	public void setStudyLevel(String studyLevel) {
		this.studyLevel = studyLevel;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getSocioeconomicLevel() {
		return socioeconomicLevel;
	}
	public void setSocioeconomicLevel(String socioeconomicLevel) {
		this.socioeconomicLevel = socioeconomicLevel;
	}
	public Boolean getTobacco() {
		return tobacco;
	}
	public void setTobacco(Boolean tobacco) {
		this.tobacco = tobacco;
	}
	public Boolean getRiskalcohol() {
		return riskalcohol;
	}
	public void setRiskalcohol(Boolean riskalcohol) {
		this.riskalcohol = riskalcohol;
	}
	public Float getSolarExposure() {
		return solarExposure;
	}
	public void setSolarExposure(Float solarExposure) {
		this.solarExposure = solarExposure;
	}
	public Boolean getSpfCream() {
		return spfCream;
	}
	public void setSpfCream(Boolean spfCream) {
		this.spfCream = spfCream;
	}
	public Float getSpfScore() {
		return spfScore;
	}
	public void setSpfScore(Float spfScore) {
		this.spfScore = spfScore;
	}
	public Float getExercise() {
		return exercise;
	}
	public void setExercise(Float exercise) {
		this.exercise = exercise;
	}
	public Boolean getDm2() {
		return dm2;
	}
	public void setDm2(Boolean dm2) {
		this.dm2 = dm2;
	}
	public Float getGlucose() {
		return glucose;
	}
	public void setGlucose(Float glucose) {
		this.glucose = glucose;
	}
	public Float getImc() {
		return imc;
	}
	public void setImc(Float imc) {
		this.imc = imc;
	}
	public Boolean getObesity() {
		return obesity;
	}
	public void setObesity(Boolean obesity) {
		this.obesity = obesity;
	}
	public Float getTas() {
		return tas;
	}
	public void setTas(Float tas) {
		this.tas = tas;
	}
	public Float getTad() {
		return tad;
	}
	public void setTad(Float tad) {
		this.tad = tad;
	}
	public Boolean getArterialHypertension() {
		return arterialHypertension;
	}
	public void setArterialHypertension(Boolean arterialHypertension) {
		this.arterialHypertension = arterialHypertension;
	}
	public Float getCholesterol() {
		return cholesterol;
	}
	public void setCholesterol(Float cholesterol) {
		this.cholesterol = cholesterol;
	}
	public Float getLdl() {
		return ldl;
	}
	public void setLdl(Float ldl) {
		this.ldl = ldl;
	}
	public Float getHdl() {
		return hdl;
	}
	public void setHdl(Float hdl) {
		this.hdl = hdl;
	}
	public Float getTg() {
		return tg;
	}
	public void setTg(Float tg) {
		this.tg = tg;
	}
	public Boolean getDyslipemy() {
		return dyslipemy;
	}
	public void setDyslipemy(Boolean dyslipemy) {
		this.dyslipemy = dyslipemy;
	}
	public Float getCreatinine() {
		return creatinine;
	}
	public void setCreatinine(Float creatinine) {
		this.creatinine = creatinine;
	}
	public Float getGlomerular() {
		return glomerular;
	}
	public void setGlomerular(Float glomerular) {
		this.glomerular = glomerular;
	}
	public Boolean getKidneyInsufficiency() {
		return kidneyInsufficiency;
	}
	public void setKidneyInsufficiency(Boolean kidneyInsufficiency) {
		this.kidneyInsufficiency = kidneyInsufficiency;
	}
	public String getFototype() {
		return fototype;
	}
	public void setFototype(String fototype) {
		this.fototype = fototype;
	}
	public Boolean getDiabetesTreatment() {
		return diabetesTreatment;
	}
	public void setDiabetesTreatment(Boolean diabetesTreatment) {
		this.diabetesTreatment = diabetesTreatment;
	}
	public Boolean getVitaminDSupplementation() {
		return vitaminDSupplementation;
	}
	public void setVitaminDSupplementation(Boolean vitaminDSupplementation) {
		this.vitaminDSupplementation = vitaminDSupplementation;
	}
	public Integer getNumberInvestigation() {
		return numberInvestigation;
	}
	public void setNumberInvestigation(Integer numberInvestigation) {
		this.numberInvestigation = numberInvestigation;
	}
	public Integer getIdSubject() {
		return idSubject;
	}
	public void setIdSubject(Integer idSubject) {
		this.idSubject = idSubject;
	}
	public Date getInvestigationDate() {
		return investigationDate;
	}
	public void setInvestigationDate(Date investigationDate) {
		this.investigationDate = investigationDate;
	}

	@Override
	public String toString() {
		return "InvestigationDetailsToRegisterDto [vitaminD=" + vitaminD + ", hba1c=" + hba1c + ", season=" + season
				+ ", gender=" + gender + ", studyLevel=" + studyLevel + ", birthDate=" + birthDate
				+ ", socioeconomicLevel=" + socioeconomicLevel + ", tobacco=" + tobacco + ", riskAlcohol=" + riskalcohol
				+ ", solarExposure=" + solarExposure + ", spfCream=" + spfCream + ", spfScore=" + spfScore
				+ ", exercise=" + exercise + ", dm2=" + dm2 + ", glucose=" + glucose + ", imc=" + imc + ", obesity="
				+ obesity + ", tas=" + tas + ", tad=" + tad + ", arterialHypertension=" + arterialHypertension
				+ ", cholesterol=" + cholesterol + ", ldl=" + ldl + ", hdl=" + hdl + ", tg=" + tg + ", dyslipemy="
				+ dyslipemy + ", creatinine=" + creatinine + ", glomerular=" + glomerular + ", kidneyInsufficiency="
				+ kidneyInsufficiency + ", fototype=" + fototype + ", diabetesTreatment=" + diabetesTreatment
				+ ", vitaminDSupplementation=" + vitaminDSupplementation + ", numberInvestigation="
				+ numberInvestigation + ", idSubject=" + idSubject + ", investigationDate=" + investigationDate + "]";
	}
}
