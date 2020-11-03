package gauss;

import matriz.Matriz;

/**
A classe Gauss tem como finalizade calcular sistemas.
A classe Gauss tem como finalizade calcular sistemas, dado por matrizes, pelo m�todo de Gauss-Seidel.
@author Rodrigo Smith Rodrigues.
@since 2019.
*/
public class Gauss implements Cloneable
{
    protected Matriz matriz = null;

    /**
    Constroi uma nova inst�ncia da classe Gauss.
    Para tanto, deve ser fornecido uma inst�ncia da classe Matriz.
    @param matriz Objeto da classe Matriz, previamente instanciado e com os valores a serem calculados.
    @throws Exception se matriz for nulo.
    */
    public Gauss (Matriz matriz) throws Exception
    {
        if (matriz == null)
            throw new Exception("Matriz inexistente!");

        this.matriz = matriz;
    }

    /**
    Seta uma nova matriz em uma inst�ncia da classe Gauss.
    Troca a matriz antiga por uma matriz nova, a ser calculada, evitando a
    necessidade de fazer uma nova inst�ncia de Gauss.
    @param matriz Objeto da classe Matriz, previamente instanciado e com os valores a serem calculados.
    @throws Exception se matriz for nulo.
    */
    public void setMatriz(Matriz matriz) throws Exception
    {
        if (matriz == null)
            throw new Exception ("matriz inv�lida");

        this.matriz = matriz;
    }

    /**
    Retorna o Objeto matriz, que foi passado no construtor ou no setter.
    @return uma inst�ncia da classe Matriz.
    */
    public Matriz getMatriz()
    {
        return this.matriz;
    }

    /**
    Retira a ultima coluna (a dos resultados) da matriz para futuros c�lculos.
    @return uma matriz bidimensional de double, sem a �ltima coluna.
    */
    protected double[][] retirarUltimaColuna()
    {
        double[][] ret = new double[this.matriz.getM()][this.matriz.getN() - 1];

        for (int i = 0; i < ret.length; i++)
        {
            for (int j = 0; j < ret[0].length; j++)
            {
                ret[i][j] = this.matriz.getValor(i, j);
            }
        }
        return ret; //n�o alterei this.matriz, pois a �ltima coluna ser� usada.
    }


    /**
    Verifica se h� valores repetidos no dado vetor.
    @param vetor Matriz unidimensional dsejada.
    @return Retorna true se sim e false se n�o.
    */
    protected boolean haValoresIguais(double[] vetor)
    {
        for (int i = 0; i < vetor.length - 1; i++)
        {
            if (vetor[i] != vetor[i + 1])
                return false;
        }
        return true;
    }

    /**
    Verifica se o sistema dado pela matriz � poss�vel de ser resolvido.
    @return Retorna true se sim e false se n�o.
    */
    protected boolean isPossivel()
    {
        double[][] matriz = retirarUltimaColuna();
        double[] divisoes = null;
        int l = 0;

       for(int i = 0; i < matriz.length; i++)
       {
            for(int k = 0; k < matriz.length; k++)
            {
                divisoes = new double[matriz.length];
                l = 0;

                for (int j = 0; j < matriz[0].length; j++)
                {
                    if (i != k)
                    {
                        divisoes[l] = matriz[i][j] / matriz[k][j];
                        l++;
                        if (haValoresIguais(divisoes) && l == matriz.length)
                            return false;
                    }
                }
            }
        }
       return true;
    }

    /**
    Verifica se h� zeros no dado vetor.
    @param vetor Matriz unidimensional desejada.
    @return Retorna true se sim e false se n�o.
    */
    protected boolean contemZeros(double[] vetor)
    {
        for (int i = 0; i < vetor.length; i++)
        {
            if (vetor[i] == 0.0)
                return true;
        }
        return false;
    }

    /**
    Troca a ordem das linhas da matriz.
    Isso � feito afim de n�o haver zeros na diagonal principal.
    @throws Exception � lan�ada caso a matriz setada em this.matriz seja nula,
    por�m; isso n�o tem possibilidade de acontecer.
    */
    protected void trocarLinhas() throws Exception
    {
        double[][] matrizTrocada = new double[this.matriz.getM()][this.matriz.getN()];

        for (int i = 0; i < this.matriz.getM(); i++)
        {
            for (int j = 0; j < this.matriz.getN(); j++)
            {
                if (i == this.matriz.getM() - 1)
                {
                    matrizTrocada[i][j] = this.matriz.getValor(0, j);
                }
                else
                {
                    matrizTrocada[i][j] = this.matriz.getValor(i + 1, j);
                }
            }
        }

        this.matriz.setMatriz(matrizTrocada);
        System.out.println(matriz);
    }

    /**
    Divide uma certa linha da matriz.
    Divide uma linha da matriz, afim de que o valor do item correspondente
    � diagonal principal se torne 1.
    @param k inteiro que representa a coordenada (k, k) da diagonal principal com
    o n�mero a se tornar 1.
    @return � retornado um inteiro: a linha em que foram feitas as altera��es,
    a qual ser� usada no m�todo "zerarColunas()".
    @throws Exception � lan�ada caso a matriz setada em this.matriz seja nula,
    por�m; isso n�o tem possibilidade de acontecer.
    */
    protected int dividirLinha(int k) throws Exception
    {
        double[][] matrizFinal = new double[this.matriz.getM()][this.matriz.getN()];
        double divisor = this.matriz.getValor(k, k);
        int linha = 0;

        for (int i = 0; i < this.matriz.getM(); i++)
        {
            for (int j = 0; j < this.matriz.getN(); j++)
            {
                if (i == k)
                {
                    linha = i;
                    matrizFinal[i][j] = this.matriz.getValor(i, j) / divisor;
                }
                else
                {
                    matrizFinal[i][j] = this.matriz.getValor(i, j);
                }
            }
        }

        this.matriz.setMatriz(matrizFinal);
        System.out.println(matriz);
        return linha;
    }

    /**
    Zera todos os valores de todas as colunas da matriz.
    Para tal, multiplica toda a linha dividida anteriormente pelo valor a ser tornado zero
    multiplicado por -1. Logo ap�s, a linha inteira � somada � linha do valor a ser zerado.
    Casso a coluna inteira seja zerada, o m�todo termina (return), afim de que a pr�xima linha
    seja dividida antes da pr�xima coluna ser zerada.
    @param linha inteiro que representa a linha que foi dividida anteriormente.
    @throws Exception � lan�ada caso a matriz setada em this.matriz seja nula,
    por�m; isso n�o tem possibilidade de acontecer.
    */
    protected int zerarColuna(int linha, int coluna) throws Exception
    {
        double[][] matrizFinal = new double[this.matriz.getM()][this.matriz.getN()];
        double[] valores = new double[this.matriz.getN()];
        double multiplicador;

        for (int i = 0; i < this.matriz.getM(); i++)
        {
            for (int j = 0; j < this.matriz.getN(); j++)
            {
                matrizFinal[i][j] = this.matriz.getValor(i, j);
            }
        }

        for (int j = 0; j < this.matriz.getN() - 1; j++)
        {
            for (int i = 0; i < this.matriz.getM(); i++)
            {
                if (this.matriz.getValor(i, j) != 0 && i != linha && i != j)
                {
                    multiplicador = -(this.matriz.getValor(i, j));

                    for (int k = 0; k < this.matriz.getN(); k++)
                    {
                        valores[k] = this.matriz.getValor(linha, k) * multiplicador;
                        matrizFinal[i][k] = this.matriz.getValor(i, k) + valores[k];
                    }

                    this.matriz.setMatriz(matrizFinal);
                    System.out.println(matriz);

                    if (i == this.matriz.getM() - 1)
                    {
                        return j + 1;
                    }
                }
                else if (coluna == 0 && i == this.matriz.getM() - 1 && this.matriz.getValor(i, j) == 0)
                {
                    return j + 1;
                }
                else if (i == this.matriz.getM() - 1 && this.matriz.getValor(i, j) == 0 && j == coluna)
                {
                    return j + 1;
                }
            }

        }
        return 0;
    }

    /**
    Calcula o sistema dado por uma matriz.
    Esse m�todo � respons�vel pelo c�lculo do sistema, em formato de matriz (this.matriz) pelo m�todo de Gauss.
    Ele utiliza diversos outros m�todos conforme sua necessidade; al�m de conferir se o sistema � poss�vel.
    @throws Exception � lan�ada uma exce��o caso o sistema seja imposs�vel de ser resolvido.
    */
    public void calcular() throws Exception
    {
        System.out.println("Segue os passos da resolu��o usando matrizes, pelo m�todo de Gauss-Seidel:\n");
        System.out.println(this.matriz);

        //etapa 1: verificar se o sistema � poss�vel
        if (!isPossivel())
            throw new Exception ("O sistema � imposs�vel");

        //etapa 2: verificar a diagonal principal
        double[] diagonal = matriz.getDiagonalPrincipal();

        //se cont�m zeros, a ordem das linhas s�o trocadas
        if (contemZeros(diagonal))
        {
            trocarLinhas();
        }

        //etapa 3: transformar todos os n�meros da diagonal em 1, dividindo a linha inteira pelo
        //pr�rio n�mero. Logo ap�s, transforma-se todos os n�meros de todas colunas (pulando a diagonal)
        //em zero, multiplicando a linha do processo anterior pelo n�mero a ser transformado em zero
        //multiplicado por -1, e somando � linha do n�mero a ser zerado.
        int linha;
        int coluna = 0;
        for (int k = 0; k < this.matriz.getM(); k++)
        {
            if (this.matriz.getValor(k, k) != 1)
            {
                linha = dividirLinha(k);
                coluna = zerarColuna(linha, coluna);
            }
        }

        //etapa 4: exibir o resultado do sistema.
        exibirResultado();
    }

    protected void exibirResultado()
    {
        char[] incognitas = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

        String ret = "Essa � a solu��o do sistema:\n\n";

        for(int i = 0; i < this.matriz.getM(); i++)
        {
            ret += incognitas[i] + " = " + this.matriz.getValor(i, this.matriz.getN() - 1) + "\n";
        }

        System.out.println(ret);

    }

    /**
    Verifica a igualdade entre duas inst�ncias de Gauss.
    Verifica se o Object fornecido como par�metro representa uma
    inst�ncia igual �quela a qual este m�todo for aplicado,
    resultando true em caso afirmativo ou false, caso contr�rio.
    @param  obj o objeto a ser comparado com a inst�ncia a qual esse m�todo for aplicado.
    @return true, caso o Object fornecido ao m�todo e a inst�ncia chamante do
            m�todo representarem agendas iguais, ou false, caso contr�rio.
    */
    public boolean equals (Gauss obj)
    {
        if (this == obj)
                return true;

        if (obj == null)
                return false;

        if (this.getClass() != obj.getClass())
            return false;

        if (this.matriz.equals(obj.matriz))
            return false;

        return true;
    }

    /**
    Calcula o c�digo de espalhamento (ou c�digo de hash) da classe Gauss.
    Calcula e resulta o c�digo de espalhamento (ou c�digo de hash, ou ainda o
    hashcode) da classe Gauss representada pela inst�ncia a qual o m�todo for aplicado.
    Para tal, chama o pr�prio m�todo hashCode() da classe matriz. Afinal, o �nico atributo
    dessa classe � uma inst�cia da classe Matriz.
    @return o c�digo de espalhamento da inst�ncia chamante do m�todo.
    */
    public int hashCode ()
    {
        return matriz.hashCode();
    }


    /**
    Gera uma representa��o textual da classe Gauss.
    Produz e resulta um String com todo o sistema de equa��es.
    @return um String contendo todo o conte�do da classe Gauss.
    */
    public String toString()
    {
        char[] incognitas = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        String ret = "Esse � o sistema a ser calculado:\n\n";

        for(int i = 0; i < this.matriz.getM(); i++)
        {
            for(int j = 0; j < this.matriz.getN(); j++)
            {
                if (j > 0)
                {
                    if (j < this.matriz.getN() - 1)
                    {
                        if (this.matriz.getValor(i, j) >= 0)
                        {
                            ret += " + " + this.matriz.getValor(i, j) + incognitas[j] + "";

                        }
                        else
                        {
                            ret += " - " + -(this.matriz.getValor(i, j)) + incognitas[j] + "";
                        }
                    }
                    else
                    {
                        if (this.matriz.getValor(i, j) >= 0)
                        {
                            ret += " = " + this.matriz.getValor(i, j) + "\n";
                        }
                        else
                        {
                            ret += " = -" + -(this.matriz.getValor(i, j)) + "\n";
                        }
                    }
                }
                else
                {
                    ret += this.matriz.getValor(i, j) + "" + incognitas[j];
                }
            }
        }

        return ret;
    }

    /**
    Copia os dados da inst�ncia atual para a inst�nicia passada.
    Para tanto, deve ser fornecida uma inst�ncia da classe Gauss para ser copiada.
    @param modelo a inst�ncia da classe Gauss a ser usada como modelo.
    @throws Exception se o modelo for null.
    */
    protected Gauss (Gauss modelo) throws Exception
    {
        if (modelo == null)
            throw new Exception ("Inst�ncia de Gauss inv�lida");

        this.matriz = modelo.matriz;
    }

    /**
    Constroi uma c�pia da inst�ncia da classe Gauss dada.
    Para tanto, chama o construtor de c�pia.
    @return retorna um objeto constru�do com todos os dados da inst�ncia chamante.
    */
    public Object clone ()
    {

        Object ret = null;

        try
        {
            ret = new Gauss (this);
        }
        catch (Exception erro)
        {}

        return ret;
    }
}
