package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Country;
import com.example.demo.services.CountryServices;

@CrossOrigin()
@RestController
@RequestMapping("country")
public class CountryController {

	@Autowired
	private CountryServices countryServices;

	@GetMapping("/list")
	private List<Country> listCountry() {
		return countryServices.listCountry();
	}

	@GetMapping("/listcountryregion/{id}")
	private List<Country> listCountryRegion(@PathVariable("id") Long id) {
		return countryServices.listCountryRegion(id);
	}

}
