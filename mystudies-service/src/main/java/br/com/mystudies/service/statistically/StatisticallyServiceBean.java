package br.com.mystudies.service.statistically;

import static br.com.mystudies.service.temp.InPeriod.inPeriod;
import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.select;
import static ch.lambdaj.Lambda.sumFrom;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.apache.commons.math3.util.Precision;

import br.com.mystudies.domain.entity.Sprint;
import br.com.mystudies.domain.entity.Story;
import br.com.mystudies.domain.enun.StoryStatus;
import br.com.mystudies.service.SprintService;
import br.com.mystudies.service.StoryService;

@Stateless
@Remote(StatisticallyService.class)
public class StatisticallyServiceBean  implements StatisticallyService{



	@EJB
	private SprintService sprintService;


	@EJB
	private StoryService storyService;


	// FIXME: ALFHA VERSION... BUT HAVE UNIT TEST...
	@Override
	public List<Temp> get() {

		List<Sprint> sprints = sprintService.getAllSprints();
		List<Story> stories = storyService.getStories(StoryStatus.BACKLOG);



		double totalDonePooints = 0;


		List<Temp> temps = new ArrayList<>();


		for (Sprint sprint : sprints) {


			totalDonePooints += sprint.getDonePoints();

			Temp temp = new Temp();
			temp.setAverage( Precision.round(totalDonePooints / sprint.getId(), 1));
			temp.setPointsInBacklog(getPointsInBacklogInSprint(sprint,stories));
			temp.setSprintsToDo(Precision.round(temp.getPointsInBacklog() / temp.getAverage(), 1) );

			temps.add(temp);
		}

		return temps;


	}



	private Integer getPointsInBacklogInSprint(Sprint sprint,List<Story> stories) {
		return sumFrom(select(stories,having(on(Story.class).getCreationDate(), inPeriod(sprint.getFinalDate())))).getPoints();
	}


}
