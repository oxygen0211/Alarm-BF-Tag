package com.engelhardt.BF.Alarm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class AlarmManager {
	private static AlarmManager instance = null;
	private ArrayList<Long> alarms;
	private Timer timer = new Timer();
	
	private TimerTask task = new TimerTask() {

		@Override
		public void run() {
			new Alarm().fireAlarm();
//			alarms.remove(time);
			timer.schedule(task, new Date(getNextAlarmTime()));
		}
	};
	
	private AlarmManager()
	{
		alarms = new ArrayList<Long>();
	}
	
	public static AlarmManager getInstance()
	{
		if(instance == null)
		{
			instance = new AlarmManager();
		}
		return instance;
	}
	
	public void addAlarm(int delay) throws DuplicateAlarmException
	{
		long scheduleTime = System.currentTimeMillis();
		long alarmTime = scheduleTime+delay*60*100;
		
		if(alarms.contains(alarmTime))
		{
			throw new DuplicateAlarmException();
		}
		
		if(alarmTime==scheduleTime)
		{
			new Alarm().fireAlarm();
		}
		else
		{
			alarms.add(alarmTime);
			try
			{
			timer.schedule(task, new Date(getNextAlarmTime()));
			}
			catch(IllegalStateException e)
			{
				//ignore
			}
		}
	}
	
	private long getNextAlarmTime()
	{
		long nextTime = alarms.get(0);
		for(int i=1;i<alarms.size();i++)
		{
			long compareTime = alarms.get(i);
			if(compareTime<nextTime)
			{
				nextTime = compareTime;
			}
		}
		return nextTime;
	}
	
}
