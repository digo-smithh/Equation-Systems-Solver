package matriz;

/**
A classe Matriz representa uma matriz de double.
H� os atributos inteiros linhas e colunas, e a pr�pria matriz de double.
M�todos dessa classe permitem manipular os valores da matriz.
@author Rodrigo Smith Rodrigues.
@since 2019.
*/
public class Matriz implements Cloneable
{
    protected int m, n;
    protected double[][] matriz;

    /**
    Constroi uma nova inst�ncia da classe Matriz.
    Para tanto, deve ser fornecido uma matriz de double, contendo os valores desejados.
    @param matriz matriz bidimensional do tipo double.
    @throws Exception se a matriz for nula.
    */
    public Matriz (double[][] matriz) throws Exception
    {
        setMatriz(matriz);
        this.m = matriz.length;
        this.n = matriz[0].length;
    }

    /**
    Seta uma nova matriz para ser substitu�da na inst�ncia chamante.
    Para tanto, tamb�m substitui (se necess�rio), a quantidade de linhas e colunas da inst�ncia,
    dada a matriz passada como par�metro.
    @param matriz matriz bidimensional do tipo double.
    @throws Exception se a matriz for nula.
    */
    public void setMatriz(double[][] matriz) throws Exception
    {
        if (matriz == null)
            throw new Exception("Matriz inv�lida");
        this.matriz = matriz;
        this.m = matriz.length;
        this.n = matriz[0].length;
    }

    /**
    Seta um novo valor na matriz em um local espec�fico.
    @param i coordenada da linha m onde se localizar� o valor desejado.
    @param j coordenada da coluna n onde se localizar� o valor desejado.
    @param valor valor desejado a ser inserido na matriz.
    @throws Exception se a coordenada est� fora dos limites da matriz.
    */
    public void setValor(int i, int j, double valor) throws Exception
    {
        if (i > this.m || j > this.n)
            throw new Exception ("Coordenada fora dos limites da matriz");

        this.matriz[i][j] = valor;
    }

    /**
    Retorna a quantidade de linhas da matriz.
    @return um inteiro que representa quantas linhas a matriz possui.
    */
    public int getM ()
    {
        return this.m;
    }

    /**
    Retorna a quantidade de colunas da matriz.
    @return um inteiro que representa quantas colunas a matriz possui.
    */
    public int getN ()
    {
        return this.n;
    }

    /**
    Retorna a matriz de double, propriamente dita.
    @return uma matriz bidimensional de double.
    */
    public double[][] getMatriz ()
    {
        return this.matriz;
    }

    /**
    Retorna um valor da matriz, na coordenada desejada.
    @param i coordenada da linha m onde se localiza o valor desejado.
    @param j coordenada da coluna n onde se localiza o valor desejado.
    @return um double, que representa o valor contido na posi��o inserida.
    */
    public double getValor (int i, int j)
    {
        return this.matriz[i][j];
    }

    /**
    Retorna um vetor com os valores da diagonal principal.
    @return uma matriz unidimensional de double.
    */
    public double[] getDiagonalPrincipal ()
    {
        double[] diagonal = new double[this.m];
        int k = 0;
        for (int i = 0; i < this.m; i++)
        {
            for (int j = 0; j < this.n; j++)
            {
                if (i == j)
                {
                    diagonal[k] = this.matriz[i][j];
                    k++;
                }
            }
        }
        return diagonal;
    }

    /**
    Verifica a igualdade entre duas inst�ncias de Matriz.
    Verifica se o Object fornecido como par�metro representa uma
    matriz igual �quela representada pela inst�ncia a qual este
    m�todo for aplicado, resultando true em caso afirmativo,
    ou false, caso contr�rio.
    @param  obj o objeto a ser comparado com a inst�ncia a qual esse m�todo
            for aplicado.
    @return true, caso o Object fornecido ao m�todo e a inst�ncia chamante do
            m�todo representarem agendas iguais, ou false, caso contr�rio.
    */
    public boolean equals (Object obj)
    {
        if (this == obj)
                return true;

        if (obj == null)
                return false;

        if (this.getClass() != obj.getClass())
            return false;

        if (this.m != ((Matriz)obj).m || this.n != ((Matriz)obj).n) //n�o preciso de getter, pois estou dentro da classe Data
                return false;

        for (int i = 0; i < this.matriz.length; i++)
        {
            for (int i2 = 0; i2 < this.matriz.length; i2++)
            {
                if (this.matriz[i][i2] != ((Matriz)obj).matriz[i][i2])
                    return false;
            }
        }

        return true;
    }

     /**
    Calcula o c�digo de espalhamento (ou c�digo de hash) da classe Matriz.
    Calcula e resulta o c�digo de espalhamento (ou c�digo de hash, ou ainda o
    hashcode) da classe Matriz representada pela inst�ncia a qual o m�todo for aplicado.
    @return o c�digo de espalhamento da inst�ncia chamante do m�todo.
    */
    public int hashCode ()
    {
        int ret = 1; //qualquer positivo

        ret = ret * 7 + new Integer (this.m).hashCode ();
        ret = ret * 7 + new Integer (this.n).hashCode ();
        for (int i = 0; i < this.m; i++)
        {
            for (int i2 = 0; i2 < this.n; i2++)
            {
                ret = ret * 7 + new Double (this.matriz[i][i2]).hashCode ();
            }
        }

        if (ret < 0)
                ret = -ret;

        return ret;
    }

    /**
    Gera uma representa��o textual da classe Matriz.
    Produz e resulta um String com toda a matriz.
    @return um String contendo todo o conte�do da classe Matriz.
    */
    public String toString ()
    {
        String ret = "";

        for (int i = 0; i < this.m; i++)
        {
            for (int i2 = 0; i2 < this.n; i2++)
            {
                ret += "["+matriz[i][i2]+"] ";
                if (i2 == this.n - 1)
                    ret+="\n";
            }
        }

        return ret;
    }

    /**
    Copia os dados da inst�ncia atual para a inst�nicia passada.
    Para tanto, deve ser fornecida uma inst�ncia da classe Matriz para ser copiada.
    @param modelo a inst�ncia da classe Matriz a ser usada como modelo.
    @throws Exception se o modelo for null.
    */
    protected Matriz (Matriz modelo) throws Exception
    {
        if (modelo == null)
            throw new Exception ("Matriz inv�lida");

        this.matriz = modelo.matriz;
        this.m = modelo.m;
        this.n = modelo.n;
    }

    /**
    Constroi uma c�pia da inst�ncia da classe Matriz dada.
    Para tanto, chama o construtor de c�pia.
    @return retorna um objeto constru�do com todos os dados da inst�ncia chamante.
    */
    public Object clone ()
    {

        Object ret = null;

        try
        {
            ret = new Matriz (this);
        }
        catch (Exception erro)
        {}

        return ret;
    }
}
