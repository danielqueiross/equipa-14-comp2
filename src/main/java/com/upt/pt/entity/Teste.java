package com.upt.pt.entity;


import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Teste {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestaoEventos gestao = new GestaoEventos();
        int opcao;

        do {
            System.out.println("\n MENU GESTÃO DE EVENTOS");
            System.out.println("1  - Registar Utilizador (Estudante / Organizador / Gestor)");
            System.out.println("2  - Autenticar Utilizador");
            System.out.println("3  - Criar Tipo de Evento");
            System.out.println("4  - Criar Evento");
            System.out.println("5  - Listar Eventos");
            System.out.println("6  - Editar Evento");
            System.out.println("7  - Fazer Inscrição");
            System.out.println("8  - Listar Utilizadores");
            System.out.println("9  - Apagar Utilizador (escolha por número)");
            System.out.println("10 - Apagar Tipo (escolha por número)");
            System.out.println("11 - Apagar Evento (escolha por número)");
            System.out.println("12 - Apagar Inscrição (escolha por número)");
            System.out.println("13 - Aprovar Evento (Gestor)");
            System.out.println("14 - Rejeitar Evento (Gestor)");
            System.out.println("15 - Listar eventos com pouca adesão");
            System.out.println("0  - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1: // registar utilizador
                    System.out.print("Tipo de utilizador (estudante/organizador/gestor): ");
                    String tipo = sc.nextLine().trim();

                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Password: ");
                    String pass = sc.nextLine();

                    try {
                        Utilizador u = gestao.registarUtilizador(nome, email, pass, tipo);
                        System.out.println("Utilizador registado com sucesso: " + u);
                    } catch (Exception ex) {
                        System.out.println("Erro ao registar utilizador: " + ex.getMessage());
                    }
                    break;

                case 2: // autenticar
                    System.out.print("Email: ");
                    String e = sc.nextLine();
                    System.out.print("Password: ");
                    String p = sc.nextLine();

                    Utilizador user = gestao.autenticarUtilizador(e, p);
                    if (user != null)
                        System.out.println("Autenticação bem-sucedida! Bem-vindo, " + user.getNome());
                    else
                        System.out.println("Credenciais inválidas!");
                    break;

                case 3: // criar tipo
                    System.out.print("Nome do Tipo: ");
                    String tipoNome = sc.nextLine();
                    try {
                        gestao.criarTipo(tipoNome);
                        System.out.println("Tipo criado com sucesso!");
                    } catch (Exception ex) {
                        System.out.println("Erro ao criar tipo: " + ex.getMessage());
                    }
                    break;

                case 4: // criar evento
                    try {
                        System.out.print("Título: ");
                        String titulo = sc.nextLine();
                        System.out.print("Descrição: ");
                        String desc = sc.nextLine();
                        System.out.print("Data (AAAA-MM-DD): ");
                        LocalDate data = LocalDate.parse(sc.nextLine());
                        System.out.print("Lotação Máxima: ");
                        int lotacao = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Tipo de Evento: ");
                        String tipoEvento = sc.nextLine();
                        System.out.print("ID do Organizador: ");
                        int idOrg = sc.nextInt();
                        sc.nextLine();

                        Evento ev = gestao.criarEvento(titulo, desc, data, lotacao, tipoEvento, idOrg);
                        System.out.println("Evento criado com sucesso: " + ev);
                    } catch (Exception ex) {
                        System.out.println("Erro ao criar evento: " + ex.getMessage());
                    }
                    break;

                case 5: // listar eventos
                    List<Evento> eventos = gestao.listarEventos();
                    if (eventos.isEmpty())
                        System.out.println("Nenhum evento encontrado.");
                    else {
                        System.out.println("\nLISTA DE EVENTOS");
                        int i = 1;
                        for (Evento evento : eventos) {
                            System.out.println(i + " - " + evento);
                            i++;
                        }
                    }
                    break;

                case 6: // editar evento
                    try {
                        List<Evento> listaEv = gestao.listarEventos();
                        if (listaEv.isEmpty()) {
                            System.out.println("Não há eventos.");
                            break;
                        }
                        int idx = 1;
                        for (Evento evL : listaEv) {
                            System.out.println(idx + " - ID=" + evL.getId() + " - " + evL.getTitulo());
                            idx++;
                        }
                        System.out.print("Escolha número do evento a editar: ");
                        int escolhaEv = sc.nextInt();
                        sc.nextLine();
                        Evento alvoEv = listaEv.get(escolhaEv - 1);

                        System.out.print("Novo título: ");
                        String novoT = sc.nextLine();
                        System.out.print("Nova descrição: ");
                        String novaD = sc.nextLine();
                        System.out.print("Nova data (AAAA-MM-DD): ");
                        LocalDate novaData = LocalDate.parse(sc.nextLine());
                        System.out.print("Nova lotação: ");
                        int novaLot = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Novo tipo: ");
                        String novoTipo = sc.nextLine();

                        Evento editado = gestao.editarEvento(alvoEv.getId(), novoT, novaD, novaData, novaLot, novoTipo);
                        System.out.println("Evento atualizado: " + editado);
                    } catch (Exception ex) {
                        System.out.println("Erro ao editar evento: " + ex.getMessage());
                    }
                    break;

                case 7: // fazer inscrição
                    try {
                        System.out.print("ID do estudante: ");
                        int idEstudante = sc.nextInt();
                        System.out.print("ID do evento: ");
                        int idEvento = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Nome do participante: ");
                        String np = sc.nextLine();
                        System.out.print("Email: ");
                        String ep = sc.nextLine();

                        Inscricao ins = gestao.fazerInscricao(idEstudante, idEvento, np, ep);
                        System.out.println("Inscrição criada: " + ins);
                    } catch (Exception ex) {
                        System.out.println("Erro ao inscrever: " + ex.getMessage());
                    }
                    break;

                case 8: // listar utilizadores
                    List<Utilizador> utilizadores = gestao.listarUtilizadores();
                    if (utilizadores.isEmpty())
                        System.out.println("Nenhum utilizador encontrado.");
                    else {
                        System.out.println("\nLISTA DE UTILIZADORES");
                        int j = 1;
                        for (Utilizador u : utilizadores) {
                            System.out.println(j + " - " + u);
                            j++;
                        }
                    }
                    break;

                case 9: // apagar utilizador por número
                    List<Utilizador> lista = gestao.listarUtilizadores();
                    if (lista.isEmpty()) {
                        System.out.println("Não há utilizadores.");
                        break;
                    }
                    int index = 1;
                    for (Utilizador u : lista) {
                        System.out.println(index + " - " + u.getNome() +
                                " (ID=" + u.getId() + ", email=" + u.getEmail() + ")");
                        index++;
                    }
                    System.out.print("Escolha número do utilizador a apagar: ");
                    int escolha = sc.nextInt();
                    sc.nextLine();

                    Utilizador alvo = lista.get(escolha - 1);
                    boolean apagado = gestao.apagarUtilizador(alvo.getId());
                    System.out.println(apagado ? "Utilizador removido com sucesso!" : "Erro ao apagar utilizador.");
                    break;

                case 10: // apagar tipo por número
                    List<Tipo> tipos = gestao.listarTipos();
                    if (tipos.isEmpty()) {
                        System.out.println("Não há tipos.");
                        break;
                    }
                    int tIndex = 1;
                    for (Tipo t : tipos) {
                        System.out.println(tIndex + " - " + t.getNome() + " (ID=" + t.getId() + ")");
                        tIndex++;
                    }
                    System.out.print("Escolha número do tipo a apagar: ");
                    int escolhaTipo = sc.nextInt();
                    sc.nextLine();
                    Tipo alvoTipo = tipos.get(escolhaTipo - 1);
                    boolean apagadoTipo = gestao.apagarTipo(alvoTipo.getId());
                    System.out.println(apagadoTipo ? "Tipo removido com sucesso!" : "Erro ao apagar tipo.");
                    break;

                case 11: // apagar evento por número
                    List<Evento> listaEventos = gestao.listarEventos();
                    if (listaEventos.isEmpty()) {
                        System.out.println("Não há eventos.");
                        break;
                    }
                    int eIndex = 1;
                    for (Evento evL : listaEventos) {
                        System.out.println(eIndex + " - " + evL.getTitulo() +
                                " (ID=" + evL.getId() + ")");
                        eIndex++;
                    }
                    System.out.print("Escolha número do evento a apagar: ");
                    int escolhaEvDel = sc.nextInt();
                    sc.nextLine();
                    Evento alvoEvDel = listaEventos.get(escolhaEvDel - 1);
                    boolean apagadoEvento = gestao.apagarEvento(alvoEvDel.getId());
                    System.out.println(apagadoEvento ? "Evento removido com sucesso!" : "Erro ao apagar evento.");
                    break;

                case 12: // apagar inscrição por número (simples: pede ID)
                    System.out.print("ID da inscrição a cancelar: ");
                    int idInsc = sc.nextInt();
                    sc.nextLine();
                    boolean cancelada = gestao.cancelarInscricao(idInsc);
                    System.out.println(cancelada ? "Inscrição cancelada com sucesso!" : "Inscrição não encontrada!");
                    break;

                case 13: // aprovar evento
                    try {
                        System.out.print("ID do gestor: ");
                        int idGestor = sc.nextInt();
                        System.out.print("ID do evento a aprovar: ");
                        int idEvAprovar = sc.nextInt();
                        sc.nextLine();

                        gestao.aprovarEvento(idEvAprovar, idGestor);
                        System.out.println("Evento aprovado com sucesso!");
                    } catch (Exception ex) {
                        System.out.println("Erro ao aprovar evento: " + ex.getMessage());
                    }
                    break;

                case 14: // rejeitar evento
                    try {
                        System.out.print("ID do gestor: ");
                        int idGestorR = sc.nextInt();
                        System.out.print("ID do evento a rejeitar: ");
                        int idEvRejeitar = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Motivo da rejeição: ");
                        String motivo = sc.nextLine();

                        gestao.rejeitarEvento(idEvRejeitar, idGestorR, motivo);
                        System.out.println("Evento rejeitado com sucesso!");
                    } catch (Exception ex) {
                        System.out.println("Erro ao rejeitar evento: " + ex.getMessage());
                    }
                    break;

                case 15: // eventos com pouca adesão
                    System.out.print("Limite de participantes: ");
                    int limite = sc.nextInt();
                    sc.nextLine();
                    List<Evento> poucos = gestao.eventosComPoucaAdesao(limite);
                    if (poucos.isEmpty()) {
                        System.out.println("Nenhum evento com menos de " + limite + " participantes.");
                    } else {
                        System.out.println("Eventos com pouca adesão:");
                        for (Evento evP : poucos) {
                            System.out.println(evP);
                        }
                    }
                    break;

                case 0:
                    System.out.println("A sair...");
                    gestao.fechar();
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }
}

