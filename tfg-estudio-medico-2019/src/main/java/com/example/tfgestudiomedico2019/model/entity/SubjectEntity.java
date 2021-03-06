package com.example.tfgestudiomedico2019.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * Entity for subject table.
 */
@Entity
@Table(name = "subject")
public class SubjectEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "identificationnumber")
	private String identificationNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idresearcher")
	private UserEntity researcher;
	
	@OneToMany(mappedBy = "subject", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<InvestigationEntity> investigations = new ArrayList<>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIdentificationNumber() {
		return identificationNumber;
	}
	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}
	public UserEntity getIdResearcher() {
		return researcher;
	}
	public void setIdResearcher(UserEntity researcher) {
		this.researcher = researcher;
	}
	public List<InvestigationEntity> getInvestigations() {
		return investigations;
	}
	public void setInvestigations(List<InvestigationEntity> investigations) {
		this.investigations = investigations;
	}
	
	@Override
	public String toString() {
		return "SubjectEntity [id=" + id + ", identificationNumber=" + identificationNumber + ", researcher="
				+ researcher + "]";
	}
}
