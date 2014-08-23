package com.engelhardt.BF.Alarm;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Alarm {
	public void fireAlarm()
	{
		try {
//			InputStream alarmStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Alarm.wav");
			File fis = new File("D:/Alarm.wav");
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
