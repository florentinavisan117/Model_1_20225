package com.example.magazin.controller;

import com.example.magazin.dto.ComandaDTO;
import com.example.magazin.model.StareComanda;
import com.example.magazin.service.ComandaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client")
public class ClientController {

    private final ComandaService service;

    public ClientController(ComandaService service) {
        this.service = service;
    }

    // Dashboard client — vede doar comenzile lui, poate plasa una nouă
    @GetMapping("/dashboard")
    public String dashboard(@RequestParam(required = false) String client,
                            Model model) {

        if (client != null && !client.isBlank()) {
            model.addAttribute("comenzi", service.cautaDupaClient(client));
            model.addAttribute("clientCurent", client);
            model.addAttribute("notificariClient", service.getNotificariPentruClient(client));
        } else {
            model.addAttribute("comenzi", java.util.Collections.emptyList());
            model.addAttribute("notificariClient", java.util.Collections.emptyList());
        }

        model.addAttribute("comanda", new ComandaDTO());
        model.addAttribute("stari", service.getStari());
        return "client/dashboard";
    }

    // Plasare comandă nouă
    @PostMapping("/comanda/adauga")
    public String adaugaComanda(@ModelAttribute ComandaDTO dto) {
        // Clientul plasează mereu cu starea PLASATA
        dto.setStare(StareComanda.PLASATA);
        service.salveaza(dto);
        // Redirecționăm înapoi la comenzile clientului
        return "redirect:/client/dashboard?client=" + dto.getClient();
    }

    // Anulare comandă proprie (doar PLASATA)
    @PostMapping("/comanda/{id}/anuleaza")
    public String anuleaza(@PathVariable Long id,
                           @RequestParam String clientNume) {
        try {
            service.anuleaza(id);
        } catch (IllegalStateException e) {
            return "redirect:/client/dashboard?client=" + clientNume + "&eroare=nu-se-poate-anula";
        }
        return "redirect:/client/dashboard?client=" + clientNume;
    }
}

