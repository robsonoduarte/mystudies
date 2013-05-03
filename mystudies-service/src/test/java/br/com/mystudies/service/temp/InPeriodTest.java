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
		// period of 1 to 31 days in a month.
		for (int i = 1; i <= 31; i++) {
			assertThat( DateUtils.setDays(new Date(), i) , inPeriod(getMinDate(),getMaxDate()));
		}
	}



	@Test
	public void shouldntBeInPeriod() {
		// without of period, add 1 in actually month.
		assertThat( DateUtils.addMonths(new Date(), 1) , not(inPeriod(getMinDate(),getMaxDate())));
	}







	// >>>>>>>>> AUXILIARIES METHODS <<<<<<<<<<<<<

	private Date getMinDate() {
		return DateUtils.setDays(new Date(), 1);
	}


	private Date getMaxDate() {
		return DateUtils.setDays(new Date(), 31);
	}



}
