package br.com.mystudies.service.statistically;

import static br.com.mystudies.service.temp.MatchersBravox.inPeriod;
import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.select;
import static ch.lambdaj.Lambda.sumFrom;
import static org.hamcrest.Matchers.equalTo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.math3.util.Precision;

import br.com.mystudies.domain.entity.Sprint;
import br.com.mystudies.domain.entity.Story;
import br.com.mystudies.domain.enun.StoryStatus;
import br.com.mystudies.service.SprintService;
import br.com.mystudies.service.StoryService;

public class StatisticallyServiceBean {

	
	
	/*@EJB*/
	private SprintService sprintService;
	
	private StoryService storyService;
	
	public List<Temp> get() {

		List<Sprint> sprints = sprintService.getAllSprints();
		List<Story> stories = storyService.getStories(StoryStatus.BACKLOG);
	
		
		double totalDonePooints = 0; 
		
		List<Temp> temps = new ArrayList<>();
		
		for (Sprint sprint : sprints) {
			
			totalDonePooints += sprint.getDonePoints();
			
			Temp temp = new Temp();
			temp.setAverage(Precision.round(totalDonePooints / sprint.getId(), 1));			
			temp.setPointsInBacklog(getPointsInBacklogInSprint(sprint,stories));
			
			temps.add(temp);
		}
		
		return temps;
		

	}

	
	
		
	private Integer getPointsInBacklogInSprint(Sprint sprint,List<Story> stories) {
		return sumFrom(select(stories,having(on(Story.class).getCreationDate(), inPeriod(sprint.getFinalDate())))).getPoints();
	}		
}
