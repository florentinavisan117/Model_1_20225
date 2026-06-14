package com.example.magazin.dto;

import com.example.magazin.model.StareComanda;

// DTO = Data Transfer Object
// Separam datele din View de entitatea din baza de date
public class ComandaDTO {

    private Long id;
    private String numeProduse;
    private String client;
    private double total;
    private StareComanda stare;

    // Getteri și setteri (identici cu Comanda)
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





