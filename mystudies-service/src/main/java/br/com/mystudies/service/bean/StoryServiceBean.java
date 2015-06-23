package br.com.mystudies.service.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.mystudies.domain.entity.Story;
import br.com.mystudies.domain.enun.StoryStatus;
import br.com.mystudies.service.StoryService;
import br.com.r3wa.fiscalpackage.persistence.Repository;

@Stateless
@Local(StoryService.class)
public class StoryServiceBean implements StoryService {


	@EJB
	private Repository repository;



	@Override
	public Story getStory(Long id) {
		return repository.selectOne("story-by-id", id) ;
	}



	@Override
	public Story updateStatusStory(Long id, StoryStatus status) {
		// FIXME: validate parameters
		Story story = getStory(id);
		story.setStatus(status);
		return repository.save(story);
	}



	@Override
	public List<Story> getStories(StoryStatus storyStatus) {
		// FIXME: validate parameters
		return repository.select("story-by-status", storyStatus);
	}


}
