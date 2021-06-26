package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
