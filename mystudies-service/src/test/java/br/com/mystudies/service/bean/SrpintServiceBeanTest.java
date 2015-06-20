package br.com.mystudies.service.bean;

import static br.com.mystudies.domain.enun.SprintStatus.RUNNING;
import static br.com.mystudies.domain.enun.StoryStatus.BACKLOG;
import static br.com.mystudies.domain.enun.StoryStatus.TODO;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.mystudies.domain.entity.Sprint;
import br.com.mystudies.domain.entity.Story;
import br.com.r3wa.fiscalpackage.persistence.Repository;

public class SrpintServiceBeanTest {

	@InjectMocks
	private SprintServiceBean sprintServiceBean;


	@Mock
	private Repository repository;


	@Before
	public void setUp() throws Exception {
		sprintServiceBean = new SprintServiceBean();
		initMocks(this);
	}


	@After
	public void tearDown() throws Exception {
		sprintServiceBean = null;
	}


	@Test
	public void shouldReturnTrueWhileContainsSprintInRun() {
		when(repository.select("select-sprint-by-status", RUNNING)).thenReturn(asList(new Sprint()));
		assertThat(sprintServiceBean.containsSprintInRun(), not(false));
		verify(repository).select("select-sprint-by-status", RUNNING);
	}

	


	@Test
	public void shouldReturnFalseWhileDontContainsSprintInRun() {
		when(repository.select("select-sprint-by-status", RUNNING)).thenReturn(null);
		assertThat(sprintServiceBean.containsSprintInRun(), not(true));
		verify(repository).select("select-sprint-by-status", RUNNING);
	}




	@Test(expected=IllegalStateException.class)
	public void shouldThrowExceptionWhenContainsSprintInRunning() {
		when(repository.select("select-sprint-by-status", RUNNING)).thenReturn(asList(new Sprint()));
		sprintServiceBean.create(new Sprint());
	}

	
	

	@Test()
	public void shouldCreateSprintWhenHaventSprintInRunning() {

		Sprint sprint = new Sprint(new Date(),new Date(),RUNNING);
		
		when(repository.select("select-sprint-by-status", RUNNING)).thenReturn(null);
		when(repository.save(any())).thenReturn(sprint);

		assertThat(sprintServiceBean.create(sprint), notNullValue());
		
		verify(repository).select("select-sprint-by-status", RUNNING);
		verify(repository).save(sprint);
	}

	
	
	@Test()
	public void shouldReturnNullWhenHaventSprintInRunning() {		
		when(repository.select("select-sprint-by-status", RUNNING)).thenReturn(null);
		assertThat(sprintServiceBean.getCurrentSprint(), not(notNullValue()));
		verify(repository).select("select-sprint-by-status", RUNNING);
	}

	
	

	@Test()
	public void shouldReturnNullWhenHaveSprintInRunning() {
		when(repository.select("select-sprint-by-status", RUNNING)).thenReturn(asList(new Sprint()));
		assertThat(sprintServiceBean.getCurrentSprint(), notNullValue());
		verify(repository).select("select-sprint-by-status", RUNNING);
	}


	
	@Test(expected=IllegalStateException.class)
	public void shouldThrowAExceptionWhenHaventSprintInRunning() {
		when(repository.select("select-sprint-by-status", RUNNING)).thenReturn(null);
		sprintServiceBean.addStoryInSprint(new Story());
	}

	
	
	@Test()
	public void shouldReturnSprintWithStoryWithStatusInSprint() {

		Sprint sprint = new Sprint();
		sprint.setEstimatedPoints(new Long(3));

		Story story = new Story();
		story.setStatus(BACKLOG);
		story.setPoints(3);
		
		when(repository.select("select-sprint-by-status", RUNNING)).thenReturn(asList(sprint));
		when(repository.save(sprint)).thenReturn(sprint);

		sprint = sprintServiceBean.addStoryInSprint(story);

		verify(repository).select("select-sprint-by-status", RUNNING);
		verify(repository).save(sprint);

		story = sprint.getStories().iterator().next();

		assertThat(story.getStatus(), equalTo(TODO));
		assertThat(story.getSprint(), equalTo(sprint));
		assertThat(sprint.getEstimatedPoints(), equalTo(6L));
	}

	
	
	
	@Test()
	public void shouldReturnListAllSprint() {
		when(repository.select("select-all-sprint")).thenReturn(asList(new Sprint()));

		List<Sprint> sprints =
				sprintServiceBean.getAllSprints();
		
		verify(repository).select("select-all-sprint");

		assertThat(sprints, hasSize(1));
	}


}
