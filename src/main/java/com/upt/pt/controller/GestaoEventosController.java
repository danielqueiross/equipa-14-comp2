package com.upt.pt.controller;

import com.upt.pt.dto.EventoDTO;
import com.upt.pt.dto.InscricaoDTO;
import com.upt.pt.entity.Evento;
import com.upt.pt.entity.Inscricao;
import com.upt.pt.mapper.EventoMapper;
import com.upt.pt.mapper.InscricaoMapper;
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

    // Criar evento
    @PostMapping("/eventos")
    public EventoDTO criarEvento(@RequestParam Long organizadorId,
                                 @RequestParam Long tipoId,
                                 @RequestBody EventoDTO dados) {

        Evento entity = EventoMapper.toEntity(dados);
        Evento created = gestaoEventosService.criarEvento(organizadorId, tipoId, entity);
        return EventoMapper.toDTO(created);
    }

    // Aprovar evento
    @PostMapping("/eventos/{eventoId}/aprovar")
    public EventoDTO aprovarEvento(@PathVariable Long eventoId,
                                   @RequestParam Long gestorId) {

        Evento e = gestaoEventosService.aprovarEvento(eventoId, gestorId);
        return EventoMapper.toDTO(e);
    }

    // Rejeitar evento 
    @PostMapping("/eventos/{eventoId}/rejeitar")
    public EventoDTO rejeitarEvento(@PathVariable Long eventoId,
                                    @RequestParam Long gestorId,
                                    @RequestParam String motivo) {

        Evento e = gestaoEventosService.rejeitarEvento(eventoId, gestorId, motivo);
        return EventoMapper.toDTO(e);
    }

    // Listar eventos com pouca adesão
    @GetMapping("/eventos/pouca-adesao")
    public List<Evento> eventosPoucaAdesao() {
        return gestaoEventosService.eventosComPoucaAdesao();
    }
    
    // Faz inscrição 
    @PostMapping("/inscricoes")
    public InscricaoDTO fazerInscricao(@RequestParam Long estudanteId,
                                       @RequestParam Long eventoId,
                                       @RequestBody InscricaoDTO dados) {

        Inscricao i = gestaoEventosService.fazerInscricao(
                estudanteId,
                eventoId,
                dados.getNomeParticipante(),
                dados.getEmail()
        );

        return InscricaoMapper.toDTO(i);
    }

    @DeleteMapping("/inscricoes/{id}")
    public void cancelarInscricao(@PathVariable Long id) {
        gestaoEventosService.cancelarInscricao(id);
    }
}
