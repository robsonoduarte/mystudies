package br.com.mystudies.service.schedule;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

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

public class ScheduleSprintTest {


	@Mock
	private SprintDao sprintDao;


	@InjectMocks
	private ScheduleSprint scheduleSprint;


	@Before
	public void setUp() throws Exception {
		scheduleSprint = new ScheduleSprint();
		initMocks(this);
	}


	@After
	public void tearDown() throws Exception {
	}


	/*
	 * 1 - scheduller sprint...
	 * 		1  - rodar todo dia a noite.. ( esse não testa... somente integraçao ??? infra...)
			2  - verificar se existe sprint rodando..
			3  - verficar data de ternimo do sprint
			4  - calcular se o sprint falhou ou não
			5  - calcular o numero de pontos feito.
			6  - historias em doing devem retorna para todo
	 */


	@Test
	public void shouldntDoNothingWhenFinalDateNotExpired() {

		Calendar calendar = Calendar.getInstance();
		calendar.roll(Calendar.DAY_OF_MONTH, 1); // rest one day for finish the sprint

		Sprint sprint = new Sprint();
		sprint.setFinalDate(calendar.getTime());
		sprint.setSprintStatus(SprintStatus.RUNNING);

		when(sprintDao.findSprintByStatus(SprintStatus.RUNNING)).thenReturn(sprint);

		scheduleSprint.execute();

		verify(sprintDao).findSprintByStatus(SprintStatus.RUNNING);

		assertEquals(SprintStatus.RUNNING, sprint.getSprintStatus());
	}


	@Test
	public void shouldThrowNPE() { // null pointer exception when sprint == null

		when(sprintDao.findSprintByStatus(SprintStatus.RUNNING)).thenReturn(null);

		scheduleSprint.execute();

		verify(sprintDao).findSprintByStatus(SprintStatus.RUNNING);
	}



	@Test
	public void shouldUpdateStatusSprintWhenSprintFailOne() { // sprint with stories in doing

		/*
		 *
		 * sprint fail = when final date expired and sprint've any story in doing.
		 *
		 * all story in status different of done should changing status to backlog.
		 *
		 */

		Calendar calendar = Calendar.getInstance();
		calendar.roll(Calendar.DAY_OF_MONTH, -1); // sprint finished

		Sprint sprint = new Sprint();
		sprint.setFinalDate(calendar.getTime());
		sprint.setSprintStatus(SprintStatus.RUNNING);


		sprint.setStories(new HashSet<Story>());
		sprint.getStories().add(new Story("STORY 1", null, StoryStatus.DONE, null, 10));
		sprint.getStories().add(new Story("STORY 2", null, StoryStatus.DONE, null, 10));
		sprint.getStories().add(new Story("STORY 3", null, StoryStatus.DOING, null, 10)); // <<---
		sprint.getStories().add(new Story("STORY 3", null, StoryStatus.DONE, null, 10));
		sprint.getStories().add(new Story("STORY 4", null, StoryStatus.DOING, null, 10));// <<---


		when(sprintDao.findSprintByStatus(SprintStatus.RUNNING)).thenReturn(sprint);
		when(sprintDao.update(sprint)).thenReturn(sprint);

		scheduleSprint.execute();

		verify(sprintDao).findSprintByStatus(SprintStatus.RUNNING);
		verify(sprintDao).update(sprint);

		assertEquals(SprintStatus.FAIL, sprint.getSprintStatus());
		assertEquals(new Long(30), sprint.getDonePoints());

		for (Story story : sprint.getStories()) {
			if (story.getStatus() != StoryStatus.DONE) {
				assertEquals(StoryStatus.BACKLOG, story.getStatus());
			}
		}
	}

	@Test
	public void shouldUpdateStatusSprintWhenSprintFailTwo() { // sprint with stories in to do

		/*
		 *
		 * sprint fail = when final date expired and sprint've any story in to do.
		 *
		 * all story in status different of done should changing status to backlog.
		 *
		 */

		Calendar calendar = Calendar.getInstance();
		calendar.roll(Calendar.DAY_OF_MONTH, -1); // sprint finished

		Sprint sprint = new Sprint();
		sprint.setFinalDate(calendar.getTime());
		sprint.setSprintStatus(SprintStatus.RUNNING);


		sprint.setStories(new HashSet<Story>());
		sprint.getStories().add(new Story("STORY 1", null, StoryStatus.DONE, null,10));
		sprint.getStories().add(new Story("STORY 2", null, StoryStatus.DONE, null,10));
		sprint.getStories().add(new Story("STORY 3", null, StoryStatus.TODO, null,10)); // <<---
		sprint.getStories().add(new Story("STORY 3", null, StoryStatus.DONE, null,10));
		sprint.getStories().add(new Story("STORY 4", null, StoryStatus.TODO, null,10));// <<---


		when(sprintDao.findSprintByStatus(SprintStatus.RUNNING)).thenReturn(sprint);
		when(sprintDao.update(sprint)).thenReturn(sprint);

		scheduleSprint.execute();

		verify(sprintDao).findSprintByStatus(SprintStatus.RUNNING);
		verify(sprintDao).update(sprint);

		assertEquals(SprintStatus.FAIL, sprint.getSprintStatus());
		assertEquals(new Long(30), sprint.getDonePoints());

		for (Story story : sprint.getStories()) {
			if (story.getStatus() != StoryStatus.DONE) {
				assertEquals(StoryStatus.BACKLOG, story.getStatus());
			}
		}
	}


	@Test
	public void shouldUpdateStatusSprintWhenSprintSucess() { // process

		/*
		 *
		 * sprint fail = when final date expired and sprint've any story in doing.
		 *
		 * all story in status different of done should changing status for todo.
		 *
		 */

		Calendar calendar = Calendar.getInstance();
		calendar.roll(Calendar.DAY_OF_MONTH, -1); // sprint finished

		Sprint sprint = new Sprint();
		sprint.setFinalDate(calendar.getTime());
		sprint.setSprintStatus(SprintStatus.RUNNING);



		sprint.setStories(new HashSet<Story>());
		sprint.getStories().add(new Story("STORY 1", null, StoryStatus.DONE, null,10));
		sprint.getStories().add(new Story("STORY 2", null, StoryStatus.DONE, null,10));
		sprint.getStories().add(new Story("STORY 3", null, StoryStatus.DONE, null,10)); // <<---
		sprint.getStories().add(new Story("STORY 3", null, StoryStatus.DONE, null,10));
		sprint.getStories().add(new Story("STORY 4", null, StoryStatus.DONE, null,10));// <<---


		when(sprintDao.findSprintByStatus(SprintStatus.RUNNING)).thenReturn(sprint);
		when(sprintDao.update(sprint)).thenReturn(sprint);

		scheduleSprint.execute();

		verify(sprintDao).findSprintByStatus(SprintStatus.RUNNING);
		verify(sprintDao).update(sprint);

		assertEquals(SprintStatus.SUCCESS, sprint.getSprintStatus());
		assertEquals(new Long(50), sprint.getDonePoints());

	}

}
