package com.engelhardt.BF.Alarm;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("{delay}")
public class AlarmServcie {

	@GET()
	@Produces("text/plain")
	public String triggerAlarm(@PathParam("delay") int delay, @PathParam("group") String group) {
		try {
			AlarmManager.getInstance().addAlarm(delay);
			return "ok";
		} catch (DuplicateAlarmException e) {
			return "duplicate alarm";
		}
	}
}
