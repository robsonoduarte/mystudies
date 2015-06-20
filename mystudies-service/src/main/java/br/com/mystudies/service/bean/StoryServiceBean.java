package br.com.mystudies.service.bean;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

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
		// FIXME: validate parameters
		List<Story> storyies = repository.select("select-story-by-id", id);
		return isNotEmpty(storyies) ? storyies.get(0) : null ;
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
		return repository.select("select-story-by-status", storyStatus);
	}


}
