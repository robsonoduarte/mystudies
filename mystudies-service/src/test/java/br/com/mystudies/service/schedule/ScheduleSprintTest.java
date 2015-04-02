package br.com.mystudies.service.schedule;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Date;
import java.util.HashSet;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Ignore;
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

		Sprint sprint = createSprint(1);

		when(sprintDao.findSprintByStatus(SprintStatus.RUNNING)).thenReturn(sprint);

		scheduleSprint.execute();

		verify(sprintDao).findSprintByStatus(SprintStatus.RUNNING);

		assertThat(SprintStatus.RUNNING, equalTo(sprint.getSprintStatus()));
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


		Sprint sprint = createSprint(-1);


		when(sprintDao.findSprintByStatus(SprintStatus.RUNNING)).thenReturn(sprint);
		when(sprintDao.update(sprint)).thenReturn(sprint);

		scheduleSprint.execute();

		verify(sprintDao).findSprintByStatus(SprintStatus.RUNNING);
		verify(sprintDao).update(sprint);

		assertThat(SprintStatus.FAIL, equalTo(sprint.getSprintStatus()));
		assertThat(new Long(30), equalTo(sprint.getDonePoints()));


		for (Story story : sprint.getStories()) {
			if (story.getStatus() != StoryStatus.DONE) {
				assertThat(StoryStatus.BACKLOG, equalTo(story.getStatus()));
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


		Sprint sprint = createSprint(-1);

		when(sprintDao.findSprintByStatus(SprintStatus.RUNNING)).thenReturn(sprint);
		when(sprintDao.update(sprint)).thenReturn(sprint);

		scheduleSprint.execute();

		verify(sprintDao).findSprintByStatus(SprintStatus.RUNNING);
		verify(sprintDao).update(sprint);

		assertThat(SprintStatus.FAIL, equalTo(sprint.getSprintStatus()));
		assertThat(new Long(30), equalTo(sprint.getDonePoints()));


		for (Story story : sprint.getStories()) {
			if (story.getStatus() != StoryStatus.DONE) {
				assertThat(StoryStatus.BACKLOG, equalTo(story.getStatus()));
			}
		}
	}


	@Ignore // we will refactoring to use Repository
	@Test
	public void shouldUpdateStatusSprintWhenSprintSucess() { // process

		/*
		 *
		 * sprint fail = when final date expired and sprint've any story in doing.
		 *
		 * all story in status different of done should changing status for todo.
		 *
		 */




		Sprint sprint = createSprint(-1);
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

		assertThat(SprintStatus.SUCCESS, equalTo(sprint.getSprintStatus()));
		assertThat(new Long(50), equalTo(sprint.getDonePoints()));

	}


























    // >>>>>>>>>>>>>>> AUXILIARES METHODS <<<<<<<<<<<<<<<<<<

	private Date getDate(int amount) {
		return DateUtils.addDays(new Date(), amount);
	}



	private Sprint createSprint(int day) {

		Sprint sprint = new Sprint();
		sprint.setFinalDate(getDate(day));
		sprint.setSprintStatus(SprintStatus.RUNNING);


		sprint.setStories(new HashSet<Story>());
		sprint.getStories().add(new Story("STORY 1", null, StoryStatus.DONE, null, 10));
		sprint.getStories().add(new Story("STORY 2", null, StoryStatus.DONE, null, 10));
		sprint.getStories().add(new Story("STORY 3", null, StoryStatus.DOING, null, 10));
		sprint.getStories().add(new Story("STORY 3", null, StoryStatus.DONE, null, 10));
		sprint.getStories().add(new Story("STORY 4", null, StoryStatus.DOING, null, 10));

		return sprint;
	}







































}
