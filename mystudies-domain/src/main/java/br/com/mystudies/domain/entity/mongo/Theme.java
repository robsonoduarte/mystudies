package br.com.mystudies.domain.entity.mongo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import br.com.mystudies.domain.enun.Priority;


@Entity("themes")
public class Theme{


	@Id
	public ObjectId id;
	public String title;
	public Priority priority;
	public Date creationDate;
	public Set<Story> stories = new HashSet<>();


	
	public void addStory(Story story){
		stories.add(story);
	}
	
	
	
	
	
}
