package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Country;
import com.example.demo.entity.Region;

public interface CountryRepository extends JpaRepository<Country, Long>, CrudRepository<Country, Long> {

	List<Country> findByRegion(Optional<Region> region);

}
