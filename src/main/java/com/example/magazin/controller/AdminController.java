package com.example.magazin.controller;

import com.example.magazin.model.StareComanda;
import com.example.magazin.service.ComandaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ComandaService service;

    public AdminController(ComandaService service) {
        this.service = service;
    }

    // Dashboard admin — vede toate comenzile, poate filtra/căuta
    @GetMapping("/dashboard")
    public String dashboard(@RequestParam(required = false) StareComanda stare,
                            @RequestParam(required = false) String client,
                            Model model) {

        if (stare != null) {
            model.addAttribute("comenzi", service.getByStare(stare));
        } else if (client != null && !client.isBlank()) {
            model.addAttribute("comenzi", service.cautaDupaClient(client));
        } else {
            model.addAttribute("comenzi", service.getAll());
        }

        model.addAttribute("stari", service.getStari());
        model.addAttribute("stareSelectata", stare);

        // Notificări: comenzile care așteaptă procesare
        model.addAttribute("notificari", service.getByStare(StareComanda.PLASATA));

        return "admin/dashboard";
    }

    // Actualizare stare comandă
    @PostMapping("/comanda/{id}/stare")
    public String actualizeazaStare(@PathVariable Long id,
                                    @RequestParam StareComanda stareNoua) {
        service.actualizeazaStare(id, stareNoua);
        return "redirect:/admin/dashboard";
    }

    // Anulare comandă (doar PLASATA)
    @PostMapping("/comanda/{id}/anuleaza")
    public String anuleaza(@PathVariable Long id, Model model) {
        try {
            service.anuleaza(id);
        } catch (IllegalStateException e) {
            return "redirect:/admin/dashboard?eroare=nu-se-poate-anula";
        }
        return "redirect:/admin/dashboard";
    }
}

