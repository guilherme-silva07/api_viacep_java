import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {
    private static List<Endereco> enderecos = new ArrayList<>();

    public static void MenuBuscaCep() {
        String opcao;

        do {
            System.out.println("Bem-vindo ao sistema de busca de CEP!");
            System.out.println("----------------------------------------");
            System.out.println("[1] Buscar CEP");
            System.out.println("[2] Listar Endereços");
            System.out.println("[3] Limpar Endereços");
            System.out.println("[4] Sair");
            System.out.println("----------------------------------------");
            System.out.print("Escolha uma opção: ");
            Scanner scanner = new Scanner(System.in);
            opcao = scanner.nextLine();

            switch (opcao){
                case "1": buscarCep();
                    break;
                case "2": listarCep();
                    break;
                case "3": limparCep();
                    break;
                default: salvarCep();
                    break;
            }

        } while (!opcao.equals("4"));

    }

    private static void limparCep() {
        File arquivo = new File("enderecos.txt");

        if(!arquivo.exists()) {
            System.out.println("Nenhum endereço encontrado. Por favor, busque um CEP primeiro.");
            return;
        }
        try {
            if(arquivo.delete()) System.out.println("Arquivo de endereços excluído com sucesso.");
        } catch (SecurityException e) {
            System.out.println("Erro ao tentar excluir o arquivo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado ao tentar excluir o arquivo: " + e.getMessage());
        }
    }

    private static void listarCep() {
        File arquivo = new File("enderecos.txt");

        if(!arquivo.exists()) {
            System.out.println("Nenhum endereço encontrado. Por favor, busque um CEP primeiro.");
            return;
        }

        try {

            BufferedReader reader = new BufferedReader(new FileReader(arquivo));

            while (reader.ready()) {
                String linha = reader.readLine();
                if (linha != null && !linha.trim().isEmpty()) {
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    Endereco endereco = gson.fromJson(linha, Endereco.class);
                    exibeEndereco(endereco);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    private static void salvarCep() {
        try {
            File arquivo = new File("enderecos.txt");
            FileWriter writer = new FileWriter(arquivo, true);
            for(Endereco end : enderecos) {
                exibeEndereco(end);
                Gson gson = new GsonBuilder().create();
                writer.write(gson.toJson(end) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
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

        Endereco endereco = ViaCepAPI.buscaEnderecoPorCep(cep);
        if (endereco != null) {
            enderecos.add(endereco);
            System.out.println("Endereço encontrado com sucesso!");
        } else {
            System.out.println("Nenhum endereço encontrado para o CEP informado.");
        }
    }

    private static void exibeEndereco(Endereco endereco) {
        System.out.println("----------------------------------------");
        System.out.println("Endereço encontrado: " + endereco.getCep());
        System.out.println("Logradouro: " + endereco.getLogradouro());
        System.out.println("Complemento: " + endereco.getComplemento());
        System.out.println("Bairro: " + endereco.getBairro());
        System.out.println("Localidade: " + endereco.getLocalidade());
        System.out.println("UF: " + endereco.getUf());
        System.out.println("Estado: " + endereco.getEstado());
        System.out.println("Unidade: " + endereco.getUnidade());
        System.out.println("Região: " + endereco.getRegiao());
        System.out.println("IBGE: " + endereco.getIbge());
        System.out.println("GIA: " + endereco.getGia());
        System.out.println("DDD: " + endereco.getDdd());
        System.out.println("SIAFI: " + endereco.getSiafi());
        System.out.println("----------------------------------------");
    }
}
