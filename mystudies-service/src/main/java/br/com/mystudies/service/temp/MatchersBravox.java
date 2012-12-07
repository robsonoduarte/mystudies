package br.com.mystudies.service.temp;

import java.util.Date;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class MatchersBravox {

	public static Matcher<Date> inPeriod(final Date date) {

		
		
		return new Matcher<Date>() {

			@Override
			public void describeTo(Description arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
				// TODO Auto-generated method stub				
			}

			@Override
			public boolean matches(Object arg0) {
				if(arg0 instanceof Date)
					return ((Date) arg0).compareTo(date) != -1;
				return false;
			}
		};
	}

}
