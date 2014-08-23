package com.engenlhardt.BF.Alarm;

import org.junit.Before;
import org.junit.Test;

import com.engelhardt.BF.Alarm.Alarm;

public class AlarmTest {
	private Alarm fixture;
	
	@Before
	public void setUp()
	{
		fixture = new Alarm();
	}
	
	@Test
	public void testFireAlarm()
	{
		fixture.fireAlarm();
	}
	
}
