package com.example.magazin.service;


import com.example.magazin.dto.ComandaDTO;
import com.example.magazin.model.Comanda;
import com.example.magazin.model.Notificare;
import com.example.magazin.model.StareComanda;
import com.example.magazin.repository.ComandaRepository;
import com.example.magazin.repository.NotificareRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
    public class ComandaService {

        private final ComandaRepository repo;
        private final NotificareRepository notificareRepo;
        private final ModelMapper mapper;

        // Injectare prin constructor (buna practica)
        public ComandaService(ComandaRepository repo, NotificareRepository notificareRepo, ModelMapper mapper) {
            this.repo = repo;
            this.notificareRepo = notificareRepo;
            this.mapper = mapper;
        }

        // Toate comenzile → convertite în DTO
        public List<ComandaDTO> getAll() {
            return repo.findAll()
                    .stream()
                    .map(c -> mapper.map(c, ComandaDTO.class))
                    .collect(Collectors.toList());
        }

        // Filtrare după stare
        public List<ComandaDTO> getByStare(StareComanda stare) {
            return repo.findByStare(stare)
                    .stream()
                    .map(c -> mapper.map(c, ComandaDTO.class))
                    .collect(Collectors.toList());
        }

        // Căutare după client
        public List<ComandaDTO> cautaDupaClient(String client) {
            return repo.findByClientContainingIgnoreCase(client)
                    .stream()
                    .map(c -> mapper.map(c, ComandaDTO.class))
                    .collect(Collectors.toList());
        }

        // O singură comandă după ID
        public ComandaDTO getById(Long id) {
            Comanda c = repo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Comanda negăsită: " + id));
            return mapper.map(c, ComandaDTO.class);
        }

        // Salvare (adăugare sau editare)
        public void salveaza(ComandaDTO dto) {
            Comanda c = mapper.map(dto, Comanda.class);
            repo.save(c);
        }

        // Actualizare stare
        public void actualizeazaStare(Long id, StareComanda stareNoua) {
            Comanda c = repo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Comanda negăsită: " + id));

            c.setStare(stareNoua);
            repo.save(c);

            // 🔔 Aici trimitem/salvăm notificarea pentru client
            String mesajNotificare = "Comanda ta #" + c.getId() + " (" + c.getNumeProduse() + ") a fost actualizată la starea: " + stareNoua;
            Notificare n = new Notificare(c.getClient(), mesajNotificare);
            notificareRepo.save(n);
        }

    public List<Notificare> getNotificariPentruClient(String client) {
        return notificareRepo.findByClient(client);
    }

        // Anulare = ștergere
        public void anuleaza(Long id) {
            Comanda c = repo.findById(id).orElseThrow(() -> new RuntimeException("Comanda negăsită: " + id));

            if(c.getStare() != StareComanda.PLASATA){
                throw new IllegalStateException("Doar comenzile în starea PLASATA pot fi anulate.");
            }

            repo.deleteById(id);
        }

        // Toate starile posibile (pentru dropdown)
        public List<StareComanda> getStari() {
            return Arrays.asList(StareComanda.values());
        }

    }

