package br.com.mystudies.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.mystudies.domain.enun.SprintStatus;

@Entity
@Table(name="SPRINT")
public class Sprint extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


	@Column(name="ESTIMATED_POINTS")
	private Long estimatedPoints = new Long(0); // default points

	@Column(name="DONE_POINTS")
	private Long donePoints;

	@Column(name="START_DATE")
	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Column(name="FINAL_DATE")
	@Temporal(TemporalType.DATE)
	private Date finalDate;


	@Column(name="SPRINT_STATUS")
	@Enumerated(EnumType.STRING)
	private SprintStatus sprintStatus;


	@OneToMany(mappedBy="sprint", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Story> stories = new ArrayList<>() ;



	public Sprint() {
	}


	public Sprint(Date startDate, Date finalDate, SprintStatus sprintStatus) {
		super();
		this.startDate = startDate;
		this.finalDate = finalDate;
		this.sprintStatus = sprintStatus;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getEstimatedPoints() {
		return estimatedPoints;
	}


	public void setEstimatedPoints(Long estimatedPoints) {
		this.estimatedPoints = estimatedPoints;
	}


	public Long getDonePoints() {
		return donePoints;
	}


	public void setDonePoints(Long donePoints) {
		this.donePoints = donePoints;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getFinalDate() {
		return finalDate;
	}


	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}


	public SprintStatus getSprintStatus() {
		return sprintStatus;
	}


	public void setSprintStatus(SprintStatus sprintStatus) {
		this.sprintStatus = sprintStatus;
	}


	public List<Story> getStories() {
		return stories;
	}


	public void setStories(List<Story> stories) {
		this.stories = stories;
	}





}
