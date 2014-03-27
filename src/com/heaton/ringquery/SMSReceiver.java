package com.heaton.ringquery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {
	


	@Override
	public void onReceive(Context context, Intent intent) {
		
		AudioManager amanager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
		
//		Vibrator vb = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
//		int ringerMode = amanager.getRingerMode();
//		amanager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		
        
		Bundle bundle = intent.getExtras();
		SmsMessage[] msgs = null;
		String str = "";
		boolean question = false;
		if(bundle != null){
			Object[] pdus = (Object[])bundle.get("pdus");
			msgs = new SmsMessage[pdus.length];
			
			for(int ii = 0; ii < msgs.length; ii++){
				msgs[ii] = SmsMessage.createFromPdu((byte[])pdus[ii]);
				str += "SMS from "+msgs[ii].getOriginatingAddress();
				str += " :";
				str += msgs[ii].getMessageBody().toString();
				str += "\n";
				if(msgs[ii].getMessageBody().toString().contains("?")) question = true;
			}
		}
		Toast.makeText(context, "Intent Detected. "+str+(question?" IS A QUESTION ":" is not a question. "), Toast.LENGTH_LONG).show();
		Ringtone r = RingtoneManager.getRingtone(context, MainActivity.ringtoneURI);
		Uri ringtone = RingtoneManager.getActualDefaultRingtoneUri(context, RingtoneManager.TYPE_NOTIFICATION);
		if(question){
			amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
			r.play();
		}
//		else{
//			amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, false);
//		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		r.stop();
		amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, false);
//		amanager.setRingerMode(ringerMode);
		
	}

}
