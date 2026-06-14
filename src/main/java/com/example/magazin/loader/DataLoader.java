package com.example.magazin.loader;

import com.example.magazin.model.Comanda;
import com.example.magazin.model.StareComanda;
import com.example.magazin.repository.ComandaRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
public class DataLoader implements ApplicationRunner {

    private final ComandaRepository repo;

    public DataLoader(ComandaRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // Rulăm doar dacă tabelul e gol (evităm duplicate la restart)
        if (repo.count() > 0) return;

        // Citim fișierul din resources/
        ClassPathResource resource = new ClassPathResource("comenzi.txt");

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {

            String linie;
            while ((linie = reader.readLine()) != null) {

                // Sărim liniile goale sau comentarii
                if (linie.isBlank() || linie.startsWith("#")) continue;

                // Separăm câmpurile după |
                String[] campuri = linie.split("\\|");
                if (campuri.length != 4) continue; // linie invalidă

                String client      = campuri[0].trim();
                String numeProduse = campuri[1].trim();
                double total       = Double.parseDouble(campuri[2].trim());
                StareComanda stare = StareComanda.valueOf(campuri[3].trim());

                Comanda c = new Comanda(numeProduse, client, total, stare);
                repo.save(c);
            }

            System.out.println("✅ Date încărcate din comenzi.txt");
        }
    }
}

