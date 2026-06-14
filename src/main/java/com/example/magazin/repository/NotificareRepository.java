package com.example.magazin.repository;

import com.example.magazin.model.Notificare;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificareRepository extends JpaRepository<Notificare, Long> {
    // Găsește notificările unui anumit client
    List<Notificare> findByClient(String client);
}
