/*
LinphoneActivity.java
Copyright (C) 2010  Belledonne Communications, Grenoble, France

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.linphone;

import java.util.List;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TabHost;
import com.zhizhi.R;

public class LinphoneActivity extends TabActivity {
	public static String DIALER_TAB = "dialer";
	private AudioManager mAudioManager;
	private static LinphoneActivity theLinphoneActivity;

	private FrameLayout mMainFrame;
	private SensorManager mSensorManager;
	static private SensorEventListener mSensorEventListener;

	private static String SCREEN_IS_HIDDEN = "screen_is_hidden";

	protected static LinphoneActivity instance() {
		if (theLinphoneActivity == null) {
			throw new RuntimeException("LinphoneActivity not instanciated yet");
		} else {
			return theLinphoneActivity;
		}
	}

	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mMainFrame.getVisibility() == View.INVISIBLE) {
			outState.putBoolean(SCREEN_IS_HIDDEN, true);
		} else {
			outState.putBoolean(SCREEN_IS_HIDDEN, false);
		}

	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

		theLinphoneActivity = this;
		// start linphone as background
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.setClass(this, LinphoneService.class);
		startService(intent);

		mMainFrame = (FrameLayout) findViewById(R.id.main_frame);

		mAudioManager = ((AudioManager) getSystemService(Context.AUDIO_SERVICE));

		TabHost lTabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Reusable TabSpec for each tab

		// Call History
		Intent lHistoryItent = new Intent().setClass(this,
				HistoryActivity.class);

		spec = lTabHost
				.newTabSpec("history")
				.setIndicator(getString(R.string.tab_history),
						getResources().getDrawable(R.drawable.history_orange))
				.setContent(lHistoryItent);
		lTabHost.addTab(spec);

		// dialer
		Intent lDialerIntent = new Intent()
				.setClass(this, DialerActivity.class);
		lDialerIntent.setData(getIntent().getData());

		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = lTabHost
				.newTabSpec("dialer")
				.setIndicator(getString(R.string.tab_dialer),
						getResources().getDrawable(R.drawable.dialer_orange))
				.setContent(lDialerIntent);
		lTabHost.addTab(spec);

		// contact pick
		Intent lContactItent = new Intent().setClass(this,
				ContactPickerActivity.class);

		spec = lTabHost
				.newTabSpec("contact")
				.setIndicator(getString(R.string.tab_contact),
						getResources().getDrawable(R.drawable.contact_orange))
				.setContent(lContactItent);
		lTabHost.addTab(spec);

		lTabHost.setCurrentTabByTag("dialer");
		if (savedInstanceState != null
				&& savedInstanceState.getBoolean(SCREEN_IS_HIDDEN, false)) {
			hideScreen(true);
		}

	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		if (intent.getData() != null) {
			DialerActivity.getDialer().newOutgoingCall(
					intent.getData().toString().substring("tel://".length()));
			intent.setData(null);
		}

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (isFinishing()) {
			// restaure audio settings
			if (Integer.parseInt(Build.VERSION.SDK) <= 4 /* <donut */) {
				mAudioManager.setMode(AudioManager.MODE_NORMAL);
				mAudioManager.setRouting(AudioManager.MODE_NORMAL,
						AudioManager.ROUTE_SPEAKER, AudioManager.ROUTE_ALL);
			} else {
				mAudioManager.setSpeakerphoneOn(false);
			}
			stopProxymitySensor();// just in case
			theLinphoneActivity = null;
		}

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the currently selected menu XML resource.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.linphone_activity_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_settings:
			startprefActivity();
			return true;
		case R.id.menu_exit:
			finish();
			Intent exitIntent = new Intent(Intent.ACTION_MAIN);
			exitIntent.setClass(this, LinphoneService.class);
			stopService(exitIntent);
			break;
		case R.id.menu_about:
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.setClass(this, AboutActivity.class);
			startActivity(intent);
		default:
			Log.e(LinphoneService.TAG, "Unknown menu item [" + item + "]");
			break;
		}

		return false;
	}

	protected void startprefActivity() {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.setClass(this, LinphonePreferencesActivity.class);
		startActivity(intent);
	}

	public void initFromConf() throws LinphoneException {

		try {
			LinphoneService.instance().initFromConf();
		} catch (LinphoneConfigException e) {
			handleBadConfig(e.getMessage());
		}

	}

	private void handleBadConfig(String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(
				String.format(getString(R.string.config_error), message))
				.setCancelable(false)
				.setPositiveButton(getString(R.string.yes),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								startprefActivity();
							}
						})
				.setNegativeButton(getString(R.string.no),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
		builder.create().show();
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// nop
	}

	protected void hideScreen(boolean isHidden) {
		WindowManager.LayoutParams lAttrs = getWindow().getAttributes();
		if (isHidden) {
			lAttrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
			mMainFrame.setVisibility(View.INVISIBLE);
		} else {
			lAttrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
			mMainFrame.setVisibility(View.VISIBLE);
		}
		getWindow().setAttributes(lAttrs);
	}

	protected synchronized void startProxymitySensor() {
		if (mSensorEventListener != null) {
			Log.i(LinphoneService.TAG, "proximity sensor already active");
			return;
		}
		List<Sensor> lSensorList = mSensorManager
				.getSensorList(Sensor.TYPE_PROXIMITY);
		mSensorEventListener = new SensorEventListener() {
			public void onSensorChanged(SensorEvent event) {
				if (event.timestamp == 0)
					return; // just ignoring for nexus 1
				Log.d(LinphoneService.TAG, "Proximity sensor report ["
						+ event.values[0] + "] , for max range ["
						+ event.sensor.getMaximumRange() + "]");

				if (event.values[0] != event.sensor.getMaximumRange()) {
					instance().hideScreen(true);
				} else {
					instance().hideScreen(false);
				}
			}

			public void onAccuracyChanged(Sensor sensor, int accuracy) {
			}
		};
		if (lSensorList.size() > 0) {
			mSensorManager.registerListener(mSensorEventListener,
					lSensorList.get(0), SensorManager.SENSOR_DELAY_UI);
			Log.i(LinphoneService.TAG, "Proximity sensor detected, registering");
		}
	}

	protected synchronized void stopProxymitySensor() {
		if (mSensorManager != null) {
			mSensorManager.unregisterListener(mSensorEventListener);
			mSensorEventListener = null;
		}
		hideScreen(false);
	}
}
