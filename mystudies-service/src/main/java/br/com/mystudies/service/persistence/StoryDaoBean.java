package br.com.mystudies.service.persistence;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.mystudies.domain.entity.Story;

@Stateless
@Local(StoryDao.class)
public class StoryDaoBean implements StoryDao {


	@PersistenceContext
	private EntityManager entityManager;


	@Override
	public Story getStory(Long id) {
		return entityManager.find(Story.class, id);
	}


	@Override
	public Story update(Story story) {
		return entityManager.merge(story);
	}

}
