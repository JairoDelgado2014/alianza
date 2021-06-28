package com.example.demo.conection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Country;
import com.example.demo.entity.Region;
import com.example.demo.repository.RegionRepositry;

@Service
public class Connection {

	@Autowired
	private RegionRepositry regionRepository;

	private String bd = "example";
	private String login = "root";
	private String password = "";
	private String url = "jdbc:mysql://localhost:3306/" + bd;

	public Connection() {

	}

	public String findbyidPa() {
		try {
			java.sql.Connection con = null;
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, login, password);
			CallableStatement consulta = con.prepareCall("{ call MUESTRA_REGION }");
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				return resultado.getString(2);
			}
			resultado.close();
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		return null;
	}

	public Boolean updateRegionPa(Integer id, String name) {
		Boolean salida = false;
		try {
			java.sql.Connection con = null;
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, login, password);
			CallableStatement consulta = con.prepareCall("{ call UPDATE_REGION(?,?)}");
			consulta.setInt(1, id);
			consulta.setString(2, name);
			consulta.execute();

		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		return null;
	}

	public void transaccion() {
		java.sql.Connection con = null;
		try {
			con = DriverManager.getConnection(url, login, password);
			Statement myStatement = con.createStatement();
			con.setAutoCommit(false);
			String query_1 = "INSERT INTO regions (id,name) VALUES('8','AFRICA')";
			myStatement.executeUpdate(query_1);
			String query_2 = "INSERT INTO conutries (id,name_courntry, region_id) VALUES(8,'ETIOPIA',5)";
			myStatement.executeUpdate(query_2);
			String query_3 = "DELETE FROM regions WHERE id =15";
			myStatement.executeUpdate(query_3);
			con.commit();
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public List<Country> regioncountry(Integer region_id) {
		List<Country> countries = new ArrayList<Country>();
		try {
			java.sql.Connection con = null;			
			con = DriverManager.getConnection(url, login, password);
			CallableStatement consulta = con.prepareCall("{ call MUESTRA_PAIS_REGION(?) }");
			consulta.setLong(1, region_id);
			ResultSet resultado = consulta.executeQuery();
			int i = 0;
			while (resultado.next()) {
				Country country = new Country();
				country.setId(resultado.getLong(1));
				country.setNameCountry(resultado.getString(2));
				Optional<Region> region = regionRepository.findById((long) resultado.getInt(3));
				country.setRegion(region.get());
				countries.add(i, country);
				i++;
			}
			resultado.close();
		} catch (SQLException e) {
			System.out.println(e);
		} 
		return countries;
	}
}
