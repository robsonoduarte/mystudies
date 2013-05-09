package br.com.mystudies.service.temp;

import java.util.Date;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class InPeriod extends TypeSafeMatcher<Date> {

	private Date maxDate;



	private InPeriod(Date maxDate) {
		this.maxDate = maxDate;
	}

	@Override
	public boolean matchesSafely(Date date) {
		return before(date) ;
	}


	@Override
	public void describeTo(Description description) {
		description.appendText(" >> date without of period");
	}


	@Factory
	public static Matcher<Date> inPeriod(Date maxDate) {
		return new InPeriod(maxDate);
	}


	private boolean before(Date date) {
		return maxDate.compareTo(date) >= 0;
	}



}
