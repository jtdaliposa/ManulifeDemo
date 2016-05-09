package com.demo.report.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.report.dto.ReportDto;
import com.demo.report.entity.WebsiteEntity;
import com.demo.report.repo.WebsiteRepository;
import com.demo.report.repo.WebsiteVisitRepo;

@Service
public class ReportServices {
	
	@Autowired
	private WebsiteRepository webRepo;
	
	@Autowired
	private WebsiteVisitRepo webVisitRepo;
	
	private final String QUERY_REPORT_BY_DATE = "select new com.demo.report.dto.ReportDto(r.name, sum(v.visitcount) as total,r.url) from "
			+ " WebsiteEntity r, WebsiteVisitEntity v where (v.localDate between :startDate and :endDate) and "
		+" r.id = v.website.id group by r.name,r.url order by total desc";
	
	private final String QUERY_REPORT_LATEST_COUNT = "select new com.demo.report.dto.ReportDto(r.name, sum(v.visitcount) as total,r.url) from "
			+ " WebsiteEntity r, WebsiteVisitEntity v where r.id = v.website.id group by r.name,r.url order by total desc";
	
	@PersistenceContext
	private EntityManager em;
	
	public List<WebsiteEntity> getWebsites(){
		return webRepo.findAll();
	}
	
	public List<ReportDto> getWebsiteVisitReport(Date start, Date end){
		
		Query query = em.createQuery(QUERY_REPORT_BY_DATE,ReportDto.class);
		query.setParameter("startDate", start);
		query.setParameter("endDate", end);
		List<ReportDto> listReports = query.getResultList();
		
		return listReports;
	}
	
	public List<ReportDto> getWebsiteVisitReport(){
		
		Query query = em.createQuery(QUERY_REPORT_LATEST_COUNT,ReportDto.class);
		List<ReportDto> listReports = query.getResultList();
		
		return listReports;
	}
	

}
