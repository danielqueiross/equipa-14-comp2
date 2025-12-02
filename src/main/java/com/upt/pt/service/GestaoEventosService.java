package com.upt.pt.service;

import com.upt.pt.entity.Estudante;
import com.upt.pt.entity.Evento;
import com.upt.pt.entity.Gestor;
import com.upt.pt.entity.Inscricao;
import com.upt.pt.entity.Organizador;
import com.upt.pt.entity.Tipo;
import com.upt.pt.repository.EstudanteRepository;
import com.upt.pt.repository.EventoRepository;
import com.upt.pt.repository.GestorRepository;
import com.upt.pt.repository.InscricaoRepository;
import com.upt.pt.repository.OrganizadorRepository;
import com.upt.pt.repository.TipoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GestaoEventosService {

    private final EventoRepository eventoRepository;
    private final GestorRepository gestorRepository;
    private final EstudanteRepository estudanteRepository;
    private final OrganizadorRepository organizadorRepository;
    private final TipoRepository tipoRepository;
    private final InscricaoRepository inscricaoRepository;

    public GestaoEventosService(EventoRepository eventoRepository,
                                GestorRepository gestorRepository,
                                EstudanteRepository estudanteRepository,
                                OrganizadorRepository organizadorRepository,
                                TipoRepository tipoRepository,
                                InscricaoRepository inscricaoRepository) {
        this.eventoRepository = eventoRepository;
        this.gestorRepository = gestorRepository;
        this.estudanteRepository = estudanteRepository;
        this.organizadorRepository = organizadorRepository;
        this.tipoRepository = tipoRepository;
        this.inscricaoRepository = inscricaoRepository;
    }

    // Criar evento com ligação a Organizador + Tipo
    public Evento criarEvento(Long organizadorId, Long tipoId, Evento dados) {

        Optional<Organizador> optOrg = organizadorRepository.findById(organizadorId);
        Optional<Tipo> optTipo = tipoRepository.findById(tipoId);

        if (optOrg.isEmpty() || optTipo.isEmpty()) {
            return null;
        }

        Organizador org = optOrg.get();
        Tipo tipo = optTipo.get();

        Evento e = new Evento();
        e.setTitulo(dados.getTitulo());
        e.setDescrição(dados.getDescrição());
        e.setData(dados.getData());
        e.setLotaçãoMax(dados.getLotaçãoMax());
        e.setEstado("Pendente");
        e.setNumParticipantes(0);
        e.setOrganizador(org);
        e.setTipo(tipo);

        return eventoRepository.save(e);
    }
    // Aprovar evento (só se existir gestor)

    public Evento aprovarEvento(Long eventoId, Long gestorId) {

        Optional<Gestor> optGestor = gestorRepository.findById(gestorId);
        if (optGestor.isEmpty()) {
            return null; // gestor não existe
        }

        Optional<Evento> optEvento = eventoRepository.findById(eventoId);
        if (optEvento.isEmpty()) {
            return null;
        }

        Evento e = optEvento.get();
        e.setEstado("Aprovado");
        e.setMotivoRejeição(null);

        return eventoRepository.save(e);
    }
    // Rejeitar evento com motivo
    public Evento rejeitarEvento(Long eventoId, Long gestorId, String motivo) {

        Optional<Gestor> optGestor = gestorRepository.findById(gestorId);
        if (optGestor.isEmpty()) {
            return null;
        }

        Optional<Evento> optEvento = eventoRepository.findById(eventoId);
        if (optEvento.isEmpty()) {
            return null;
        }

        Evento e = optEvento.get();
        e.setEstado("Rejeitado");
        e.setMotivoRejeição(motivo);

        return eventoRepository.save(e);
    }

    // Listar eventos com pouca adesão
    public List<Evento> eventosComPoucaAdesao() {
        return eventoRepository.findAll()
                .stream()
                .filter(e -> e.getNumParticipantes() < (e.getLotaçãoMax() * 0.5))
                .toList();
    }

    // Fazer inscrição (estudante + evento)
    public Inscricao fazerInscricao(Long estudanteId, Long eventoId,
                                    String nomeParticipante, String email) {

        Optional<Estudante> optEst = estudanteRepository.findById(estudanteId);
        Optional<Evento> optEv = eventoRepository.findById(eventoId);

        if (optEst.isEmpty() || optEv.isEmpty()) {
            return null;
        }

        Estudante est = optEst.get();
        Evento ev = optEv.get();

        // verifica lotação
        if (ev.getNumParticipantes() >= ev.getLotaçãoMax()) {
            return null; // ou lançar exceção
        }

        Inscricao ins = new Inscricao(nomeParticipante, email);
        ins.setEstudante(est);
        ins.setEvento(ev);

        // atualiza contagem local
        ev.setNumParticipantes(ev.getNumParticipantes() + 1);

        inscricaoRepository.save(ins);
        eventoRepository.save(ev);

        return ins;
    }

    // Cancelar inscrição
    public boolean cancelarInscricao(Long inscricaoId) {

        Optional<Inscricao> optInsc = inscricaoRepository.findById(inscricaoId);
        if (optInsc.isEmpty()) {
            return false;
        }

        Inscricao ins = optInsc.get();
        Evento ev = ins.getEvento();

        if (ev != null) {
            int atual = ev.getNumParticipantes();
            ev.setNumParticipantes(Math.max(0, atual - 1));
            eventoRepository.save(ev);
        }

        inscricaoRepository.delete(ins);
        return true;
    }
}
