import java.io.*;
import java.util.Scanner;
import gauss.Gauss;
import matriz.Matriz;
import arquivo.LeitorArquivoMatriz;

public class Programa
{
    public static void main(String[] args)
    {
        try
        {
            System.out.println("======================================================");
            System.out.println("     Resolução de sistemas de equações do 1º grau     ");
            System.out.println("======================================================\n");

            Scanner scanEnter = new Scanner(System.in);
            System.out.println("Qual o endereço do arquivo onde se localiza o sistema?");
            String arquivoEntrada = scanEnter.nextLine();
            System.out.println("\n");

            LeitorArquivoMatriz arquivo = new LeitorArquivoMatriz(arquivoEntrada);
            Matriz matriz = new Matriz (arquivo.lerMatriz());
            Gauss gauss = new Gauss(matriz);

            System.out.println(gauss);

            System.out.println("\n");
            System.out.println("Aperte enter para calcular...\n");
            scanEnter.nextLine();

            gauss.calcular();
        }
        catch (IOException erro)
        {
            System.err.println("Erro no arquivo .txt: " + erro);
        }
        catch (Exception erro)
        {
            System.err.println("Erro:" + erro);
        }

    }
}

