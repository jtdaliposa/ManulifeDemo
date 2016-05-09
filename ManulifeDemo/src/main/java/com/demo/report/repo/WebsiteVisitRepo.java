package com.demo.report.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.report.dto.ReportDto;
import com.demo.report.entity.WebsiteVisitEntity;

public interface WebsiteVisitRepo extends JpaRepository<WebsiteVisitEntity, Integer>{

	List<WebsiteVisitEntity> findByLocalDateBetween(Date startDate, Date endDate);
	
	
	/*
	 * select sum(v.visit_count), r.name from website_ref r, website_visit v 
where local_date between '2013-01-01' and '2013-01-06' and r.website_id = v.website_id
group by r.name
	 */
	/*@Query("select new com.example.report.dto.ReportDto(r.name, sum(v.visitcount)) from WebsiteEntity r, WebsiteVisitEntity v where v.localDate between :startDate and :endDate "
			+" r.id = v.website.id group by r.name")
	List<ReportDto> getWebsiteVisitReport(@Param("startDate") Date start,@Param("endDate") Date end);*/
}
