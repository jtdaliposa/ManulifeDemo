package com.demo.report.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.report.dto.ReportDto;
import com.demo.report.entity.WebsiteEntity;
import com.demo.report.service.ReportServices;

@RestController
public class ReportController {
	
	@Autowired
	private ReportServices reportServices;
	
	@RequestMapping(method=RequestMethod.GET, produces="application/json", path="/list/websites")
	public List<WebsiteEntity> getWebsiteList(){
		 return reportServices.getWebsites();
	}
	
	@RequestMapping(method=RequestMethod.GET, produces="application/json", path="/list/reports")
	public List<ReportDto> getWebsiteVisitReport(@PathParam("startDate")String startDate,
			@PathParam("endDate")String endDate){
		
		List<ReportDto> listReports = new ArrayList<>();
		
		try {
			if(!isEmpty(startDate) && !isEmpty(endDate)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				listReports = reportServices.getWebsiteVisitReport(sdf.parse(startDate), sdf.parse(endDate));
			}
			else{
				listReports = reportServices.getWebsiteVisitReport();
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return listReports;
	}
	
	private boolean isEmpty(String s){
		
		return s!=null? s.trim().equals(""):true;
	}

}
