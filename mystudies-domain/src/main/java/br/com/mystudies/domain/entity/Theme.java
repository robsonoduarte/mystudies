package br.com.mystudies.domain.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.DATE;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Reference;

import br.com.mystudies.domain.enun.Priority;

@org.mongodb.morphia.annotations.Entity
@Entity
@Table(name="THEME")
public class Theme extends BaseEntity{


	private static final long serialVersionUID = 1L;


	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=IDENTITY)
	private Long id;

	@org.mongodb.morphia.annotations.Id
	private ObjectId ObjectId;
	

	@Column(name="TITLE")
	String title;


	@Column(name="PRIORITY")
	@Enumerated(STRING)
	Priority priority;

	@Column(name="CREATION_DATE")
	@Temporal(DATE)
	Date creationDate;

	@ManyToOne
	@JoinColumn(name="BACKLOG_ID")
	private BackLog backLog;


	@OneToMany(mappedBy="theme", cascade = ALL)
	@Reference
	private Set<Story> stories = new HashSet<>();



	@Transient // not implemented
	private List<Comment> comments;



	public Theme() {
	}


	public Theme(String name, Priority priority, Date creationDate) {
		super();
		this.title = name;
		this.priority = priority;
		this.creationDate = creationDate;
	}

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

/*	public BackLog getBackLog() {
		return backLog;
	}

	public void setBackLog(BackLog backLog) {
		this.backLog = backLog;
	}*/


	public Set<Story> getStories() {
		return stories;
	}

	public void setStories(Set<Story> stories) {
		this.stories = stories;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Theme other = (Theme) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (priority != other.priority)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
