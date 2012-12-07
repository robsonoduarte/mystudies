package br.com.mystudies.domain.entity;

import java.util.Date;
import java.util.Set;

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
public class Sprint implements EntityBase {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="ESTIMATED_POINTS")
	private Long estimatedPoints;

	@Column(name="DONE_POINTS")
	private Long donePoints;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(name="FINAL_DATE")
	private Date finalDate;

	@Enumerated(EnumType.STRING)
	@Column(name="SPRINT_STATUS")
	private SprintStatus sprintStatus;

	@OneToMany(mappedBy="sprint", cascade = CascadeType.ALL, fetch=FetchType.EAGER) // FIXME: can't eager !!
	private Set<Story> stories;


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


	public Set<Story> getStories() {
		return stories;
	}


	public void setStories(Set<Story> stories) {
		this.stories = stories;
	}


}
