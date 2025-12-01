package com.upt.pt.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.util.List;

public class GestaoEventos {

    private final SessionFactory factory;

    public GestaoEventos() {
        this.factory = new Configuration().configure().buildSessionFactory();
    }

    // AUXILIARES DE VALIDAÇÃO

    private boolean validarEmailPorTipo(String email, String tipo) {
        return switch (tipo.toLowerCase()) {
            case "estudante" -> email.endsWith("@estudante");
            case "organizador" -> email.endsWith("@organizador");
            case "gestor" -> email.endsWith("@gestor");
            default -> false;
        };
    }

    private String obterTipoUtilizador(Utilizador u) {
        if (u instanceof Estudante) return "estudante";
        if (u instanceof Organizador) return "organizador";
        if (u instanceof Gestor) return "gestor";
        return "desconhecido";
    }

    // CRUD UTILIZADOR

    public Utilizador registarUtilizador(String nome, String email, String password, String tipo) {

        if (!validarEmailPorTipo(email, tipo)) {
            throw new IllegalArgumentException("Email inválido para o tipo de utilizador.");
        }

        Utilizador u;

        switch (tipo.toLowerCase()) {
            case "estudante" -> u = new Estudante();
            case "organizador" -> u = new Organizador();
            case "gestor" -> u = new Gestor();
            default -> throw new IllegalArgumentException("Tipo de utilizador inválido.");
        }

        u.setNome(nome);
        u.setEmail(email);
        u.setPassword(password);

        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(u);
            tx.commit();
        }

        return u;
    }

    public Utilizador obterUtilizador(int id) {
        try (Session session = factory.openSession()) {
            return session.get(Utilizador.class, id);
        }
    }

    public List<Utilizador> listarUtilizadores() {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM Utilizador", Utilizador.class).getResultList();
        }
    }

    public Utilizador editarUtilizador(int id, String novoNome, String novoEmail, String novaPassword) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Utilizador u = session.get(Utilizador.class, id);
            if (u != null) {
                if (novoNome != null && !novoNome.isEmpty()) {
                    u.setNome(novoNome);
                }
                if (novoEmail != null && !novoEmail.isEmpty()) {
                    String tipo = obterTipoUtilizador(u);
                    if (!validarEmailPorTipo(novoEmail, tipo)) {
                        tx.rollback();
                        throw new IllegalArgumentException("Novo email inválido para o tipo " + tipo);
                    }
                    u.setEmail(novoEmail);
                }
                if (novaPassword != null && !novaPassword.isEmpty()) {
                    u.setPassword(novaPassword);
                }
                session.merge(u);
            }
            tx.commit();
            return u;
        }
    }

    public boolean apagarUtilizador(int id) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Utilizador u = session.get(Utilizador.class, id);
            if (u != null) {
                session.remove(u);
                tx.commit();
                return true;
            }
            tx.rollback();
            return false;
        }
    }

    // CRUD TIPO 

    public Tipo criarTipo(String nome) {
        Tipo t = new Tipo(nome);
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(t);
            tx.commit();
        }
        return t;
    }

    public List<Tipo> listarTipos() {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM Tipo", Tipo.class).getResultList();
        }
    }

    public boolean apagarTipo(int id) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Tipo t = session.get(Tipo.class, id);
            if (t != null) {
                session.remove(t);
                tx.commit();
                return true;
            }
            tx.rollback();
            return false;
        }
    }

    //  CRUD EVENTO 
    public Evento criarEvento(String titulo, String descricao, LocalDate data, int lotacaoMax,
                              String nomeTipo, int idOrganizador) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();

            Organizador org = session.get(Organizador.class, idOrganizador);
            if (org == null) {
                tx.rollback();
                throw new IllegalArgumentException("Organizador não encontrado");
            }

            Tipo tipo = session.createQuery("FROM Tipo t WHERE t.nome = :nome", Tipo.class)
                    .setParameter("nome", nomeTipo)
                    .uniqueResult();

            if (tipo == null) {
                tx.rollback();
                throw new IllegalArgumentException("Tipo de evento não encontrado");
            }

            Evento e = new Evento(titulo, descricao, data, lotacaoMax);
            e.setOrganizador(org);
            e.setTipo(tipo);

            session.persist(e);
            tx.commit();

            return e;
        }
    }

    public Evento editarEvento(int id, String novoTitulo, String novaDescricao, LocalDate novaData,
                               Integer novaLotacao, String novoTipoNome) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();

            Evento e = session.get(Evento.class, id);
            if (e == null) {
                tx.rollback();
                throw new IllegalArgumentException("Evento com ID " + id + " não encontrado");
            }

            if (novoTitulo != null && !novoTitulo.isEmpty()) e.setTitulo(novoTitulo);
            if (novaDescricao != null && !novaDescricao.isEmpty()) e.setDescrição(novaDescricao);
            if (novaData != null) e.setData(novaData);
            if (novaLotacao != null) e.setLotaçãoMax(novaLotacao);

            if (novoTipoNome != null && !novoTipoNome.isEmpty()) {
                Tipo novoTipo = session.createQuery("FROM Tipo t WHERE t.nome = :nome", Tipo.class)
                        .setParameter("nome", novoTipoNome)
                        .uniqueResult();
                if (novoTipo == null) {
                    tx.rollback();
                    throw new IllegalArgumentException("Tipo '" + novoTipoNome + "' não encontrado");
                }
                e.setTipo(novoTipo);
            }

            session.merge(e);
            tx.commit();

            return e;
        }
    }

    public List<Evento> listarEventos() {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM Evento", Evento.class).getResultList();
        }
    }

    public boolean apagarEvento(int id) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Evento e = session.get(Evento.class, id);
            if (e != null) {
                session.remove(e);
                tx.commit();
                return true;
            }
            tx.rollback();
            return false;
        }
    }

    //MÉTODOS DO GESTOR PARA EVENTOS

    public void aprovarEvento(int idEvento, int idGestor) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();

            Utilizador u = session.get(Utilizador.class, idGestor);
            if (!(u instanceof Gestor)) {
                tx.rollback();
                throw new IllegalArgumentException("Utilizador não é gestor.");
            }

            Evento e = session.get(Evento.class, idEvento);
            if (e == null) {
                tx.rollback();
                throw new IllegalArgumentException("Evento não encontrado.");
            }

            e.setEstado("Aprovado");
            e.setMotivoRejeição(null);

            session.merge(e);
            tx.commit();
        }
    }

    public void rejeitarEvento(int idEvento, int idGestor, String motivo) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();

            Utilizador u = session.get(Utilizador.class, idGestor);
            if (!(u instanceof Gestor)) {
                tx.rollback();
                throw new IllegalArgumentException("Utilizador não é gestor.");
            }

            Evento e = session.get(Evento.class, idEvento);
            if (e == null) {
                tx.rollback();
                throw new IllegalArgumentException("Evento não encontrado.");
            }

            e.setEstado("Rejeitado");
            e.setMotivoRejeição(motivo);

            session.merge(e);
            tx.commit();
        }
    }

    public List<Evento> eventosComPoucaAdesao(int limite) {
        try (Session session = factory.openSession()) {
            return session.createQuery(
                    "FROM Evento e WHERE e.numParticipantes < :limite", Evento.class)
                    .setParameter("limite", limite)
                    .getResultList();
        }
    }

    // INSCRIÇÃO 

    public Inscricao fazerInscricao(int estudanteId, int eventoId, String nomeParticipante, String email) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();

            Estudante est = session.get(Estudante.class, estudanteId);
            Evento ev = session.get(Evento.class, eventoId);

            if (est == null) {
                tx.rollback();
                throw new IllegalArgumentException("Estudante não encontrado");
            }
            if (ev == null) {
                tx.rollback();
                throw new IllegalArgumentException("Evento não encontrado");
            }

            if (ev.getNumParticipantes() >= ev.getLotaçãoMax()) {
                tx.rollback();
                throw new IllegalStateException("Evento já atingiu lotação máxima");
            }

            Inscricao i = new Inscricao(nomeParticipante, email);
            i.setEstudante(est);
            i.setEvento(ev);
            ev.adicionarInscricao(i);

            session.persist(i);
            session.merge(ev);
            tx.commit();

            return i;
        }
    }

    public boolean cancelarInscricao(int idInscricao) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();

            Inscricao i = session.get(Inscricao.class, idInscricao);
            if (i == null) {
                tx.rollback();
                return false;
            }

            Evento e = i.getEvento();
            if (e != null) e.removerInscricao(i);

            session.remove(i);
            session.merge(e);
            tx.commit();
            return true;
        }
    }

    // AUTENTICAÇÃO 

    public Utilizador autenticarUtilizador(String email, String password) {
        try (Session session = factory.openSession()) {
            return session.createQuery(
                    "FROM Utilizador u WHERE u.email = :email AND u.password = :password", Utilizador.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .uniqueResult();
        }
    }

    public void fechar() {
        factory.close();
    }
}
