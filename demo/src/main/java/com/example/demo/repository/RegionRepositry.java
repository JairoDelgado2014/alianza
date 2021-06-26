package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Region;

public interface RegionRepositry extends JpaRepository<Region, Long> {

}
