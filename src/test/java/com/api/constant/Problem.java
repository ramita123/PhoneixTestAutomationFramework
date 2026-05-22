package com.api.constant;

public enum Problem {
	
	SMART_PHONE_RUNNING_SLOW(1),
	POOR_BATTERY_LIEF(2),
	PHONE_OR_APP_CRASHES(3),
	SYNC_ISSUE(4),
	MICROSD_CARD_NOT_WORKING_ON_YOUR_PHONE(5),
	OVERHEATING(6);
	int code;

	Problem(int code) {
	this.code=code;
	}
	
	
	public int getCode() {
		return code;
	}
	
	
	

}
