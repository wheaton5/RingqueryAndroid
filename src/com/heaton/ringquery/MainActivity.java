package com.heaton.ringquery;

import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

    private RingtoneManager mRingtoneManager;
	private Cursor mcursor;
	private String title;
	private TextView text;
	private Button button1;
	private Intent Mringtone;


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRingtoneManager = new RingtoneManager(this);
        mcursor = mRingtoneManager.getCursor();
        title = mRingtoneManager.EXTRA_RINGTONE_TITLE;
//        text = (TextView)findViewById(R.id.textadd);
        button1 = (Button)findViewById(R.id.button01);
        button1.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View arg0) {
		Toast.makeText(arg0.getContext(),"select a ringtone", Toast.LENGTH_LONG).show();
		// TODO Auto-generated method stub
		//Starts the intent or Activity of the ringtone manager, opens popup box
		Mringtone = new Intent(mRingtoneManager.ACTION_RINGTONE_PICKER);

		//specifies what type of tone we want, in this case "ringtone", can be notification if you want
		Mringtone.putExtra(mRingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_RINGTONE);

		//gives the title of the RingtoneManager picker title
		Mringtone.putExtra(mRingtoneManager.EXTRA_RINGTONE_TITLE, "This is the title Of Your Picker!");

		//returns true shows the rest of the songs on the device in the default location
		Mringtone.getBooleanExtra(mRingtoneManager.EXTRA_RINGTONE_INCLUDE_DRM, true);

		String uri = null;
		//chooses and keeps the selected item as a uri
		if ( uri != null ) { 
			Mringtone.putExtra(mRingtoneManager.EXTRA_RINGTONE_EXISTING_URI, Uri.parse( uri ));
		} else { 
			Mringtone.putExtra(mRingtoneManager.EXTRA_RINGTONE_EXISTING_URI, (Uri)null);
		}
	}
    
}
