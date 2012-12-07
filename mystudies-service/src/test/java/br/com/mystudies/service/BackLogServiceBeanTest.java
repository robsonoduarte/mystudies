/**
 *
 */
package br.com.mystudies.service;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.mystudies.domain.entity.BackLog;
import br.com.mystudies.domain.entity.Story;
import br.com.mystudies.domain.entity.Theme;
import br.com.mystudies.domain.enun.Priority;
import br.com.mystudies.service.persistence.BackLogDAO;

/**
 * unit test to {@link BackLogServiceBean}
 *
 * @author Robson
 *
 */
public class BackLogServiceBeanTest {


	@InjectMocks
	private BackLogServiceBean backLogServicebean;


	@Mock
	private BackLogDAO backlogDAO;


	@Before
	public void setUp() throws Exception {
		backLogServicebean = new BackLogServiceBean();
		MockitoAnnotations.initMocks(this);
	}


	@After
	public void tearDown() throws Exception {
		backLogServicebean = null;
	}


	@Test
	public void shouldAddThemeInBackLog() {

		BackLog backLog = new BackLog();
		Theme theme = new Theme();

		when(backlogDAO.update(backLog)).thenReturn(backLog);

		backLog = backLogServicebean.addTheme(backLog, theme);

		verify(backlogDAO).update(backLog);

		assertTrue(backLog.getThemes().contains(theme));
		assertEquals(theme.getBackLog(), backLog);

	}

	@Test
	public void shouldListThemesInBackLog() {

		BackLog backLog = new BackLog();
		backLog.addTheme(new Theme("theme1",Priority.HEIGHT,new Date()));
		backLog.addTheme(new Theme("theme2",Priority.HEIGHT,new Date()));
		backLog.addTheme(new Theme("theme3",Priority.HEIGHT,new Date()));


		when(backlogDAO.find(1L)).thenReturn(backLog);

		backLog = backLogServicebean.getBackLog(1L);

		verify(backlogDAO).find(1L);

		assertNotNull(backLog.getThemes().size());
		assertEquals(3, backLog.getThemes().size());
	}

}
