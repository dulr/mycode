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
import android.util.Log;
import com.zhizhi.R;

public class KeepAliveManager extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		if (!LinphoneService.isready()) {
			Log.i(LinphoneService.TAG, "Linphone service not ready");
			return;
		} else {
			if (intent.getAction().equalsIgnoreCase(Intent.ACTION_SCREEN_ON)) {
				LinphoneService.getLc().enableKeepAlive(true);
			} else if (intent.getAction().equalsIgnoreCase(
					Intent.ACTION_SCREEN_OFF)) {
				LinphoneService.getLc().enableKeepAlive(false);
			}
		}

	}

}