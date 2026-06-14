package com.example.magazin.model;

import jakarta.persistence.*;

@Entity
@Table(name = "notificari")
public class Notificare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String client; // Numele clientului care va primi notificarea
    private String mesaj;  // Textul notificării (ex: "Comanda #3 a fost EXPEDIATA")

    public Notificare() {}

    public Notificare(String client, String mesaj) {
        this.client = client;
        this.mesaj = mesaj;
    }

    // Getteri și Setteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getClient() { return client; }
    public void setClient(String client) { this.client = client; }

    public String getMesaj() { return mesaj; }
    public void setMesaj(String mesaj) { this.mesaj = mesaj; }
}
