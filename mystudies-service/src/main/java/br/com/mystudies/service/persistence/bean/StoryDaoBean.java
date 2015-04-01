package br.com.mystudies.service.persistence.bean;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.mystudies.domain.entity.Story;
import br.com.mystudies.domain.enun.StoryStatus;
import br.com.mystudies.service.persistence.StoryDao;

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


	@Override
	public List<Story> getStoriesByStatus(StoryStatus storyStatus) {


		CriteriaBuilder criteriaBuilder =
				entityManager.getCriteriaBuilder();


		CriteriaQuery<Story> criteriaQuery =
				criteriaBuilder.createQuery(Story.class);


		Root<Story> root =
				criteriaQuery.from(Story.class);


		criteriaQuery.select(root)
			.where(criteriaBuilder.equal(root.get("status"), storyStatus));

		return entityManager
				.createQuery(criteriaQuery)
				.getResultList();


	}

}
