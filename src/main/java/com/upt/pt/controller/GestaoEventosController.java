package com.upt.pt.controller;

import com.upt.pt.entity.Evento;
import com.upt.pt.entity.Inscricao;
import com.upt.pt.service.GestaoEventosService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gestao-eventos")
public class GestaoEventosController {

    private final GestaoEventosService gestaoEventosService;

    public GestaoEventosController(GestaoEventosService gestaoEventosService) {
        this.gestaoEventosService = gestaoEventosService;
    }


    @PostMapping("/eventos")
    public Evento criarEvento(@RequestParam Long organizadorId,
                              @RequestParam Long tipoId,
                              @RequestBody Evento dados) {
        return gestaoEventosService.criarEvento(organizadorId, tipoId, dados);
    }

    
    @PostMapping("/eventos/{eventoId}/aprovar")
    public Evento aprovarEvento(@PathVariable Long eventoId,
                                @RequestParam Long gestorId) {
        return gestaoEventosService.aprovarEvento(eventoId, gestorId);
    }

    @PostMapping("/eventos/{eventoId}/rejeitar")
    public Evento rejeitarEvento(@PathVariable Long eventoId,
                                 @RequestParam Long gestorId,
                                 @RequestParam String motivo) {
        return gestaoEventosService.rejeitarEvento(eventoId, gestorId, motivo);
    }


    @GetMapping("/eventos/pouca-adesao/{limite}")
    public List<Evento> eventosPoucaAdesao(@PathVariable int limite) {
        return gestaoEventosService.eventosComPoucaAdesao(limite);
    }

    @PostMapping("/inscricoes")
    public Inscricao fazerInscricao(@RequestParam Long estudanteId,
                                    @RequestParam Long eventoId,
                                    @RequestBody Inscricao dados) {

        return gestaoEventosService.fazerInscricao(
                estudanteId,
                eventoId,
                dados.getNomeParticipante(),
                dados.getEmail()
        );
    }

    @DeleteMapping("/inscricoes/{id}")
    public void cancelarInscricao(@PathVariable Long id) {
        gestaoEventosService.cancelarInscricao(id);
    }
}
