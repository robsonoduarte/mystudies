package br.com.mystudies.service.temp;


import static br.com.mystudies.service.temp.InPeriod.inPeriod;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

public class InPeriodTest {



	@Test
	public void shouldBeInPeriod() {

		for (int i = 1; i <= 30; i++) {
			assertThat( DateUtils.setDays(new Date(), i) , inPeriod(getMonth()));
		}

	}



	@Test
	public void shouldntBeInPeriod() {
		// without of period, add 1 in actually month.
		assertThat( DateUtils.addMonths(new Date(), 1) , not(inPeriod(getMonth())));
	}







	// >>>>>>>>> AUXILIARIES METHODS <<<<<<<<<<<<<



	private Date getMonth() {
		return DateUtils.setDays(new Date(), 30);
	}



}
