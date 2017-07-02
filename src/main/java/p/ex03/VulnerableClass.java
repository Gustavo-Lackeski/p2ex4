package p.ex03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



public class VulnerableClass {
  private static void check(String str) {
    if (!str.matches("^[a-zA-Z0-9]*" + "\\.txt$")) {
      throw new IllegalArgumentException();
    }
  }
  
  public void vulnerableMethod(String filename) {
    int ix = 0;
    Scanner console = new Scanner(System.in);
    check(filename);
    //removendo loop infinito por causa do denial of service
    while (ix < 3) {
      System.out.print("Digite a operacao desejada para realizar "
          + "no arquivo <R para ler um arquivo, "
          + "W para escrever em um arquivo>? ");
      //System.out.println("teste1");
      String opr = console.nextLine();
      System.out.println(opr);
      if (opr.equals("R")) {
        BufferedReader br = null;
        FileReader fr = null;

        try {

          fr = new FileReader(filename);
          br = new BufferedReader(fr);

          String scurrentline;

          br = new BufferedReader(new FileReader(filename));

          while ((scurrentline = br.readLine()) != null) {
            System.out.println(scurrentline);
          }
          br.close();
          fr.close();

        } catch (IOException e) {

          e.printStackTrace();

        } 
      } else {
        if (opr.equals("W")) {
          BufferedWriter buffWrite;

          try {
            buffWrite = new BufferedWriter(new FileWriter(filename));
            String linha = "";
            System.out.println("Escreva algo: ");
            linha = console.nextLine();
            buffWrite.append(linha + "\n");
            buffWrite.close();

          } catch (IOException e) {
            e.printStackTrace();
          }
        } else {
          System.out.println("Escreve W ou R");
        }
      }
      ix++;
    }
    console.close();
  }
}
