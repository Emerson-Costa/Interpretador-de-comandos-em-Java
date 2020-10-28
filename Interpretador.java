import java.lang.System;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
* Trabalho de SO
**/
class Interpretador{

    public static void main(String[] args) throws IOException, InterruptedException {

       String [] comand = {"ping", "google.com"};

       ProcessBuilder processBuilder = new ProcessBuilder(comand);
       processBuilder.directory(new File(System.getProperty("user.home")));
       
       try {
           Process process = processBuilder.start();

           BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

           String line;

           while((line = reader.readLine()) != null){
                System.out.print(line +"\n");
           }

           int exitCode = process.waitFor();

           System.out.println("\n exited whith error code:" + exitCode);

       } catch(IOException e) {
           e.printStackTrace();
       }
    }
}