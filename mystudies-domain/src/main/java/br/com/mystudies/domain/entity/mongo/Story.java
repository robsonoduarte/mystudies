package br.com.mystudies.domain.entity.mongo;

import java.util.Date;

import br.com.mystudies.domain.enun.Priority;
import br.com.mystudies.domain.enun.StoryStatus;


public class Story{

	
	public String title;
	public Priority priority;
	public StoryStatus status;
	public Date creationDate;
	public Integer points;


}
