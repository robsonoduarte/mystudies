/**
 *
 */
package br.com.mystudies.service.bean;

import static br.com.mystudies.domain.enun.Priority.HEIGHT;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.mystudies.domain.entity.BackLog;
import br.com.mystudies.domain.entity.Theme;
import br.com.mystudies.service.BackLogService;
import br.com.mystudies.service.persistence.Repository;

/**
 * unit test to {@link BackLogServiceBean}
 *
 * @author Robson
 *
 */
public class BackLogServiceBeanTest {

	
	@InjectMocks
	private BackLogService backLogService;

	
	@Mock
	private Repository repository;

	

	@Before
	public void setUp() throws Exception {
		backLogService = new BackLogServiceBean();
		initMocks(this);
	}



	@Test
	public void shouldAddThemeInBackLog() {

		BackLog backLog = new BackLog();
		Theme theme = new Theme();

		when(repository.save(backLog)).thenReturn(backLog);

		backLog = backLogService.addTheme(backLog, theme);

		verify(repository).save(backLog);

		assertTrue(backLog.getThemes().contains(theme));
		assertEquals(theme.getBackLog(), backLog);

	}

	
	
	@Test
	public void shouldListThemesInBackLog() {

		BackLog backLog = new BackLog();
		backLog.addTheme(new Theme("theme1",HEIGHT,new Date()));
		backLog.addTheme(new Theme("theme2",HEIGHT,new Date()));
		backLog.addTheme(new Theme("theme3",HEIGHT,new Date()));


		when(repository.find(BackLog.class, 1L)).thenReturn(backLog);

		backLog = backLogService.getBackLog(1L);

		verify(repository).find(BackLog.class, 1L);

		assertNotNull(backLog.getThemes().size());
		assertEquals(3, backLog.getThemes().size());
	}

	
}
