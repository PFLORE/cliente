package com.flores.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flores.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	Optional<Cliente> findById(Integer id);
	
	List<Cliente> findByNombresContaining(String nombres);
}
