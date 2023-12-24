package com.example.domain;

import java.util.Map;

public class CoindeskForm {
	
	private TimeUpdate time ;
	
	private String disclaimer ;
	
	private String chartName ;
	
	private Map<String,CurrencyForm> bpi ;

	public TimeUpdate getTime() {
		return time;
	}

	public void setTime(TimeUpdate time) {
		this.time = time;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public String getChartName() {
		return chartName;
	}

	public void setChartName(String chartName) {
		this.chartName = chartName;
	}

	public Map<String, CurrencyForm> getBpi() {
		return bpi;
	}

	public void setBpi(Map<String, CurrencyForm> bpi) {
		this.bpi = bpi;
	}
	

}
