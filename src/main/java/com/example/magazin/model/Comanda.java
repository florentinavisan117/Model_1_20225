
package com.example.magazin.model;

import com.example.magazin.model.StareComanda;
import jakarta.persistence.*;

@Entity
@Table(name = "comenzi")
public class Comanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeProduse;   // produsele comandate
    private String client;        // numele clientului
    private double total;         // suma totală

    @Enumerated(EnumType.STRING)
    private StareComanda stare;   // starea curentă

    // Constructor gol (obligatoriu pentru JPA)
    public Comanda() {}

    public Comanda(String numeProduse, String client, double total, StareComanda stare) {
        this.numeProduse = numeProduse;
        this.client = client;
        this.total = total;
        this.stare = stare;
    }

    // Getteri și setteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeProduse() { return numeProduse; }
    public void setNumeProduse(String numeProduse) { this.numeProduse = numeProduse; }

    public String getClient() { return client; }
    public void setClient(String client) { this.client = client; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public StareComanda getStare() { return stare; }
    public void setStare(StareComanda stare) { this.stare = stare; }
}
