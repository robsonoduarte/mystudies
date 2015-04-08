/**
 *
 */
package br.com.mystudies.service.bean;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

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
		
		when(repository.save(backLog)).thenReturn(backLog);
		backLog = backLogService.addTheme(backLog, new Theme());
		assertThat(backLog.getThemes(), hasSize(1));
		verify(repository).save(backLog);
	}

	
	
	@Test
	public void shouldFindBackLogUsingTheRepositoryFindMethod() {
		when(repository.find(BackLog.class, 1L)).thenReturn(new BackLog());		
		assertThat(backLogService.getBackLog(1L), notNullValue());
		verify(repository).find(BackLog.class, 1L);
	}
	
}
