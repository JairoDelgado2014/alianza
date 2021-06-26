package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Country;
import com.example.demo.entity.Region;
import com.example.demo.repository.CountryRepository;
import com.example.demo.repository.RegionRepositry;

@Service
public class CountryServices {

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private RegionRepositry regionRepository;

	public List<Country> listCountry() {
		return countryRepository.findAll();
	}

	public List<Country> listCountryRegion(Long id) {
		Optional<Region> region = regionRepository.findById(id);
		List<Country> list = new ArrayList<Country>();
		if (region.isPresent()) {
			list = countryRepository.findByRegion(region);
		}
		return list;
	}

}
