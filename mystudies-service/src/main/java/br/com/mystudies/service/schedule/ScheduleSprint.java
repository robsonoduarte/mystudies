
package br.com.mystudies.service.schedule;

import static br.com.mystudies.domain.enun.SprintStatus.RUNNING;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

import br.com.mystudies.domain.entity.Sprint;
import br.com.mystudies.domain.entity.Story;
import br.com.mystudies.domain.enun.SprintStatus;
import br.com.mystudies.domain.enun.StoryStatus;
import br.com.r3wa.fiscalpackage.persistence.Repository;

@Singleton
public class ScheduleSprint {







	// FIXME: choice the best log for app AND LOGGING ONLY IN DEBUG MODE.
	private Logger LOGGER = Logger.getLogger(this.getClass().getName());


	@Inject
	private Repository repository;



	@Schedule(month="*", dayOfMonth="Last")
	public void execute() {
		Sprint sprint =
				repository.selectOne("sprint-by-status", RUNNING);

		if(sprint != null){

			if(sprintExpired(sprint)){
				if(allStoriesIsDone(sprint.getStories())){
					prepareSprintToSucess(sprint);
				}else{
					prepareSprintToFail(sprint);
				}
				repository.save(sprint);
			}
		}
	}



	private boolean sprintExpired(Sprint sprint) {
		return new Date().after(sprint.getFinalDate());
	}



	private void prepareSprintToSucess(Sprint sprint) {
		sprint.setSprintStatus(SprintStatus.SUCCESS);
		sprint.setDonePoints(getDonePoints(sprint.getStories()));
	}


	private void prepareSprintToFail(Sprint sprint) {
		sprint.setSprintStatus(SprintStatus.FAIL);
		sprint.setDonePoints(getDonePoints(sprint.getStories()));
		moveStoriesForStatusSprintBackLog(sprint.getStories());
	}


	private boolean allStoriesIsDone(List<Story> stories) {
		for (Story story : stories) {
			if(!storyIsDone(story)){
				return false;
			}
		}
		return true;
	}


	private void moveStoriesForStatusSprintBackLog(List<Story> stories) {
		for (Story story : stories) {
			if(!storyIsDone(story)){
				story.setStatus(StoryStatus.BACKLOG);
			}
		}
	}

	private boolean storyIsDone(Story story) {
		return story.getStatus() == StoryStatus.DONE;
	}



	private Long getDonePoints(List<Story> stories) {
		Long donePoints = new Long(0);
		for (Story story : stories) {
			if(storyIsDone(story)){
				donePoints += story.getPoints();
			}
		}
		return donePoints;
	}
}
