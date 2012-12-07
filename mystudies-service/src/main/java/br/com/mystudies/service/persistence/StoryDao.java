package br.com.mystudies.service.persistence;

import br.com.mystudies.domain.entity.Story;

public interface StoryDao {

	Story getStory(Long id);

	Story update(Story story);

}
