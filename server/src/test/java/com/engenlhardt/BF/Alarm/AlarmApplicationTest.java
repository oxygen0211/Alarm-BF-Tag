package com.engenlhardt.BF.Alarm;

import javax.ws.rs.core.Application;

import org.junit.Before;
import org.junit.Test;

import com.engelhardt.BF.Alarm.AlarmApplication;

public class AlarmApplicationTest {
	private AlarmApplication fixture;
	
	@Before
	public void setup()
	{
		fixture = new AlarmApplication();
	}
	
	@Test
	public void testInterfaceImplementationCorrect()
	{
		Application application = (Application)fixture;
	}
}
