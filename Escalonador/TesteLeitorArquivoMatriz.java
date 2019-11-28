import arquivo.LeitorArquivoMatriz;;
import matriz.Matriz;
import java.io.IOException;

/**
A classe TesteArquivoMatriz testa os métodos públicos da classe ArquivoMatriz.
@author Rodrigo Smith Rodrigues.
@since 2019.
*/
public class TesteLeitorArquivoMatriz
{
    public static void main(String[] args)
    {
        try
        {
            //testa o construtor e o toString()
            LeitorArquivoMatriz arquivo = new LeitorArquivoMatriz("txt/teste.txt");
            LeitorArquivoMatriz arquivo2 = new LeitorArquivoMatriz("txt/teste2.txt");
            Matriz matriz = new Matriz(arquivo.lerMatriz());
            System.out.println(matriz);
            System.out.println(arquivo + "\n");

            //testa os setters e os getters
            arquivo.setEnderecoArquivo("txt/teste2.txt");
            System.out.println(arquivo.getEnderecoArquivo());
            arquivo.setEnderecoArquivo("txt/teste.txt");

            System.out.println("testando os métodos obrigatórios:\n");

            System.out.println("toString() já está sendo testado!\n");

            //testa o equals()
            System.out.println("equals():\n");
            System.out.println(arquivo.equals(arquivo2));
            arquivo.setEnderecoArquivo("txt/teste2.txt");
            System.out.println(arquivo.equals(arquivo2));

            //testa o hashCode()
            System.out.println("hashCode():\n");
            System.out.println(arquivo.hashCode());
            arquivo2.setEnderecoArquivo("txt/teste.txt");
            System.out.println(arquivo2.hashCode() + "\n");

            //testa o clone() (e construtor de cópia)
            System.out.println("clone():\n");
            LeitorArquivoMatriz clone = (LeitorArquivoMatriz)arquivo.clone();
            System.out.println(clone  + "\n");
        }
        catch (IOException erro)
        {
            System.err.println("Erro no arquivo .txt: " + erro);
        }
        catch (Exception erro)
        {
            System.err.println("Erro: " + erro);
        }
    }
}
