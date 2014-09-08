package com.engelhardt.BF.Alarm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class AlarmManager {
	private static AlarmManager instance = null;
	private ArrayList<Long> alarms;
	private Map<Long, String> groups;
	
	private Timer timer = new Timer();
	
	private String nextGroup = "all";

	private TimerTask task = new TimerTask() {
		@Override
		public void run() {
			new Alarm().fireAlarm(nextGroup);
			// alarms.remove(time);
			timer.schedule(task, new Date(getNextAlarmTime()));
		}
	};

	private AlarmManager() {
		alarms = new ArrayList<Long>();
		groups = new HashMap<Long, String>();
	}

	public static AlarmManager getInstance() {
		if (instance == null) {
			instance = new AlarmManager();
		}
		return instance;
	}

	public void addAlarm(int delay, String group)
			throws DuplicateAlarmException {
		long scheduleTime = System.currentTimeMillis();
		long alarmTime = scheduleTime + delay * 60 * 1000;
		
		groups.put(alarmTime, group);
		
		if (alarms.contains(alarmTime)) {
			throw new DuplicateAlarmException();
		}

		if (alarmTime == scheduleTime) {
			new Alarm().fireAlarm(groups.get(alarmTime));
		} else {
			alarms.add(alarmTime);
			try {
				long nextAlarm = getNextAlarmTime();
				nextGroup = groups.get(nextAlarm);
				timer.schedule(task, new Date(nextAlarm));
			} catch (IllegalStateException e) {
				// ignore
			}
		}
	}

	private long getNextAlarmTime() {
		long nextTime = alarms.get(0);
		for (int i = 1; i < alarms.size(); i++) {
			long compareTime = alarms.get(i);
			if (compareTime < nextTime) {
				nextTime = compareTime;
			}
		}
		return nextTime;
	}

}
