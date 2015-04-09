package br.com.mystudies.service.bean;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.mystudies.domain.entity.Story;
import br.com.mystudies.domain.entity.Theme;
import br.com.mystudies.service.persistence.Repository;

public class ThemeServiceBeanTest {


	@InjectMocks
	private ThemeServiceBean themeServiceBean;

	@Mock
	private Repository repository;

	
	@Before
	public void setUp() throws Exception {
		themeServiceBean = new ThemeServiceBean();
		initMocks(this);
	}


	
	@Test
	public void shouldGetThemeByID() {
		when(repository.find(Theme.class, 1L)).thenReturn(new Theme());
		assertThat(themeServiceBean.getTheme(1L), notNullValue());
		verify(repository).find(Theme.class, 1L);
	}
	
	
	
	
	
	
	@Test
	public void shouldAddStoryInTheme() {
			
		Theme theme = new Theme();
		
		when(repository.save(any())).thenReturn(theme);
		
		theme = themeServiceBean.addStory(theme, new Story());
		assertThat(theme.getStories(), hasSize(1)); 
		
		verify(repository).save(any());
		
		
	}
	
}
