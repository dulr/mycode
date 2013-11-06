package com.bbpos.cswiper.ui;


import java.util.HashMap;

import com.bbpos.cswiper.CSwiperController;
import com.bbpos.cswiper.CSwiperController.PINKey;
import com.tfb.tfb168.R;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class CSwiperExampleUI extends Activity {
	
	// -----------------------------------------------------------------------
	// Constants
	// -----------------------------------------------------------------------
		
	private final static String INTENT_ACTION_CALL_STATE = "com.bbpos.cswiper.ui.CALL_STATE";
	
	private TextView outMsg;
	private TextView tvFormatID;
	private TextView tvKsn;
	private TextView tvEncTracks;
	private TextView tvTrack1Length;
	private TextView tvTrack2Length;
	private TextView tvTrack3Length;
	private TextView tvRandomNumber;
	private TextView tvMac;
	private TextView tvMaskedPAN;
	private TextView tvExpiryDate;
	private TextView tvCardHolderName;
	
	private TextView tvDescFormatID;
	private TextView tvDescKsn;
	private TextView tvDescEncTracks;
	private TextView tvDescTrack1Length;
	private TextView tvDescTrack2Length;
	private TextView tvDescTrack3Length;
	private TextView tvDescRandomNumber;
	private TextView tvDescMac;
	private TextView tvDescMaskedPAN;
	private TextView tvDescExpiryDate;
	private TextView tvDescCardHolderName;

	private Button enableSwipeButton;
	private Button getKsnButton;
	
	private CSwiperController cswiperController;	
	private CSwiperController.CSwiperStateChangedListener stateChangedListener;
	
	private IncomingCallServiceReceiver incomingCallServiceReceiver;
	private String currentResults = "";
	
	// -----------------------------------------------------------------------
	// Interface
	// -----------------------------------------------------------------------
	
	private static final int MENU_COPY = 1;
	private static final int MENU_FIRMWARE_VERSION 	= 3;
	private static final int MENU_BATTERY_VOLTAGE	= 4;
	
	@Override  
	public boolean onCreateOptionsMenu(Menu menu) {  
		menu.add(0,MENU_COPY,0,R.string.CopyToClipboard); 
		menu.add(0,MENU_FIRMWARE_VERSION,0,R.string.FirmwareVersion);
		menu.add(0,MENU_BATTERY_VOLTAGE,0,R.string.BatteryVoltage);
	    return super.onCreateOptionsMenu(menu);
	} 

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == MENU_COPY) {
			CharSequence thingsToCopy = currentResults;
			String msg = "nothing to copy!";
			if (thingsToCopy.length() > 0) {
				msg = "copied!";
				ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
				clipboard.setText(thingsToCopy);
			}
			
			Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
			toast.show();
			return true;
		}
		else if (item.getItemId() == MENU_FIRMWARE_VERSION) {
			if (cswiperController.getCSwiperState() == CSwiperController.CSwiperControllerState.STATE_IDLE) {
				String msg = cswiperController.getFirmwareVersion();
				if (msg.length() > 0) {
					resetUIControls();
					msg = "ver " + msg;
					Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
					toast.show();
				}
			}
			return true;
		}
		else if (item.getItemId() == MENU_BATTERY_VOLTAGE) {
			if (cswiperController.getCSwiperState() == CSwiperController.CSwiperControllerState.STATE_IDLE) {
				String msg = cswiperController.getBatteryVoltage();
				if (msg.length() > 0) {
					resetUIControls();
					msg += "V";
					Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
					toast.show();
				}
			}
			return true;
		}
		else {
			return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cswiper_menu);
		initViews();
		startCallStateService();
    	
		stateChangedListener = new StateChangedListener();
		cswiperController = CSwiperController.createInstance(this.getApplicationContext(), stateChangedListener);
		cswiperController.setDetectDeviceChange(true);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		cswiperController.deleteCSwiper();
		endCallStateService();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			cswiperController.deleteCSwiper();
			endCallStateService();
		}
		return super.onKeyDown(keyCode, event);
	}
	
	// -----------------------------------------------------------------------
	// CSwiper API
	// -----------------------------------------------------------------------
	
	private void cardSwipeDetected() {
		outMsg.setText(R.string.ReadingCardData);
	}
	
	private void decodeCompleted(HashMap<String, String> decodeData) {
		resetUIControls();
		outMsg.setText(R.string.DecodeCompleted);
		outMsg.setText(outMsg.getText());
		getKsnButton.setEnabled(true);
		
		String formatID = decodeData.get("formatID");
		String ksn = decodeData.get("ksn");
		String encTracks = decodeData.get("encTracks");
		String track1Length = decodeData.get("track1Length");
		String track2Length = decodeData.get("track2Length");
		String track3Length = decodeData.get("track3Length");
		String randomNumber = decodeData.get("randomNumber");
		String mac = decodeData.get("mac");
		String maskedPAN = decodeData.get("maskedPAN");
		String expiryDate = decodeData.get("expiryDate");
		String cardHolderName = decodeData.get("cardHolderName");
		
		tvFormatID.setText(formatID);
		tvKsn.setText(ksn);
		tvEncTracks.setText(encTracks);
		tvTrack1Length.setText(track1Length);
		tvTrack2Length.setText(track2Length);
		tvTrack3Length.setText(track3Length);
		tvRandomNumber.setText(randomNumber);
		tvMac.setText(mac);
		tvMaskedPAN.setText(maskedPAN);
		tvExpiryDate.setText(expiryDate);
		tvCardHolderName.setText(cardHolderName);
		changeCardDataDescColor(true);
		
		String separator = "; ";
		StringBuilder sb = new StringBuilder();
		sb.append("formatID: "+formatID + separator);
		sb.append("ksn: "+ksn + separator);
		sb.append("encTracks: "+encTracks + separator);
		sb.append("track1Length: "+track1Length + separator);
		sb.append("track2Length: "+track2Length + separator);
		sb.append("track3Length: "+track3Length + separator);
		sb.append("randomNumber: "+randomNumber + separator);
		sb.append("mac: "+mac + separator);
		sb.append("maskedPAN: "+maskedPAN + separator);
		sb.append("expiryDate: "+expiryDate + separator);
		sb.append("cardHolderName: "+cardHolderName + separator);
		currentResults = sb.toString();
	}
	
	private void decodeError(int res) {
		resetUIControls();
		outMsg.setText(res);
		changeCardDataDescColor(false);
		getKsnButton.setEnabled(true);
	}
	
	private void error(String err) {
		resetUIControls();
		outMsg.setText(err);
	}
	
	private class StateChangedListener implements CSwiperController.CSwiperStateChangedListener {

		@Override
		public void onCardSwipeDetected() {
			cardSwipeDetected();
		}

		@Override
		public void onDecodeCompleted(HashMap<String, String> decodeData) {
			decodeCompleted(decodeData);
		}

		@Override
		public void onDecodeError(CSwiperController.DecodeResult decodeResult) {
			if (decodeResult == CSwiperController.DecodeResult.DECODE_SWIPE_FAIL) {
				decodeError(R.string.BadSwipe);
			} else if (decodeResult == CSwiperController.DecodeResult.DECODE_CRC_ERROR) {
				decodeError(R.string.CRCError);
			} else if (decodeResult == CSwiperController.DecodeResult.DECODE_COMM_ERROR) {
				decodeError(R.string.CommunicationError);
			} else {
				decodeError(R.string.UnknownDecodeError);
			}
		}

		@Override
		public void onError(int errorCode, String message) {
			String msg = "";
			if (errorCode == CSwiperController.ERROR)
				msg = "ERROR";
			else if (errorCode == CSwiperController.ERROR_FAIL_TO_START)
				msg = "ERROR_FAIL_TO_START";
			else if (errorCode == CSwiperController.ERROR_FAIL_TO_GET_KSN)
				msg = "ERROR_FAIL_TO_GET_KSN";
			else if (errorCode == CSwiperController.ERROR_FAIL_TO_GET_FIRMWARE_VERSION)
				msg = "ERROR_FAIL_TO_GET_FIRMWARE_VERSION";
			else if (errorCode == CSwiperController.ERROR_FAIL_TO_GET_BATTERY_VOLTAGE)
				msg = "ERROR_FAIL_TO_GET_BATTERY_VOLTAGE";
			error(msg + " " + message);
		}

		@Override
		public void onGetKsnCompleted(String ksn) {
			resetUIControls();
			outMsg.setText(R.string.GetKsnCompleted);
			outMsg.setText(outMsg.getText());
			tvKsn.setText(ksn);
			currentResults = "ksn: " + ksn;
		}

		@Override
		public void onInterrupted() {
			resetUIControls();
			outMsg.setText(R.string.Interrupted);
		}

		@Override
		public void onNoDeviceDetected() {
			resetUIControls();
			outMsg.setText(R.string.CSwiperIsNotDetected);
		}

		@Override
		public void onTimeout() {
			resetUIControls();			
			outMsg.setText(R.string.Timeout);
		}

		@Override
		public void onWaitingForCardSwipe() {
			outMsg.setText(R.string.WaitingCardSwipe);
		}

		@Override
		public void onWaitingForDevice() {
			outMsg.setText(R.string.WaitingForDevice);
		}

		@Override
		public void onDevicePlugged() {
			Toast toast = Toast.makeText(CSwiperExampleUI.this, R.string.CSwiperPlugged, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
			toast.show();
		}

		@Override
		public void onDeviceUnplugged() {
			Toast toast = Toast.makeText(CSwiperExampleUI.this, R.string.CSwiperUnplugged, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
			toast.show();
		}

		@Override
		public void onEPBDetected() {
		}

		@Override
		public void onPinEntryDetected(PINKey arg0) {
		}

		@Override
		public void onWaitingForPinEntry() {
		}
		
	}
	
	private void startCallStateService() {
		startService(new Intent(INTENT_ACTION_CALL_STATE));
		if (incomingCallServiceReceiver == null) {
			incomingCallServiceReceiver = new IncomingCallServiceReceiver();
			IntentFilter intentFilter = new IntentFilter();
			intentFilter.addAction(CSwiperCallStateService.INTENT_ACTION_INCOMING_CALL);
			this.registerReceiver(incomingCallServiceReceiver, intentFilter);
		}
	}
	
	private void endCallStateService() {
		stopService(new Intent(INTENT_ACTION_CALL_STATE));
		if (incomingCallServiceReceiver != null) {
			this.unregisterReceiver(incomingCallServiceReceiver);
			incomingCallServiceReceiver = null;
		}
	}
	
	public void resetUIControls() {
		currentResults = "";
		
		changeCardDataDescColor(false);
		outMsg.setText("");
		tvFormatID.setText("");
		tvKsn.setText("");
		tvEncTracks.setText("");
		tvTrack1Length.setText("");
		tvTrack2Length.setText("");
		tvTrack3Length.setText("");
		tvRandomNumber.setText("");
		tvMac.setText("");
		tvMaskedPAN.setText("");
		tvExpiryDate.setText("");
		tvCardHolderName.setText("");
		
		enableSwipeButton.setEnabled(true);
		enableSwipeButton.setText(R.string.enableSwipeButtonText);
		
		getKsnButton.setEnabled(true);
		getKsnButton.setText(R.string.getKsnButtonText);
	}
	
	public void changeCardDataDescColor(boolean enable) {
		int hex_enable = 0xFF000000;
		int hex_disable = 0xFF635959;
		if (enable) {
			tvDescFormatID.setTextColor(hex_enable);
			tvDescKsn.setTextColor(hex_enable);
			tvDescEncTracks.setTextColor(hex_enable);
			tvDescTrack1Length.setTextColor(hex_enable);
			tvDescTrack2Length.setTextColor(hex_enable);
			tvDescTrack3Length.setTextColor(hex_enable);
			tvDescRandomNumber.setTextColor(hex_enable);
			tvDescMac.setTextColor(hex_enable);
			tvDescMaskedPAN.setTextColor(hex_enable);
			tvDescExpiryDate.setTextColor(hex_enable);
			tvDescCardHolderName.setTextColor(hex_enable);
		}else{
			tvDescFormatID.setTextColor(hex_disable);
			tvDescKsn.setTextColor(hex_disable);
			tvDescEncTracks.setTextColor(hex_disable);
			tvDescTrack1Length.setTextColor(hex_disable);
			tvDescTrack2Length.setTextColor(hex_disable);
			tvDescTrack3Length.setTextColor(hex_disable);
			tvDescRandomNumber.setTextColor(hex_disable);
			tvDescMac.setTextColor(hex_disable);
			tvDescMaskedPAN.setTextColor(hex_disable);
			tvDescExpiryDate.setTextColor(hex_disable);
			tvDescCardHolderName.setTextColor(hex_disable);
		}		
	}
	
	
	// -----------------------------------------------------------------------
	// Private classes
	// -----------------------------------------------------------------------
	
	private void initViews() {
		outMsg = (TextView) findViewById(R.id.displayMsg);

		tvFormatID = (TextView) findViewById(R.id.tvFormatID);
		tvKsn = (TextView) findViewById(R.id.tvKsn);
		tvKsn.setMovementMethod(new ScrollingMovementMethod());
		tvEncTracks = (TextView) findViewById(R.id.tvEncTracks);
		tvEncTracks.setMovementMethod(new ScrollingMovementMethod());
		tvTrack1Length = (TextView) findViewById(R.id.tvTrack1Length);
		tvTrack2Length = (TextView) findViewById(R.id.tvTrack2Length);
		tvTrack3Length = (TextView) findViewById(R.id.tvTrack3Length);
		tvRandomNumber = (TextView) findViewById(R.id.tvRandomNumber);
		tvMac = (TextView) findViewById(R.id.tvMac);
		tvMaskedPAN = (TextView) findViewById(R.id.tvMaskedPAN);
		tvExpiryDate = (TextView) findViewById(R.id.tvExpiryDate);
		tvCardHolderName = (TextView) findViewById(R.id.tvCardHolderName);
		
		tvDescFormatID = (TextView) findViewById(R.id.tvDescFormatID);
		tvDescKsn = (TextView) findViewById(R.id.tvDescKsn);
		tvDescEncTracks = (TextView) findViewById(R.id.tvDescEncTracks);
		tvDescTrack1Length = (TextView) findViewById(R.id.tvDescTrack1Length);
		tvDescTrack2Length = (TextView) findViewById(R.id.tvDescTrack2Length);
		tvDescTrack3Length = (TextView) findViewById(R.id.tvDescTrack3Length);
		tvDescRandomNumber = (TextView) findViewById(R.id.tvDescRandomNumber);
		tvDescMac = (TextView) findViewById(R.id.tvDescMac);
		tvDescMaskedPAN = (TextView) findViewById(R.id.tvDescMaskedPAN);
		tvDescExpiryDate = (TextView) findViewById(R.id.tvDescExpiryDate);
		tvDescCardHolderName = (TextView) findViewById(R.id.tvDescCardHolderName);
		changeCardDataDescColor(false);
		
		enableSwipeButton = (Button) findViewById(R.id.enableSwipeButton);
		enableSwipeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				enableSwipe();
			}
		});
		
		getKsnButton = (Button) findViewById(R.id.getKsnButton);
		getKsnButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				getKsn();
			}
		});
	}
	
	private void enableSwipe() {
		resetUIControls();
		try {
			if (cswiperController.getCSwiperState() == CSwiperController.CSwiperControllerState.STATE_IDLE) {
				enableSwipeButton.setText(R.string.STOP);
				getKsnButton.setEnabled(false);
				cswiperController.startCSwiper();
			}
			else {
				cswiperController.stopCSwiper();
			}
		}
		catch (IllegalStateException ex) {
			resetUIControls();
			outMsg.setText("IllegalStateException: " + ex.getMessage());
		}
	}
	
	private void getKsn() {
		try {
			if (cswiperController.getCSwiperState() == CSwiperController.CSwiperControllerState.STATE_IDLE) {
				resetUIControls();
				outMsg.setText(R.string.ReadingKsnData);
				getKsnButton.setEnabled(false);
				enableSwipeButton.setEnabled(false);
				cswiperController.getCSwiperKsn();
			}
		}
		catch (IllegalStateException ex) {
			resetUIControls();
			outMsg.setText("IllegalStateException: " + ex.getMessage());
		}
	}
	
	// -----------------------------------------------------------------------
	// Inner classes
	// -----------------------------------------------------------------------
	
	private class IncomingCallServiceReceiver extends BroadcastReceiver {		
		@Override
		public void onReceive(Context context, Intent intent) {
			
			if (intent.getAction().equals(CSwiperCallStateService.INTENT_ACTION_INCOMING_CALL)) {
				resetUIControls();
				outMsg.setText(R.string.IncomingCallDetected);
				try {
					if (cswiperController.getCSwiperState() != CSwiperController.CSwiperControllerState.STATE_IDLE) {						
						cswiperController.stopCSwiper();
					}
				}
				catch (IllegalStateException ex) {
					resetUIControls();
					outMsg.setText("IllegalStateException: " + ex.getMessage());
				}
			}			
		} // end-of onReceive		
	}
	
}
