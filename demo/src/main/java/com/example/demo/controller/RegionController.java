package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Country;
import com.example.demo.entity.Region;
import com.example.demo.services.RegionService;

@CrossOrigin()
@RestController
@RequestMapping("region")
public class RegionController {

	@Autowired
	private RegionService regionService;

	@PostMapping("/save")
	public Region saveRegion(@RequestBody Region region) {
		return regionService.saveRegion(region);
	}

	@GetMapping("/list")
	public List<Region> listRegion() {
		return regionService.listRegion();
	}

	@GetMapping("/findbyidPa")
	public ResponseEntity<String> findbyidPa() {
		String nombreRegion = regionService.findbyidPa();
		return new ResponseEntity<>(nombreRegion, HttpStatus.OK);
	}

	@GetMapping("/updateRegionPa/{id}/{name}")
	public ResponseEntity<Boolean> updateRegionPa(@PathVariable("id") Integer id, @PathVariable("name") String name) {
		Boolean nombreRegion = regionService.updateRegionPa(id, name);
		return new ResponseEntity<Boolean>(nombreRegion, HttpStatus.OK);
	}

	@GetMapping("/transaccion")
	public void transaccion() {
		regionService.transaccion();
	}

	@GetMapping("/regioncountry/{region_id}")
	public List<Country> regioncountry(@PathVariable("region_id") Integer region_id) {
		return regionService.regioncountry(region_id);

	}

}
