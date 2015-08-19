package com.tarlic.logbook;

import java.util.Date;

public class LogItem {
    public int icon;
    public String title;
    public String text;
    public Date date;
    public LogItem(){
        super();
    }
    
    public LogItem(int icon, String title, String text, Date date) {
        super();
        this.icon = icon;
        this.title = title;
        this.text= text;
        this.date= date;
    }

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
