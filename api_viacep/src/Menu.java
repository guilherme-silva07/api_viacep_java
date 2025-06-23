import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {
    public static void MenuBuscaCep(){
        String opcao;

        do {
            System.out.println("Bem-vindo ao sistema de busca de CEP!");
            System.out.println("----------------------------------------");
            System.out.println("[1] Buscar CEP");
            System.out.println("[2] Sair");
            System.out.println("----------------------------------------");
            System.out.print("Escolha uma opção: ");
            Scanner scanner = new Scanner(System.in);
            opcao = scanner.nextLine();

            switch (opcao){
                case "1": buscarCep();
                    break;
            }

        } while (!opcao.equals("2"));

    }

    private static void buscarCep(){
        System.out.println("Digite o CEP que deseja buscar (apenas números):");
        Scanner scanner = new Scanner(System.in);
        String cep = scanner.nextLine();

        Pattern pattern = Pattern.compile("\\d{8}");
        Matcher matcher = pattern.matcher(cep);

        if (cep.length() != 8 || !matcher.matches()) {
            System.out.println("CEP inválido. Certifique-se de digitar 8 dígitos.");
            return;
        }

        try {
            String endereco = ViaCepAPI.buscaEnderecoPorCep(cep);
            if (endereco != null) {
                System.out.println("Endereço encontrado: ");
                System.out.println(endereco);
            } else {
                System.out.println("Nenhum endereço encontrado para o CEP informado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar o CEP: " + e.getMessage());
        }
    }
}
