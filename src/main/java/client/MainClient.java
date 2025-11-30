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
            System.out.println("\n  EVENTOS API CLIENT MENU ");
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
            }
          }
        }
    }