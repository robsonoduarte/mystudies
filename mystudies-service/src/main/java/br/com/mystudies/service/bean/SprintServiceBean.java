package br.com.mystudies.service.bean;

import static br.com.mystudies.domain.enun.SprintStatus.RUNNING;
import static br.com.mystudies.domain.enun.StoryStatus.TODO;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.mystudies.domain.entity.Sprint;
import br.com.mystudies.domain.entity.Story;
import br.com.mystudies.service.SprintService;
import br.com.r3wa.fiscalpackage.persistence.Repository;

@Stateless
@Remote(SprintService.class)
public class SprintServiceBean implements SprintService{


	@EJB
	private Repository repository;




	@Override
	public boolean containsSprintInRun() {
		return getCurrentSprint() != null;
	}



	@Override
	public Sprint getCurrentSprint(){
		return repository.selectOne("sprint-by-status", RUNNING);
	}






	@Override
	public Sprint create(Sprint sprint) {
		if(containsSprintInRun())
			throw new IllegalStateException("Contains sprint in running");

		return repository.save(sprint);
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
		story.setStatus(TODO);

		return repository.save(sprint);
	}




	@Override
	public List<Sprint> getAllSprints() {
		return repository.select("all-sprint");
	}



}

