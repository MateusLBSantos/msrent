package com.mateus.msrent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mateus.msrent.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
