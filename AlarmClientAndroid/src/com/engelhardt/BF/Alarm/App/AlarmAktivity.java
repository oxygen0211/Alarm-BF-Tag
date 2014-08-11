package com.engelhardt.BF.Alarm.App;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AlarmAktivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_aktivity);
        final PropertyManager propManager = new PropertyManager(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS));
        
        final CheckBox delayCheck = (CheckBox) findViewById(R.id.delayCheck);
        final EditText delayField = (EditText) findViewById(R.id.delayField);
        final TextView minLabel = (TextView) findViewById(R.id.minuteLabel);
        final EditText serverField = (EditText) findViewById(R.id.ServerField);
        Button fireAlarmButton = (Button) findViewById(R.id.fireAlarmButton);
        
        delayField.setVisibility(View.GONE);
        minLabel.setVisibility(View.GONE);
        
        delayCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked)
				{
					delayField.setVisibility(View.VISIBLE);
					minLabel.setVisibility(View.VISIBLE);
				}
				
				else
				{
					delayField.setVisibility(View.GONE);
			        minLabel.setVisibility(View.GONE);
				}
				
			}
		});
        
        fireAlarmButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int delay = 0;
				if(delayCheck.isChecked())
				{
					delay = Integer.valueOf(delayField.getText().toString());
				}
				String host = serverField.getText().toString() ;
				if(host==null || host.equals(""))
				{
					Toast firingToast = Toast.makeText(AlarmAktivity.this,
							"Erst server angeben!", Toast.LENGTH_SHORT);
					firingToast.show();
				}
				else
				{
					propManager.saveLasthost(host);
					new AlarmServiceClient().fireAlarm(AlarmAktivity.this,host,delay);
				}
			}
		});
        
       serverField.setText(propManager.getLastHost());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_alarm_aktivity, menu);
        return true;
    }
    
}
