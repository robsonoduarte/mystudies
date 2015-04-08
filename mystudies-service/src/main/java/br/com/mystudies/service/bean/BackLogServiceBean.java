package br.com.mystudies.service.bean;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.mystudies.domain.entity.BackLog;
import br.com.mystudies.domain.entity.Theme;
import br.com.mystudies.service.BackLogService;
import br.com.mystudies.service.persistence.Repository;

/**
 * Implementation to {@link BackLogService} using technology EJB 3.1
 *
 * @author Robson
 *
 *
 */
@Stateless
@Remote(BackLogService.class)
public class BackLogServiceBean implements BackLogService {


	@EJB
	private Repository repository;


	
	@Override
	public BackLog addTheme(BackLog backLog, Theme theme) {
		backLog.addTheme(theme); 		// FIXME: validation with beans validation !! technical debt !
		return repository.save(backLog);
	}


	
	@Override
	public BackLog getBackLog(long id) {
		return repository.find(BackLog.class, id);
	}

}
