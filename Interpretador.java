import java.lang.System;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
 * Trabalho de SO
 * https://www.softorks.com/en/java/how-to-execute-shell-command-java-runtimeExec.php
 **/
class Interpretador {

    public static void main(String[] args)  throws IOException, InterruptedException{
       Interpretador i = new Interpretador();
       i.comando1();
    }

    public void comando1(){
        try {
            Process process = Runtime.getRuntime().exec("pwd");

            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;

            while((line = reader.readLine()) != null){
                output.append(line + "\n");
            }

            int exitVal = process.waitFor();
            if(exitVal == 0){
                System.out.println("Sucesso");
                System.out.println(output);
                System.exit(0);
            }else{
                System.out.println("Som");
            }
            
        } catch (IOException e) {
           e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    // metodos 
    public void comando2()  throws IOException, InterruptedException {

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