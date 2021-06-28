package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Country;
import com.example.demo.entity.Region;
import com.example.demo.conection.Connection;
import com.example.demo.repository.RegionRepositry;
import java.sql.*;

@Service
public class RegionService {

	@Autowired
	private RegionRepositry regionRepositry;

	@Autowired
	private Connection procedimientoAlmacenado;

	public Region saveRegion(Region region) {
		return regionRepositry.save(region);
	}

	public List<Region> listRegion() {
		return regionRepositry.findAll();
	}

	public Optional<Region> findRegion(Long id) {
		return regionRepositry.findById(id);
	}

	public String findbyidPa() {
		return procedimientoAlmacenado.findbyidPa();
	}

	public Boolean updateRegionPa(Integer id, String name) {
		return procedimientoAlmacenado.updateRegionPa(id, name);
	}

	public void transaccion() {
		procedimientoAlmacenado.transaccion();
	}

	public List<Country> regioncountry(Integer region_id) {
		return procedimientoAlmacenado.regioncountry(region_id);
	}

}
