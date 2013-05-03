package br.com.mystudies.service.temp;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class InPeriod extends TypeSafeMatcher<Date> {

	private Date maxDate;
	private Date minDate;


	private InPeriod(Date minDate, Date maxDate) {
		this.maxDate = maxDate;
		this.minDate = DateUtils.truncate(maxDate, Calendar.MONTH); // truncate the hours
	}

	@Override
	public boolean matchesSafely(Date date) {
		return before(date)  &&  after(date);
	}


	@Override
	public void describeTo(Description description) {
		description.appendText(" >> date without of period");
	}


	@Factory
	public static Matcher<Date> inPeriod(Date minDate, Date maxDate) {
		return new InPeriod(minDate, maxDate);
	}



	private boolean after(Date date) {
		return date.compareTo(maxDate) <= 0;
	}


	private boolean before(Date date) {
		return date.compareTo(minDate) >= 0;
	}



}
