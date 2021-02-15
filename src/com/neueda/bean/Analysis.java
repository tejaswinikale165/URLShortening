package com.neueda.bean;

public class Analysis {
	private int id;
	private String longUrl;
	private String shortUrl;
	private int hitCount;  
	private int userId;
	private String urlDate;
	private String urlTime;
	private int urlYear; 
	private int urlDay;
	private int urlHour;
	public Analysis(int id, String longUrl, int hitCount, int userId, String urlDate, String urlTime, int urlYear,
			int urlDay, int urlHour) {
		super();
		this.id = id;
		this.longUrl = longUrl;
		this.hitCount = hitCount;
		this.userId = userId;
		this.urlDate = urlDate;
		this.urlTime = urlTime;
		this.urlYear = urlYear;
		this.urlDay = urlDay;
		this.urlHour = urlHour;
	}
	
	
	
	
	public Analysis(int hitCount, int urlYear) {
		super();
		this.hitCount = hitCount;
		this.urlYear = urlYear;
	}




	public Analysis(String shortUrl,String longUrl, int hitCount) {
		super();
		this.shortUrl = shortUrl;
		this.longUrl = longUrl;
		this.hitCount = hitCount;
	}



	public Analysis(int hitCount,String urlDate) {
		super();
		this.urlDate = urlDate;
		this.hitCount = hitCount;
		
	}
	
	


	public Analysis(String shortUrl,String longUrl, int hitCount, String urlDate) {
		super();
		this.shortUrl = shortUrl;
		this.longUrl = longUrl;
		this.hitCount = hitCount;
		this.urlDate = urlDate;
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getLongUrl() {
		return longUrl;
	}




	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}




	public String getShortUrl() {
		return shortUrl;
	}




	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}




	public int getHitCount() {
		return hitCount;
	}




	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}




	public int getUserId() {
		return userId;
	}




	public void setUserId(int userId) {
		this.userId = userId;
	}




	public String getUrlDate() {
		return urlDate;
	}




	public void setUrlDate(String urlDate) {
		this.urlDate = urlDate;
	}




	public String getUrlTime() {
		return urlTime;
	}




	public void setUrlTime(String urlTime) {
		this.urlTime = urlTime;
	}




	public int getUrlYear() {
		return urlYear;
	}




	public void setUrlYear(int urlYear) {
		this.urlYear = urlYear;
	}




	public int getUrlDay() {
		return urlDay;
	}




	public void setUrlDay(int urlDay) {
		this.urlDay = urlDay;
	}




	public int getUrlHour() {
		return urlHour;
	}




	public void setUrlHour(int urlHour) {
		this.urlHour = urlHour;
	}





}
