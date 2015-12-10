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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import org.bson.types.ObjectId;

import br.com.mystudies.domain.enun.Priority;


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


	@OneToMany(mappedBy="theme", cascade = ALL, fetch=FetchType.EAGER)
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


	
	
	
	
	
	
	
	public br.com.mystudies.domain.entity.mongo.Theme tomongo(){
		br.com.mystudies.domain.entity.mongo.Theme theme = new br.com.mystudies.domain.entity.mongo.Theme();
		
		theme.creationDate = new Date(creationDate.getTime());
		theme.priority = priority;
		theme.title = title;
		
		stories.forEach( s -> {
			br.com.mystudies.domain.entity.mongo.Story story = new br.com.mystudies.domain.entity.mongo.Story();
			story.creationDate = new Date(s.getCreationDate().getTime());
			story.points = s.getPoints();
			story.priority = s.getPriority();
			story.status = s.getStatus();
			story.title = s.getTitle();
			
			theme.addStory(story);
		});
		
		
		
		
		return theme;
		
	}
	
	
	
	
	
	
	
}
