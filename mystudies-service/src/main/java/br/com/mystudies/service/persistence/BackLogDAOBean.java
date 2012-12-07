package br.com.mystudies.service.persistence;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.mystudies.domain.entity.BackLog;

/**
 * Implementation to {@link BackLogDAO} using technology EJB 3.1
 *
 * @author Robson
 *
 */
@Stateless
@Local(BackLogDAO.class)
public class BackLogDAOBean  implements BackLogDAO{

	@PersistenceContext
	private EntityManager entityManager;


	@Override
	public BackLog update(BackLog backLog) {

		// FIXME: EXTREMELY TEMPORARY BECAUSE I'VE TECHNICAL DEBT ABOUT UNIT TEST WITH PERSISTENCE CLASS.

		return entityManager.merge(backLog);
	}


	@Override
	public BackLog find(long id) {
		// FIXME: EXTREMELY TEMPORARY BECAUSE I'VE TECHNICAL DEBT ABOUT UNIT TEST WITH PERSISTENCE CLASS.
		return  entityManager.find(BackLog.class, id);
	}

}
