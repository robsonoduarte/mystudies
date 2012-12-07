package br.com.mystudies.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.mystudies.domain.entity.Sprint;
import br.com.mystudies.domain.entity.Story;
import br.com.mystudies.domain.enun.SprintStatus;
import br.com.mystudies.domain.enun.StoryStatus;
import br.com.mystudies.service.persistence.SprintDao;

@Stateless
@Remote(SprintService.class)
public class SprintServiceBean implements SprintService{


	@EJB
	private SprintDao sprintDao;


	@Override
	public boolean containsSprintInRun() {

		if( getCurrentSprint() != null){
			return true;
		}

		return false;
	}



	@Override
	public Sprint create(Sprint sprint) {

		if(containsSprintInRun())
			throw new IllegalStateException("Contains sprint in running");

		// FIXME: validate parameters
		sprint.setEstimatedPoints(new Long(0));

		return sprintDao.persist(sprint);
	}




	@Override
	public Sprint getCurrentSprint() {
		return sprintDao.findSprintByStatus(SprintStatus.RUNNING);
	}


	@Override
	public Sprint addStoryInSprint(Story story) {

		// FIXME: validation of parameters story...

		Sprint sprint = getCurrentSprint();

		if(sprint == null)
			throw new IllegalStateException("Haven't sprint in running");

		sprint.getStories().add(story);
		sprint.setEstimatedPoints(sprint.getEstimatedPoints() + story.getPoints());

		story.setSprint(sprint);
		story.setStatus(StoryStatus.TODO);


		return sprintDao.update(sprint);
	}



	@Override
	public List<Sprint> getAllSprints() {
		return sprintDao.listAll();
	}


}

