package com.heaton.ringquery;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private RingtoneManager mRingtoneManager;
	private Cursor mcursor;
	private String title;
	private TextView text;
	private Button button1;
	private Intent Mringtone;

	static Uri ringtoneURI = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//		RelativeLayout rl = (RelativeLayout)findViewById(id))(R.id.action_settings);
		//		rl.setBackgroundColor(Color.DKGRAY);
		ringtoneURI = RingtoneManager.getActualDefaultRingtoneUri(this, RingtoneManager.TYPE_NOTIFICATION);

		setContentView(R.layout.activity_main);
		//		findViewById(R.layout.activity_main).getContext().getResources().getColor(android.R.color.background_dark);

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

		startActivityForResult(Mringtone, 0);

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent Mringtone) {
		//		switch (resultCode) {
		/*
		 * 
		 */
		if(resultCode == RESULT_OK){
			//sents the ringtone that is picked in the Ringtone Picker Dialog
			Uri uri = Mringtone.getParcelableExtra(mRingtoneManager.EXTRA_RINGTONE_PICKED_URI);
			ringtoneURI = uri;
			//send the output of the selected to a string
			String test = uri.toString();

			//the program creates a "line break" when using the "\n" inside a string value
//			text.setText("\n " + test + "\n " + title);

			//prints out the result in the console window
			Log.i("Sample", "uri " + uri);

			//this passed the ringtone selected from the user to a new method
//			Ringtone rt = play(uri);

			//inserts another line break for more data, this times adds the cursor count on the selected item
//			text.append("\n " + mcursor.getCount()); 

			//set default ringtone
			//			try
			//			{ 
			////				RingtoneManager.setActualDefaultRingtoneUri(
			////						  MainActivity.this,
			////						  RingtoneManager.TYPE_NOTIFICATION,
			////						  uri
			////						);
			////				RingtoneManager.setActualDefaultRingtoneUri(this, resultCode, uri);
			//			}
			//			catch (Exception localException)
			//			{
			//
			////			}
			//			break; 


		}

	}


	private Ringtone play(Uri uri) {
		if (uri != null) {

			//in order to play the ringtone, you need to create a new Ringtone with RingtoneManager and pass it to a variable
			Ringtone rt = mRingtoneManager.getRingtone(this, uri);
			rt.play();
			return rt;
		}
		return null;

	}

}
