import gauss.Gauss;
import matriz.Matriz;

/**
A classe TesteGauss testa os métodos públicos da classe Gauss.
@author Rodrigo Smith Rodrigues.
@since 2019.
*/
public class TesteGauss
{
    public static void main(String[] args)
    {
		try
		{
			double[][] teste = new double[2][3];
			teste[0][0] = 11212;
			teste[0][1] = -1;
			teste[0][2] = 111.75;
			teste[1][0] = 4567;
			teste[1][1] = 1000;
			teste[1][2] = 00001;

			double[][] teste2 = new double[2][3];
			teste2[0][0] = 12;
			teste2[0][1] = -1;
			teste2[0][2] = 1.7875;
			teste2[1][0] = 457;
			teste2[1][1] = 0;
			teste2[1][2] = 45569811;

			Matriz matriz = new Matriz(teste);
			Matriz matriz2 = new Matriz(teste2);

			Gauss gauss = new Gauss(matriz);
			Gauss gauss2 = new Gauss(matriz2);

			//testa o getter:
			System.out.println(gauss.getMatriz());

			//testa o setter:
			gauss.setMatriz(matriz2);
			System.out.println(gauss.getMatriz());

			gauss.calcular();

			System.out.println("testando os métodos obrigatórios:\n");

			System.out.println("o toString() já está sendo testado!\n");

			//testa o equals()
			System.out.println("equals():\n");
			System.out.println(gauss.equals(gauss2));

			gauss.setMatriz(matriz);

			System.out.println(gauss.equals(gauss2) + "\n");

			//testa o hashCode()
			System.out.println("hashCode():\n");
			System.out.println(gauss.hashCode());
			System.out.println(gauss2.hashCode() + "\n");

			//testa o clone() (e construtor de cópia)
			System.out.println("clone():\n");
			Gauss clone = (Gauss)gauss.clone();
			System.out.println(clone);
		}
		catch (Exception erro)
		{}
    }
}
