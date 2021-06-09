package com.alianza.demo.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alianza.demo.entity.ClientEntity;



@Repository
public interface ClienteRepository extends JpaRepository<ClientEntity, Integer> {

	List<ClientEntity> findByShareKeyContaining(String shareKey);	

	@Query(value = "SELECT * FROM client_entity c WHERE c.share_key LIKE  CONCAT('%',?1,'%') "
			+ "AND c.phone LIKE CONCAT('%',?2,'%') AND c.email LIKE CONCAT('%',?3,'%') "
			+ "AND c.date_add >=  if(?4 IS NULL,'1000-01-01',?4) AND c.date_add <= if(?5 IS NULL,'3000-01-01',?5)", nativeQuery = true)
	List<ClientEntity> findByPrueba(@Param("sharekey") String sharekey, @Param("phone") String phone,
			@Param("email") String email, @Param("dateStart") Date dateStart, @Param("dateEnd") Date dateEnd);

}