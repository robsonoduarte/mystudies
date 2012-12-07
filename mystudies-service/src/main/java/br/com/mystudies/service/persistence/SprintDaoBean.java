package br.com.mystudies.service.persistence;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.mystudies.domain.entity.Sprint;
import br.com.mystudies.domain.enun.SprintStatus;

@Stateless
@Local(SprintDao.class)
public class SprintDaoBean implements SprintDao {


	@PersistenceContext
	private EntityManager entityManager;


	@Override
	public Sprint persist(Sprint sprint) {
		entityManager.persist(sprint);
		return sprint;
	}

	@Override
	public Sprint update(Sprint sprint) {
		return entityManager.merge(sprint);
	}


	@Override
	public Sprint findSprintByStatus(SprintStatus sprintStatus){ // FIXME: I think this should return a collection of sprint

		CriteriaBuilder criteriaBuilder =
				entityManager.getCriteriaBuilder();

		CriteriaQuery<Sprint> criteriaQuery =
				criteriaBuilder.createQuery(Sprint.class);


		Root<Sprint> root =
				criteriaQuery.from(Sprint.class);


		criteriaQuery
			.select(root)
			.where(criteriaBuilder.equal(root.get("sprintStatus"), sprintStatus));


		try {
			// FIXME:3 the same about fixme above.
			return entityManager
					.createQuery(criteriaQuery)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}




	@Override
	public List<Sprint> listAll() {

		CriteriaBuilder criteriaBuilder =
				entityManager.getCriteriaBuilder();

		CriteriaQuery<Sprint> criteriaQuery =
				criteriaBuilder.createQuery(Sprint.class);


		Root<Sprint> root =
				criteriaQuery.from(Sprint.class);


		criteriaQuery.select(root);

		return entityManager.createQuery(criteriaQuery).getResultList();
	}

}
