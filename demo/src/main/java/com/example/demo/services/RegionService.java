package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Region;
import com.example.demo.repository.RegionRepositry;

@Service
public class RegionService {

	@Autowired
	private RegionRepositry regionRepositry;

	public Region saveRegion(Region region) {
		return regionRepositry.save(region);
	}

	public List<Region> listRegion() {
		return regionRepositry.findAll();
	}

	public Optional<Region> findRegion(Long id) {
		return regionRepositry.findById(id);
	}

}
