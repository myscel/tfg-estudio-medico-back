package com.example.tfgestudiomedico2019.model.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity for investigation_details table.
 */
@Entity
@Table(name = "investigation_details")
public class InvestigationEntityDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "vitamind")
	private Float vitaminD;
	@Column(name = "hba1c")
	private Float hba1c;
	@Column(name = "season")
	private String season;
	
	@Column(name = "gender")
	private String gender;
	@Column(name = "studylevel")
	private String studyLevel;
	@Column(name = "birthdate")
	private Date birthDate;
	@Column(name = "socioeconomiclevel")
	private String socioeconomicLevel;

	@Column(name = "tobacco")
	private Boolean tobacco;
	@Column(name = "riskAlcohol")
	private Boolean riskAlcohol;
	@Column(name = "solarexposure")
	private Float solarExposure;
	@Column(name = "spfcream")
	private Boolean spfCream;
	@Column(name = "spfscore")
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
	@Column(name = "arterialhypertension")
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
	@Column(name = "kidneyinsufficiency")
	private Boolean kidneyInsufficiency;
	@Column(name = "fototype")
	private String fototype;
	@Column(name = "diabetestreatment")
	private Boolean diabetesTreatment;
	@Column(name = "vitamindsupplementation")
	private Boolean vitaminDSupplementation;
	@Column(name = "investigationdate")
	private Date investigationDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
		return riskAlcohol;
	}
	public void setRiskalcohol(Boolean riskAlcohol) {
		this.riskAlcohol = riskAlcohol;
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
	public Date getInvestigationDate() {
		return investigationDate;
	}
	public void setInvestigationDate(Date investigationDate) {
		this.investigationDate = investigationDate;
	}
	
	@Override
	public String toString() {
		return "InvestigationEntityDetails [id=" + id + ", vitaminD=" + vitaminD + ", hba1c=" + hba1c + ", season="
				+ season + ", gender=" + gender + ", studyLevel=" + studyLevel + ", birthDate=" + birthDate
				+ ", socioeconomicLevel=" + socioeconomicLevel + ", tobacco=" + tobacco + ", riskAlcohol=" + riskAlcohol
				+ ", solarExposure=" + solarExposure + ", spfCream=" + spfCream + ", spfScore=" + spfScore
				+ ", exercise=" + exercise + ", dm2=" + dm2 + ", glucose=" + glucose + ", imc=" + imc + ", obesity="
				+ obesity + ", tas=" + tas + ", tad=" + tad + ", arterialHypertension=" + arterialHypertension
				+ ", cholesterol=" + cholesterol + ", ldl=" + ldl + ", hdl=" + hdl + ", tg=" + tg + ", dyslipemy="
				+ dyslipemy + ", creatinine=" + creatinine + ", glomerular=" + glomerular + ", kidneyInsufficiency="
				+ kidneyInsufficiency + ", fototype=" + fototype + ", diabetesTreatment=" + diabetesTreatment
				+ ", vitaminDSupplementation=" + vitaminDSupplementation + ", investigationDate=" + investigationDate
				+ "]";
	}
}
