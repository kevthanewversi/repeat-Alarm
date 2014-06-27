package com.fragmment.adapter;
import java.util.Calendar;

import com.example.shoppercart.R;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

 
public class AlertFragment extends Fragment
{  
  Context c;
    private PendingIntent pendingIntent;
    TimePicker tp;
     
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.alert_view, container, false);
         tp = (TimePicker)rootView.findViewById(R.id.timePicker1);
        Button tButton = (Button)rootView.findViewById(R.id.toggleButton1);
 tButton.setOnClickListener(	new View.OnClickListener()
	{
		@Override 
		public void onClick(View v) { {
      armAlarm();
 }}});
      return rootView;} 
    public void armAlarm(){
    	
    	Calendar calendar = Calendar.getInstance();
        
           calendar.set(Calendar.MONTH, 6);
        calendar.set(Calendar.YEAR, 2014);
        calendar.set(Calendar.DAY_OF_MONTH, 11);
   
        calendar.set(Calendar.HOUR_OF_DAY,tp.getCurrentHour()); //get the current time from timepicker widget
        calendar.set(Calendar.MINUTE, tp.getCurrentMinute());
        calendar.set(Calendar.SECOND, 0);
    	Intent myIntent = new Intent(getActivity(), LeReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, myIntent,0);
   
  	AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(FragmentActivity.ALARM_SERVICE);
  	//RTC_WAKEUP so the alarm can still ring even when device is asleep
  	 alarmManager.setRepeating(alarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1*60*1000, pendingIntent); //pending intent to be receved by broadcast receiver after everyi 60 seconds
  	 
    }
    
}
