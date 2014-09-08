package com.engelhardt.BF.Alarm;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Alarm {
	
	private static ConcurrentMap<String, String> groupAlarms = new ConcurrentHashMap<String, String>();
	
	static
	{
		groupAlarms.put("all", "AlarmAllGroups.wav");
		groupAlarms.put("1", "AlarmGroup1.wav");
		groupAlarms.put("2", "AlarmGroup2.wav");
	}
	
	public void fireAlarm(String group)
	{
		try {
			String dataUrl = System.getProperty("jboss.server.data.dir");
			File dataFile = new File(dataUrl);
			File fis = new File(dataFile, groupAlarms.get(group));
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(fis);
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
