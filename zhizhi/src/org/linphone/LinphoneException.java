/*
LinphoneCoreException.java
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

import com.zhizhi.R;

@SuppressWarnings("serial")
public class LinphoneException extends Exception {

	public LinphoneException() {
		// TODO Auto-generated constructor stub
	}

	public LinphoneException(String detailMessage) {
		super(detailMessage);
		// TODO Auto-generated constructor stub
	}

	public LinphoneException(Throwable throwable) {
		super(throwable);
		// TODO Auto-generated constructor stub
	}

	public LinphoneException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
		// TODO Auto-generated constructor stub
	}
}
