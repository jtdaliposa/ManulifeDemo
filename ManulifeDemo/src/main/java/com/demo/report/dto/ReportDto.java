package com.demo.report.dto;

public class ReportDto {

	private String websiteName;
	
	private String url;
	
	private Long totalVisits;
	
	public ReportDto(){
		
	}
	
	public ReportDto(String websiteName, Long totalVisits, String url){
		this.websiteName = websiteName;
		this.totalVisits = totalVisits;
		this.url = url;
	}

	public String getWebsiteName() {
		return websiteName;
	}

	public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName;
	}

	public Long getTotalVisits() {
		return totalVisits;
	}

	public void setTotalVisits(Long totalVisits) {
		this.totalVisits = totalVisits;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
