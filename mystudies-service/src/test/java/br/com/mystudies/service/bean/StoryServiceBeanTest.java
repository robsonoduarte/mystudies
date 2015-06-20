package br.com.mystudies.service.bean;

import static br.com.mystudies.domain.enun.StoryStatus.BACKLOG;
import static br.com.mystudies.domain.enun.StoryStatus.DOING;
import static br.com.mystudies.domain.enun.StoryStatus.TODO;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.mystudies.domain.entity.Entity;
import br.com.mystudies.domain.entity.Story;
import br.com.mystudies.domain.enun.StoryStatus;
import br.com.r3wa.fiscalpackage.persistence.Repository;

public class StoryServiceBeanTest {


    @InjectMocks
    private StoryServiceBean storyServiceBean;


    @Mock
    private Repository repository;
    

    @Before
    public void setUp() throws Exception {
        storyServiceBean = new StoryServiceBean();
        initMocks(this);
    }



    @Test
    public void shouldGetThemeByID() {
    	when(repository.select("select-story-by-id", 1L)).thenReturn(asList(new Story()));    	
    	assertThat(storyServiceBean.getStory(1L), notNullValue());
    	verify(repository).select("select-story-by-id", 1L);
    }

    
    
   
    @Test
    public void shouldUpdateStatusStory() {
    	
    	Story story = new Story(null,null, TODO, null, null);
    	
    	when(repository.select("select-story-by-id", 1L)).thenReturn(asList(story));    	
    	when(repository.save(story)).thenReturn(new Story(null,null, DOING, null, null));
    	
        assertThat(storyServiceBean.updateStatusStory(1L, DOING).getStatus(), equalTo(DOING));
        
        verify(repository).select("select-story-by-id", 1L);
        verify(repository).save(story);
    }


    
    


    @Test
    public void shouldGetStoriesByStatus() {
    	when(repository.select("select-story-by-status", BACKLOG)).thenReturn(geStories());    	
    	assertThat(storyServiceBean.getStories(StoryStatus.BACKLOG), hasSize(6));
    	verify(repository).select("select-story-by-status", BACKLOG);
    }




























    // ->>>>>>>>>>>>>>> METHODS AUXILIARES <<<<<<<<<<<<<<<<<<-
	private List<Entity> geStories() {
		return asList(
				new Story(),
				new Story(),
				new Story(),
				new Story(),
				new Story(),
				new Story()
			);
	}


}
