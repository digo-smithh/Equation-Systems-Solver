import matriz.Matriz;

/**
A classe TesteMatriz testa os métodos públicos da classe Matriz.
@author Rodrigo Smith Rodrigues.
@since 2019.
*/
public class TesteMatriz
{
    public static void main(String[] args)
    {
        try
        {
            double[][] teste = new double[3][3];
            teste[0][0] = 1;
            teste[0][1] = -1;
            teste[0][2] = 1.7875;
            teste[1][0] = 45/67;
            teste[1][1] = 10000;
            teste[1][2] = 455495698;
            teste[2][0] = 14554.5454;
            teste[2][1] = 0;
            teste[2][2] = 00000000;

            //testa o construtor e o toString()
            Matriz matriz = new Matriz(teste);
            System.out.println(matriz);

            //testa os getters
            System.out.println("linhas: " + matriz.getM());
            System.out.println("colunas: " + matriz.getN() + "\n");

            double[][] teste2 = new double[2][2];
            teste2[0][0] = 8;
            teste2[0][1] = -1.85;
            teste2[1][0] = 87/6;
            teste2[1][1] = 100;

            //testa o setter
            matriz.setMatriz(teste2);
            System.out.println(matriz);

            System.out.println("linhas: " + matriz.getM());
            System.out.println("colunas: " + matriz.getN() + "\n");

            System.out.println("testando os métodos obrigatórios:\n");

            System.out.println("toString() já está sendo testado!\n");

            double[][] teste3 = new double[3][3];
            teste3[0][0] = 1;
            teste3[0][1] = -1;
            teste3[0][2] = 1.7875;
            teste3[1][0] = 45/67;
            teste3[1][1] = 10000;
            teste3[1][2] = 455495698;
            teste3[2][0] = 14554.5454;
            teste3[2][1] = 0;
            teste3[2][2] = 00000000;

            Matriz matriz2 = new Matriz(teste3);

            //testa o equals()
            System.out.println("equals():\n");
            System.out.println(matriz.equals(matriz2));

            double[][] teste4 = new double[2][2];
            teste4[0][0] = 8;
            teste4[0][1] = -1.85;
            teste4[1][0] = 86/6;
            teste4[1][1] = 100;

            matriz2.setMatriz(teste4);

            System.out.println(matriz.equals(matriz2) + "\n");

            //testa o hashCode()
            System.out.println("hashCode():\n");
            System.out.println(matriz.hashCode());
            System.out.println(matriz2.hashCode());
            matriz2.setValor(0, 0, 1);
            System.out.println(matriz2.hashCode() + "\n");

            //testa o clone() (e construtor de cópia)
            System.out.println("clone():\n");
            Matriz clone = (Matriz)matriz.clone();
            System.out.println(clone);
        }
        catch (Exception erro)
        {
            System.err.println("Erro:" + erro);
        }
    }
}
