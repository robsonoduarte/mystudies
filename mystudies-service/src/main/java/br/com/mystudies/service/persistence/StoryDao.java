package br.com.mystudies.service.persistence;

import java.util.List;

import br.com.mystudies.domain.entity.Story;
import br.com.mystudies.domain.enun.StoryStatus;

public interface StoryDao {

	Story getStory(Long id);

	Story update(Story story);

	List<Story> getStoriesByStatus(StoryStatus storyStatus);

}
