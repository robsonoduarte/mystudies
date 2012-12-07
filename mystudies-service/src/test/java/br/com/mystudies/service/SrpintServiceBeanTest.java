package br.com.mystudies.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.mystudies.domain.entity.Sprint;
import br.com.mystudies.domain.entity.Story;
import br.com.mystudies.domain.enun.SprintStatus;
import br.com.mystudies.domain.enun.StoryStatus;
import br.com.mystudies.service.persistence.SprintDao;

public class SrpintServiceBeanTest {

	@InjectMocks
	private SprintServiceBean sprintServiceBean;


	@Mock
	private SprintDao sprintDao;


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

		when(sprintDao.findSprintByStatus(SprintStatus.RUNNING)).thenReturn(new Sprint());

		boolean containsSprintInRun = sprintServiceBean.containsSprintInRun();

		verify(sprintDao).findSprintByStatus(SprintStatus.RUNNING);

		assertTrue(containsSprintInRun);
	}



	@Test
	public void shouldReturnFalseWhileDontContainsSprintInRun() {

		when(sprintDao.findSprintByStatus(SprintStatus.RUNNING)).thenReturn(null);

		boolean containsSprintInRun = sprintServiceBean.containsSprintInRun();

		verify(sprintDao).findSprintByStatus(SprintStatus.RUNNING);

		assertFalse(containsSprintInRun);
	}



	@Test(expected=IllegalStateException.class)
	public void shouldThrowExceptionWhenContainsSprintInRunning() {

		when(sprintDao.findSprintByStatus(SprintStatus.RUNNING)).thenReturn(new Sprint());

		sprintServiceBean.create(new Sprint());

	}

	@Test()
	public void shouldCreateSprintWhenHaventSprintInRunning() {

		Sprint sprint = new Sprint(
				new Date(),
				new Date(),
				SprintStatus.RUNNING
				);

		when(sprintDao.findSprintByStatus(SprintStatus.RUNNING)).thenReturn(null);
		when(sprintDao.persist(any(Sprint.class) )).thenReturn(sprint);


		sprint = sprintServiceBean.create(sprint);

		verify(sprintDao).findSprintByStatus(SprintStatus.RUNNING);
		verify(sprintDao).persist(sprint);

		assertNotNull(sprint);
		assertEquals(SprintStatus.RUNNING, sprint.getSprintStatus());
		assertEquals(new Long(0), sprint.getEstimatedPoints());


	}


	@Test()
	public void shouldReturnNullWhenHaventSprintInRunning() {

		when(sprintDao.findSprintByStatus(SprintStatus.RUNNING)).thenReturn(null);

		Sprint sprint = sprintServiceBean.getCurrentSprint();

		verify(sprintDao).findSprintByStatus(SprintStatus.RUNNING);

		assertNull(sprint);

	}


	@Test()
	public void shouldReturnNullWhenHaveSprintInRunning() {

		when(sprintDao.findSprintByStatus(SprintStatus.RUNNING)).thenReturn(new Sprint());

		Sprint sprint = sprintServiceBean.getCurrentSprint();

		verify(sprintDao).findSprintByStatus(SprintStatus.RUNNING);

		assertNotNull(sprint);
	}


	@Test(expected=IllegalStateException.class)
	public void shouldThrowAExceptionWhenHaventSprintInRunning() {

		when(sprintDao.findSprintByStatus(SprintStatus.RUNNING)).thenReturn(null);

		sprintServiceBean.addStoryInSprint(new Story());

		verify(sprintDao).findSprintByStatus(SprintStatus.RUNNING);

	}


	@Test()
	public void shouldReturnSprintWithStoryWithStatusInSprint() {

		Sprint sprint = new Sprint();
		sprint.setStories(new HashSet<Story>());
		sprint.setEstimatedPoints(new Long(3));

		Story story = new Story();
		story.setStatus(StoryStatus.BACKLOG);
		story.setPoints(3);

		when(sprintDao.findSprintByStatus(SprintStatus.RUNNING)).thenReturn(sprint);
		when(sprintDao.update(sprint)).thenReturn(sprint);

		sprint = sprintServiceBean.addStoryInSprint(story);

		verify(sprintDao).findSprintByStatus(SprintStatus.RUNNING);
		verify(sprintDao).update(sprint);

		story = sprint.getStories().iterator().next();

		assertEquals(StoryStatus.TODO, story.getStatus());
		assertEquals(sprint, story.getSprint());
		assertEquals(new Long(6), sprint.getEstimatedPoints());

	}

	@Test()
	public void shouldReturnListAllSprint() {


		when(sprintDao.listAll()).thenReturn(new ArrayList<Sprint>());

		List<Sprint> sprints =
				sprintServiceBean.getAllSprints();

		verify(sprintDao).listAll();

		assertNotNull(sprints);

	}


}
