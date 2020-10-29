import java.lang.System;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
 * Trabalho de Sistemas Operacionais Referência:
 * https://www.softorks.com/en/java/how-to-execute-shell-command-java-runtimeExec.php
 **/
class Interpretador {
    // atributo sinalizador de saída do terminal Shell de comandos
    public boolean sair = false;

    public static void main(String[] args) throws IOException, InterruptedException {

        // instanciando um interpretador de comandos i
        Interpretador i = new Interpretador();

        // importar um scanner para a entrada do teclado
        Scanner input = new Scanner(System.in);

        // criar uma string onde serão armazenados os comandos pelo usuario
        String comando;

        String path = i.pwd_start();
        while (i.sair == false) {

            System.out.print(path + "$ ");
            comando = input.nextLine();

            if (comando.equals("exit")) {
                i.exit();
            }

            if (comando.equals("teste")) {
                path = i.cd_entrar("teste"); // funciona
            }

        }

    }

    // Métodos para navegar entre os diretórios

    // $ cd caminho
    public String cd_entrar(String caminho) throws IOException, InterruptedException {
        // primeiro verifica se o diretorio exite
        File diretorio;

        diretorio = new File(caminho);
        if (diretorio.exists()) { // Se o diretório existir atualiza o path

            /* Algoritmo a ser implementado... */

            return "Diretório Encontrado!";

        } else { // Se o diretorio não existir retorna uma mensagem de erro

            System.err.print("Nao foi possivel encontrar o diretorio \n");
            return caminho;
        }
    }

    // cd ..
    public String cd_voltar(String caminho) throws IOException, InterruptedException {

        String path[] = caminho.split("/");

        String path_concat = "";
        for (int i = 0; i < path.length - 1; i++) {
            path_concat += path[i] + "/";
        }
        return path_concat;
    }

    // Método para mostrar o diretorio corrente do sistema

    public String pwd_start() {
        try {
            Process process = Runtime.getRuntime().exec("pwd");

            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null) {
                output.append(line);
            }

            return output.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    // Método para fechar os interpretador de comandos shell
    public void exit() {
        this.sair = true;
    }

    // Métodos para executar shells do sistema

    public void processo1(String comando) {
        try {
            Process process = Runtime.getRuntime().exec(comando);

            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null) {
                output.append(line);
            }

            System.out.println(output);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // metodos
    public void processo2() throws IOException, InterruptedException {

        String[] comand = { "ping", "google.com" };

        ProcessBuilder processBuilder = new ProcessBuilder(comand);
        processBuilder.directory(new File(System.getProperty("user.home")));

        try {
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.print(line + "\n");
            }

            int exitCode = process.waitFor();

            System.out.println("\n exited whith error code:" + exitCode);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}