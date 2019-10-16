package com.example.tfgestudiomedico2019.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subject")
public class SubjectEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "identificationnumber")
	private Integer identificationNumber;
	
	
	@OneToOne ()
	@JoinColumn(name="idresearcher")
	private UserEntity idResearcher;
	
	
	
	@OneToMany(mappedBy = "subject")
	private List<InvestigationEntity> investigations = new ArrayList<>();
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdentificationNumber() {
		return identificationNumber;
	}
	
	public void setIdentificationNumber(Integer identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public UserEntity getIdResearcher() {
		return idResearcher;
	}

	public void setIdResearcher(UserEntity idResearcher) {
		this.idResearcher = idResearcher;
	}

	public List<InvestigationEntity> getInvestigations() {
		return investigations;
	}
	
	public void setInvestigations(List<InvestigationEntity> investigations) {
		this.investigations = investigations;
	}

	@Override
	public String toString() {
		return "SubjectEntity [id=" + id + ", identificationNumber=" + identificationNumber + ", idResearcher="
				+ idResearcher + "]";
	}
}
