package com.demo.report.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.report.entity.WebsiteEntity;

public interface WebsiteRepository extends JpaRepository<WebsiteEntity, Integer>{

}
