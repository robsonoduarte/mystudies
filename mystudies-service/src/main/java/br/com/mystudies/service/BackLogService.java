package br.com.mystudies.service;

import br.com.mystudies.domain.entity.BackLog;
import br.com.mystudies.domain.entity.Story;
import br.com.mystudies.domain.entity.Theme;

/**
 *
 * Service with operations to work with {@link BackLog}
 *
 * @author Robson
 */
public interface BackLogService {

	/**
	 * Add the {@link Theme} in {@link BackLog}.
	 *
	 * @param backLog - where {@link Theme} will added.
	 * @param theme - to add in {@link BackLog}.
	 * @return {@link BackLog} with {@link Theme} added.
	 */
	BackLog addTheme(BackLog backLog, Theme theme);
	
	
	/**
	 * return the {@link BackLog} by id.
	 *
	 * @param id - the id to find.
	 * @return - {@link BackLog} found with id;
	 */
	BackLog getBackLog(long id);




	
}