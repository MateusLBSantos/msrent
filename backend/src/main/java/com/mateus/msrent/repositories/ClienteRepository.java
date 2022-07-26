package com.mateus.msrent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mateus.msrent.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
