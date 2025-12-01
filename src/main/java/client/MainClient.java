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

                    String json = """
                            {
                              "nome": "%s",
                              "email": "%s",
                              "password": "%s"
                            }
                            """.formatted(nome, email, pass);

                    HttpHeaders h = new HttpHeaders();
                    h.setContentType(MediaType.APPLICATION_JSON);

                    HttpEntity<String> req = new HttpEntity<>(json, h);

                    try {
                        String response = rest.postForObject(
                                BASE_URL + "/utilizadores",
                                req,
                                String.class
                        );
                        System.out.println("Utilizador criado:");
                        System.out.println(response);

                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
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

                    String json = "{ \"gestorId\": %d }".formatted(gestorId);

                    HttpHeaders h = new HttpHeaders();
                    h.setContentType(MediaType.APPLICATION_JSON);

                    HttpEntity<String> req = new HttpEntity<>(json, h);

                    try {
                        String resp = rest.postForObject(
                                BASE_URL + "/gestao-eventos/eventos/" + eventoId + "/aprovar",
                                req,
                                String.class
                        );
                        System.out.println("Evento aprovado:");
                        System.out.println(resp);
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

                    String json = """
                            {
                              "gestorId": %d,
                              "motivo": "%s"
                            }
                            """.formatted(gestorId, motivo);

                    HttpHeaders h = new HttpHeaders();
                    h.setContentType(MediaType.APPLICATION_JSON);
                    HttpEntity<String> req = new HttpEntity<>(json, h);

                    try {
                        String resp = rest.postForObject(
                                BASE_URL + "/gestao-eventos/eventos/" + eventoId + "/rejeitar",
                                req,
                                String.class
                        );
                        System.out.println("Evento rejeitado:");
                        System.out.println(resp);
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
                              "estudanteId": %d,
                              "eventoId": %d,
                              "nomeParticipante": "%s",
                              "email": "%s"
                            }
                            """.formatted(estudanteId, eventoId, nomeP, emailP);

                    HttpHeaders h = new HttpHeaders();
                    h.setContentType(MediaType.APPLICATION_JSON);
                    HttpEntity<String> req = new HttpEntity<>(json, h);

                    try {
                        String resp = rest.postForObject(
                                BASE_URL + "/gestao-eventos/inscricoes",
                                req,
                                String.class
                        );
                        System.out.println("Inscrição criada:");
                        System.out.println(resp);
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

                case 0 -> {
                    System.out.println("A sair...");
                    return;
                }

                default -> System.out.println("Opção inválida!");
            }
        }
    }
}