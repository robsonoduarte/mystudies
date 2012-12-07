package br.com.mystudies.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.mystudies.domain.entity.Story;
import br.com.mystudies.domain.enun.StoryStatus;
import br.com.mystudies.service.persistence.StoryDao;

public class StoryServiceBeanTest {


    @InjectMocks
    private StoryServiceBean storyServiceBean;


    @Mock
    private StoryDao storyDao;


    @Before
    public void setUp() throws Exception {
        storyServiceBean = new StoryServiceBean();
        initMocks(this);
    }


    @After
    public void tearDown() throws Exception {
        storyServiceBean = null;
    }


    @Test
    public void shouldGetThemeByID() {

        when(storyDao.getStory(any(Long.class))).thenReturn(new Story());

        Story story = storyServiceBean.getStory(1L);

        verify(storyDao).getStory(1L);

        assertNotNull(story);
    }


    @Test
    public void shouldUpdateStatusStory() {

    	Story story =
    			new Story(null,null, StoryStatus.TODO, null, null);

    	when(storyDao.getStory(any(Long.class))).thenReturn(story);
    	when(storyDao.update(story)).thenReturn(story);


         story = storyServiceBean.updateStatusStory(1L, StoryStatus.DOING);


        verify(storyDao).getStory(1L);
        verify(storyDao).update(story);


        assertEquals(StoryStatus.DOING, story.getStatus());



/*		when(storyDao.getStory(any(Long.class))).thenReturn(new Story());

        Story story = storyServiceBean.getStory(1L);

        verify(storyDao).getStory(1L);

        assertNotNull(story);*/
    }



}
