package matriz;

/**
A classe Matriz representa uma matriz de double.
Há os atributos inteiros linhas e colunas, e a própria matriz de double.
Métodos dessa classe permitem manipular os valores da matriz.
@author Rodrigo Smith Rodrigues.
@since 2019.
*/
public class Matriz implements Cloneable
{
    protected int m, n;
    protected double[][] matriz;

    /**
    Constroi uma nova instância da classe Matriz.
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
    Seta uma nova matriz para ser substituída na instância chamante.
    Para tanto, também substitui (se necessário), a quantidade de linhas e colunas da instância,
    dada a matriz passada como parâmetro.
    @param matriz matriz bidimensional do tipo double.
    @throws Exception se a matriz for nula.
    */
    public void setMatriz(double[][] matriz) throws Exception
    {
        if (matriz == null)
            throw new Exception("Matriz inválida");
        this.matriz = matriz;
        this.m = matriz.length;
        this.n = matriz[0].length;
    }

    /**
    Seta um novo valor na matriz em um local especìfico.
    @param i coordenada da linha m onde se localizará o valor desejado.
    @param j coordenada da coluna n onde se localizará o valor desejado.
    @param valor valor desejado a ser inserido na matriz.
    @throws Exception se a coordenada está fora dos limites da matriz.
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
    @return um double, que representa o valor contido na posição inserida.
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
    Verifica a igualdade entre duas instâncias de Matriz.
    Verifica se o Object fornecido como parâmetro representa uma
    matriz igual àquela representada pela instância a qual este
    método for aplicado, resultando true em caso afirmativo,
    ou false, caso contrário.
    @param  obj o objeto a ser comparado com a instância a qual esse método
            for aplicado.
    @return true, caso o Object fornecido ao método e a instância chamante do
            método representarem agendas iguais, ou false, caso contrário.
    */
    public boolean equals (Object obj)
    {
        if (this == obj)
                return true;

        if (obj == null)
                return false;

        if (this.getClass() != obj.getClass())
            return false;

        if (this.m != ((Matriz)obj).m || this.n != ((Matriz)obj).n) //nï¿½o preciso de getter, pois estou dentro da classe Data
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
    Calcula o código de espalhamento (ou código de hash) da classe Matriz.
    Calcula e resulta o código de espalhamento (ou código de hash, ou ainda o
    hashcode) da classe Matriz representada pela instância a qual o método for aplicado.
    @return o código de espalhamento da instância chamante do método.
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
    Gera uma representação textual da classe Matriz.
    Produz e resulta um String com toda a matriz.
    @return um String contendo todo o conteúdo da classe Matriz.
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
    Copia os dados da instância atual para a instânicia passada.
    Para tanto, deve ser fornecida uma instância da classe Matriz para ser copiada.
    @param modelo a instância da classe Matriz a ser usada como modelo.
    @throws Exception se o modelo for null.
    */
    protected Matriz (Matriz modelo) throws Exception
    {
        if (modelo == null)
            throw new Exception ("Matriz inválida");

        this.matriz = modelo.matriz;
        this.m = modelo.m;
        this.n = modelo.n;
    }

    /**
    Constroi uma cópia da instância da classe Matriz dada.
    Para tanto, chama o construtor de cópia.
    @return retorna um objeto construído com todos os dados da instância chamante.
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
