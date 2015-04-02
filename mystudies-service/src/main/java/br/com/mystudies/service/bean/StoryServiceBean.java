package br.com.mystudies.service.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.mystudies.domain.entity.Story;
import br.com.mystudies.domain.enun.StoryStatus;
import br.com.mystudies.service.StoryService;
import br.com.mystudies.service.persistence.StoryDao;

@Stateless
@Remote(StoryService.class)
public class StoryServiceBean implements StoryService {

	@EJB
	private StoryDao storyDao;


	@Override
	public Story getStory(Long id) {
		// FIXME: validate parameters
		return storyDao.getStory(id);
	}


	@Override
	public Story updateStatusStory(Long storyID, StoryStatus storyStatus) {
		// FIXME: validate parameters

		Story story = getStory(storyID);


		story.setStatus(storyStatus);


		return storyDao.update(story);
	}


	@Override
	public List<Story> getStories(StoryStatus storyStatus) {
		// FIXME: validate parameters
		return storyDao.getStoriesByStatus(storyStatus);
	}


}
