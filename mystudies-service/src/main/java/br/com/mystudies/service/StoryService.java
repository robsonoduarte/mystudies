package br.com.mystudies.service;

import java.util.List;

import br.com.mystudies.domain.entity.Story;
import br.com.mystudies.domain.enun.StoryStatus;

public interface StoryService {

	Story getStory(Long id);

	Story updateStatusStory(Long storyID, StoryStatus storyStatus);

	List<Story> getStories(StoryStatus storyStatus);

}
