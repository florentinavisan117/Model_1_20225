package com.example.magazin.repository;

import com.example.magazin.model.Comanda;
import com.example.magazin.model.StareComanda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ComandaRepository extends JpaRepository<Comanda, Long> {
    // Filtrare după stare
    List<Comanda> findByStare(StareComanda stare);
    // Căutare după client (conține text)
    List<Comanda> findByClientContainingIgnoreCase(String client);
}
