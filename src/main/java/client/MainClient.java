package client;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

public class MainClient {

    private static final String BASE_URL = "http://localhost:8080/api";
    private static final RestTemplate rest = new RestTemplate();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n=== EVENTOS API CLIENT MENU ===");
            System.out.println("1. Criar Utilizador");
            System.out.println("2. Criar Tipo de Evento");
            System.out.println("3. Criar Evento");
            System.out.println("4. Aprovar Evento");
            System.out.println("5. Rejeitar Evento");
            System.out.println("6. Inscrever Estudante");
            System.out.println("7. Listar Eventos");
            System.out.println("8. Listar Utilizadores");
            System.out.println("9. Apagar Utilizador");
            System.out.println("10. Apagar Evento");  
            System.out.println("11. Listar Eventos com Pouca Aderência");
            System.out.println("12. Cancelar Inscrição");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            int option = Integer.parseInt(sc.nextLine());

            switch (option) {
            case 1 -> {
                System.out.print("Nome: ");
                String nome = sc.nextLine();

                System.out.print("Email: ");
                String email = sc.nextLine();

                System.out.print("Password: ");
                String pass = sc.nextLine();

                System.out.print("Tipo (estudante / organizador / gestor): ");
                String tipo = sc.nextLine();

                String json = """
                {
                  "nome": "%s",
                  "email": "%s",
                  "password": "%s",
                  "tipo": "%s"
                }
                """.formatted(nome, email, pass, tipo);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                HttpEntity<String> request = new HttpEntity<>(json, headers);

                try {
                    String response = rest.postForObject(
                            BASE_URL + "/utilizadores",
                            request,
                            String.class
                    );
                    System.out.println("\nUtilizador criado:");
                    System.out.println(response);

                } catch (Exception e) {
                    System.out.println("Erro ao criar utilizador: " + e.getMessage());
                }
               }
                case 2 -> {
                    System.out.print("Nome do Tipo: ");
                    String nomeTipo = sc.nextLine();

                    String json = "{ \"nome\": \"%s\" }".formatted(nomeTipo);

                    HttpHeaders h = new HttpHeaders();
                    h.setContentType(MediaType.APPLICATION_JSON);
                    HttpEntity<String> req = new HttpEntity<>(json, h);

                    try {
                        String resp = rest.postForObject(
                                BASE_URL + "/tipos",
                                req,
                                String.class
                        );
                        System.out.println("Tipo criado:");
                        System.out.println(resp);

                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                case 3 -> {
                    System.out.print("Organizador ID: ");
                    long orgId = Long.parseLong(sc.nextLine());

                    System.out.print("Tipo ID: ");
                    long tipoId = Long.parseLong(sc.nextLine());

                    System.out.print("Título: ");
                    String titulo = sc.nextLine();

                    System.out.print("Descrição: ");
                    String descricao = sc.nextLine();

                    System.out.print("Data (AAAA-MM-DD): ");
                    String data = sc.nextLine();

                    System.out.print("Lotação Máxima: ");
                    int lot = Integer.parseInt(sc.nextLine());

                    String json = """
                            {
                              "titulo": "%s",
                              "descricao": "%s",
                              "data": "%s",
                              "lotacaoMax": %d
                            }
                            """.formatted(titulo, descricao, data, lot);

                    HttpHeaders h = new HttpHeaders();
                    h.setContentType(MediaType.APPLICATION_JSON);

                    HttpEntity<String> req = new HttpEntity<>(json, h);

                    String url = BASE_URL +
                            "/gestao-eventos/eventos?organizadorId=" +
                            orgId + "&tipoId=" + tipoId;

                    try {
                        String resp = rest.postForObject(url, req, String.class);
                        System.out.println("Evento criado:");
                        System.out.println(resp);
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                case 4 -> {
                    System.out.print("ID do evento: ");
                    long eventoId = Long.parseLong(sc.nextLine());

                    System.out.print("ID do gestor: ");
                    long gestorId = Long.parseLong(sc.nextLine());

                    String url = BASE_URL + "/gestao-eventos/eventos/" + eventoId + "/aprovar?gestorId=" + gestorId;

                    try {
                        String response = rest.postForObject(url, null, String.class);
                        System.out.println("\nEvento aprovado:");
                        System.out.println(response);

                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                case 5 -> {
                    System.out.print("ID do evento: ");
                    long eventoId = Long.parseLong(sc.nextLine());

                    System.out.print("ID do gestor: ");
                    long gestorId = Long.parseLong(sc.nextLine());

                    System.out.print("Motivo: ");
                    String motivo = sc.nextLine();

                    String url = BASE_URL + "/gestao-eventos/eventos/" + eventoId
                            + "/rejeitar?gestorId=" + gestorId
                            + "&motivo=" + motivo.replace(" ", "%20");

                    try {
                        String response = rest.postForObject(url, null, String.class);
                        System.out.println("\nEvento rejeitado:");
                        System.out.println(response);

                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                case 6 -> {
                    System.out.print("ID Estudante: ");
                    long estudanteId = Long.parseLong(sc.nextLine());

                    System.out.print("ID Evento: ");
                    long eventoId = Long.parseLong(sc.nextLine());

                    System.out.print("Nome Participante: ");
                    String nomeP = sc.nextLine();

                    System.out.print("Email: ");
                    String emailP = sc.nextLine();

                    String json = """
                    {
                      "nomeParticipante": "%s",
                      "email": "%s"
                    }
                    """.formatted(nomeP, emailP);

                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);

                    HttpEntity<String> request = new HttpEntity<>(json, headers);

                    String url = BASE_URL + "/gestao-eventos/inscricoes?estudanteId=" 
                                 + estudanteId + "&eventoId=" + eventoId;

                    try {
                        String response = rest.postForObject(url, request, String.class);
                        System.out.println("\nInscrição criada:");
                        System.out.println(response);

                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                case 7 -> {
                    try {
                        String resp = rest.getForObject(BASE_URL + "/eventos", String.class);
                        System.out.println("\nEventos:");
                        System.out.println(resp);
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                case 8 -> {
                    try {
                        String resp = rest.getForObject(BASE_URL + "/utilizadores", String.class);
                        System.out.println("\nUtilizadores:");
                        System.out.println(resp);
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                case 9 -> {
                    System.out.print("ID do utilizador a apagar: ");
                    long id = Long.parseLong(sc.nextLine());

                    String url = BASE_URL + "/utilizadores/" + id;

                    try {
                        rest.delete(url);
                        System.out.println("\nUtilizador apagado com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro ao apagar utilizador: " + e.getMessage());
                    }
                }
                case 10 -> {
                    System.out.print("ID do evento a apagar: ");
                    long id = Long.parseLong(sc.nextLine());

                    String url = BASE_URL + "/eventos/" + id;

                    try {
                        rest.delete(url);
                        System.out.println("\nEvento apagado com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro ao apagar evento: " + e.getMessage());
                    }
                }
                case 11 -> {
                    String url = BASE_URL + "/gestao-eventos/eventos/pouca-adesao";

                    try {
                        String response = rest.getForObject(url, String.class);
                        System.out.println("\nEventos com pouca adesão (< 50% da lotação):");
                        System.out.println(response);
                    } catch (Exception e) {
                        System.out.println("Erro ao listar eventos com pouca adesão: " + e.getMessage());
                    }
                }
                case 12 -> {
                    System.out.print("ID da inscrição a cancelar: ");
                    long id = Long.parseLong(sc.nextLine());

                    String url = BASE_URL + "/gestao-eventos/inscricoes/" + id;

                    try {
                        rest.delete(url);
                        System.out.println("\nInscrição cancelada com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro ao cancelar inscrição: " + e.getMessage());
                    }
                }
                case 0 -> {
                    System.out.println("A sair...");
                    return;
                }

                default -> System.out.println("Opção inválida!");
            }
        }
    }
}