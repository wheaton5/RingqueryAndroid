package com.heaton.ringquery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
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
	}

}
