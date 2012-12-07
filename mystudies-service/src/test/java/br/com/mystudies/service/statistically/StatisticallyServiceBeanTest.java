package br.com.mystudies.service.statistically;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.mystudies.domain.entity.Sprint;
import br.com.mystudies.domain.entity.Story;
import br.com.mystudies.domain.enun.StoryStatus;
import br.com.mystudies.service.SprintService;
import br.com.mystudies.service.StoryService;

public class StatisticallyServiceBeanTest {

	
	@InjectMocks
	private StatisticallyServiceBean statisticallyService;

	
	@Mock
	private SprintService sprintService;
	
	
	@Mock
	private StoryService storyService;
	
	
	@Before
	public void setUp() throws Exception {
		statisticallyService = new StatisticallyServiceBean();
		initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
		statisticallyService = null;
	}

	
	
	@Test
	public void shouldReturnAverageOfPointsBySprints() throws Exception {
		
		// 1 - media de pontos por sprint
		
		when(sprintService.getAllSprints()).thenReturn(getSprints());
		
		
		List<Temp> temps = 
				statisticallyService.get();

		
		verify(sprintService).getAllSprints();
		
		
		assertEquals(new Double(40), temps.get(0).getAverage()); 
		assertEquals(new Double(22.5), temps.get(1).getAverage());
		assertEquals(new Double(32.3), temps.get(2).getAverage());
		assertEquals(new Double(34), temps.get(3).getAverage());
		assertEquals(new Double(36.6), temps.get(4).getAverage());
		
	}
	
	
	@Test
	public void shouldReturnTotalOfPointsInBacklogInSprint() throws Exception {
		
		
		// 2 - total de pontos no backlog no sprint // TODO em portugues o comentario ??
		
		when(sprintService.getAllSprints()).thenReturn(getSprints());
		when(storyService.getStories(StoryStatus.BACKLOG)).thenReturn(getStories());
		
		
		List<Temp> temps = statisticallyService.get();
		
		
		verify(sprintService).getAllSprints();
		verify(storyService).getStories(StoryStatus.BACKLOG);
		
		System.out.println(DateUtils.parseDate("05/07/2012", "dd/MM/yyyy").compareTo(DateUtils.parseDate("04/07/2012", "dd/MM/yyyy")));
		
		
		
		assertEquals(new Integer(50), temps.get(0).getPointsInBacklog()); 
		assertEquals(new Integer(100), temps.get(1).getPointsInBacklog());
		assertEquals(new Integer(120), temps.get(2).getPointsInBacklog());
		assertEquals(new Integer(140), temps.get(3).getPointsInBacklog());
		assertEquals(new Integer(250), temps.get(4).getPointsInBacklog());
		
		
		
	}

	
	
	// preciso agora !
	// 3 - total de sprints necessarios para fechar o backlog atual do sprint...
	
	
	
	
	
	
	private List<Story> getStories() throws ParseException {
		
		List<Story> stories = new ArrayList<>();
		
		// */07/2012 >> 50 points in backlog
		stories.add(new Story(null, null, null, DateUtils.parseDate("05/07/2012", "dd/MM/yyyy"), 10 ));
		stories.add(new Story(null, null, null, DateUtils.parseDate("10/07/2012", "dd/MM/yyyy"), 10 ));
		stories.add(new Story(null, null, null, DateUtils.parseDate("15/07/2012", "dd/MM/yyyy"), 10 ));
		stories.add(new Story(null, null, null, DateUtils.parseDate("25/07/2012", "dd/MM/yyyy"), 10 ));
		stories.add(new Story(null, null, null, DateUtils.parseDate("30/07/2012", "dd/MM/yyyy"), 10 ));
		
		// */08/2012 >> + 50  points in backlog >> TOTAL = 100 points
		stories.add(new Story(null, null, null, DateUtils.parseDate("05/08/2012", "dd/MM/yyyy"), 20 ));
		stories.add(new Story(null, null, null, DateUtils.parseDate("10/08/2012", "dd/MM/yyyy"), 20 ));
		stories.add(new Story(null, null, null, DateUtils.parseDate("30/08/2012", "dd/MM/yyyy"), 10 ));
		
		// */09/2012 >> + 20  points in backlog >> TOTAL = 120 points
		stories.add(new Story(null, null, null, DateUtils.parseDate("05/09/2012", "dd/MM/yyyy"), 5 ));
		stories.add(new Story(null, null, null, DateUtils.parseDate("10/09/2012", "dd/MM/yyyy"), 5 ));
		stories.add(new Story(null, null, null, DateUtils.parseDate("30/09/2012", "dd/MM/yyyy"), 10 ));
				
		// */10/2012 >> + 20  points in backlog >> TOTAL = 140 points
		stories.add(new Story(null, null, null, DateUtils.parseDate("05/10/2012", "dd/MM/yyyy"), 5 ));
		stories.add(new Story(null, null, null, DateUtils.parseDate("10/10/2012", "dd/MM/yyyy"), 5 ));
		stories.add(new Story(null, null, null, DateUtils.parseDate("30/10/2012", "dd/MM/yyyy"), 10 ));
		
		
		// */11/2012 >> + 110 points in backlog >> TOTAL = 250 points
		stories.add(new Story(null, null, null, DateUtils.parseDate("05/11/2012", "dd/MM/yyyy"), 20 ));
		stories.add(new Story(null, null, null, DateUtils.parseDate("10/11/2012", "dd/MM/yyyy"), 20));
		stories.add(new Story(null, null, null, DateUtils.parseDate("15/11/2012", "dd/MM/yyyy"), 20 ));
		stories.add(new Story(null, null, null, DateUtils.parseDate("15/11/2012", "dd/MM/yyyy"), 20 ));
		stories.add(new Story(null, null, null, DateUtils.parseDate("15/11/2012", "dd/MM/yyyy"), 20 ));
		stories.add(new Story(null, null, null, DateUtils.parseDate("30/11/2012", "dd/MM/yyyy"), 10 ));
		
		return stories;
	}

	
	
	
	private List<Sprint> getSprints() throws ParseException {
		List<Sprint> sprints = new ArrayList<>();
		Sprint sprint = new Sprint();
		sprint.setDonePoints(40l);
		sprint.setId(1l);
		sprint.setFinalDate(DateUtils.parseDate("30/07/2012", "dd/MM/yyyy"));
		sprints.add(sprint);
		
		sprint = new Sprint();
		sprint.setDonePoints(5l);
		sprint.setId(2l);
		sprint.setFinalDate(DateUtils.parseDate("30/08/2012", "dd/MM/yyyy"));
		sprints.add(sprint);

		sprint = new Sprint();
		sprint.setDonePoints(52l);
		sprint.setId(3l);
		sprint.setFinalDate(DateUtils.parseDate("30/09/2012", "dd/MM/yyyy"));
		sprints.add(sprint);
		
		sprint = new Sprint();
		sprint.setDonePoints(39l);
		sprint.setId(4l);
		sprint.setFinalDate(DateUtils.parseDate("30/10/2012", "dd/MM/yyyy"));
		sprints.add(sprint);
		
		
		sprint = new Sprint();
		sprint.setDonePoints(47l);
		sprint.setId(5l);
		sprint.setFinalDate(DateUtils.parseDate("30/11/2012", "dd/MM/yyyy"));
		sprints.add(sprint);
		
		return sprints;
	}
	
}
