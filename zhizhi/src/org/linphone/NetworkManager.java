/*
ContactPickerActivity.java
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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.zhizhi.R;

public class NetworkManager extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		NetworkInfo lNetworkInfo = (NetworkInfo) intent
				.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
		Log.i(LinphoneService.TAG, "Network info [" + lNetworkInfo + "]");
		Boolean lNoConnectivity = intent.getBooleanExtra(
				ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
		if (!LinphoneService.isready()) {
			Log.i(LinphoneService.TAG, "Linphone service not ready");
			return;
		}
		if (lNoConnectivity
				| ((lNetworkInfo.getState() == NetworkInfo.State.DISCONNECTED) /*
																				 * &&
																				 * !
																				 * lIsFailOver
																				 */)) {
			LinphoneService.instance().getLinphoneCore()
					.setNetworkReachable(false);
		} else if (lNetworkInfo.getState() == NetworkInfo.State.CONNECTED) {
			LinphoneService.instance().getLinphoneCore()
					.setNetworkReachable(true);
		} else {
			// unhandled event
		}

	}

}
