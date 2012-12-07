package br.com.mystudies.service;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.mystudies.domain.entity.BackLog;
import br.com.mystudies.domain.entity.Story;
import br.com.mystudies.domain.entity.Theme;
import br.com.mystudies.service.persistence.BackLogDAO;

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
	private BackLogDAO backLogDAO;


	@Override
	public BackLog addTheme(BackLog backLog, Theme theme) {
		// FIXME: validation with beans validation !! technical debt !
		backLog.addTheme(theme);
		return backLogDAO.update(backLog);
	}


	@Override
	public BackLog getBackLog(long id) {
		// FIXME: validate the parameters
		return backLogDAO.find(id);
	}

}
