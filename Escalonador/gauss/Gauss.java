package gauss;

import matriz.Matriz;

/**
A classe Gauss tem como finalizade calcular sistemas.
A classe Gauss tem como finalizade calcular sistemas, dado por matrizes, pelo método de Gauss-Seidel.
@author Rodrigo Smith Rodrigues.
@since 2019.
*/
public class Gauss implements Cloneable
{
    protected Matriz matriz = null;

    /**
    Constroi uma nova instância da classe Gauss.
    Para tanto, deve ser fornecido uma instância da classe Matriz.
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
    Seta uma nova matriz em uma instância da classe Gauss.
    Troca a matriz antiga por uma matriz nova, a ser calculada, evitando a
    necessidade de fazer uma nova instância de Gauss.
    @param matriz Objeto da classe Matriz, previamente instanciado e com os valores a serem calculados.
    @throws Exception se matriz for nulo.
    */
    public void setMatriz(Matriz matriz) throws Exception
    {
        if (matriz == null)
            throw new Exception ("matriz inválida");

        this.matriz = matriz;
    }

    /**
    Retorna o Objeto matriz, que foi passado no construtor ou no setter.
    @return uma instância da classe Matriz.
    */
    public Matriz getMatriz()
    {
        return this.matriz;
    }

    /**
    Retira a ultima coluna (a dos resultados) da matriz para futuros cálculos.
    @return uma matriz bidimensional de double, sem a última coluna.
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
        return ret; //não alterei this.matriz, pois a última coluna será usada.
    }


    /**
    Verifica se há valores repetidos no dado vetor.
    @param vetor Matriz unidimensional dsejada.
    @return Retorna true se sim e false se não.
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
    Verifica se o sistema dado pela matriz é possível de ser resolvido.
    @return Retorna true se sim e false se não.
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
    Verifica se há zeros no dado vetor.
    @param vetor Matriz unidimensional desejada.
    @return Retorna true se sim e false se não.
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
    Isso é feito afim de não haver zeros na diagonal principal.
    @throws Exception é lançada caso a matriz setada em this.matriz seja nula,
    porém; isso não tem possibilidade de acontecer.
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
    à diagonal principal se torne 1.
    @param k inteiro que representa a coordenada (k, k) da diagonal principal com
    o número a se tornar 1.
    @return é retornado um inteiro: a linha em que foram feitas as alterações,
    a qual será usada no método "zerarColunas()".
    @throws Exception é lançada caso a matriz setada em this.matriz seja nula,
    porém; isso não tem possibilidade de acontecer.
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
    multiplicado por -1. Logo após, a linha inteira é somada à linha do valor a ser zerado.
    Casso a coluna inteira seja zerada, o método termina (return), afim de que a próxima linha
    seja dividida antes da próxima coluna ser zerada.
    @param linha inteiro que representa a linha que foi dividida anteriormente.
    @throws Exception é lançada caso a matriz setada em this.matriz seja nula,
    porém; isso não tem possibilidade de acontecer.
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
    Esse método é responsável pelo cálculo do sistema, em formato de matriz (this.matriz) pelo método de Gauss.
    Ele utiliza diversos outros métodos conforme sua necessidade; além de conferir se o sistema é possível.
    @throws Exception é lançada uma exceção caso o sistema seja impossível de ser resolvido.
    */
    public void calcular() throws Exception
    {
        System.out.println("Segue os passos da resolução usando matrizes, pelo método de Gauss-Seidel:\n");
        System.out.println(this.matriz);

        //etapa 1: verificar se o sistema é possível
        if (!isPossivel())
            throw new Exception ("O sistema é impossível");

        //etapa 2: verificar a diagonal principal
        double[] diagonal = matriz.getDiagonalPrincipal();

        //se contém zeros, a ordem das linhas são trocadas
        if (contemZeros(diagonal))
        {
            trocarLinhas();
        }

        //etapa 3: transformar todos os números da diagonal em 1, dividindo a linha inteira pelo
        //prório número. Logo após, transforma-se todos os números de todas colunas (pulando a diagonal)
        //em zero, multiplicando a linha do processo anterior pelo número a ser transformado em zero
        //multiplicado por -1, e somando à linha do número a ser zerado.
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

        String ret = "Essa é a solução do sistema:\n\n";

        for(int i = 0; i < this.matriz.getM(); i++)
        {
            ret += incognitas[i] + " = " + this.matriz.getValor(i, this.matriz.getN() - 1) + "\n";
        }

        System.out.println(ret);

    }

    /**
    Verifica a igualdade entre duas instâncias de Gauss.
    Verifica se o Object fornecido como parâmetro representa uma
    instância igual àquela a qual este método for aplicado,
    resultando true em caso afirmativo ou false, caso contrário.
    @param  obj o objeto a ser comparado com a instância a qual esse método for aplicado.
    @return true, caso o Object fornecido ao método e a instância chamante do
            método representarem agendas iguais, ou false, caso contrário.
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
    Calcula o código de espalhamento (ou código de hash) da classe Gauss.
    Calcula e resulta o código de espalhamento (ou código de hash, ou ainda o
    hashcode) da classe Gauss representada pela instância a qual o método for aplicado.
    Para tal, chama o próprio método hashCode() da classe matriz. Afinal, o único atributo
    dessa classe é uma instâcia da classe Matriz.
    @return o código de espalhamento da instância chamante do método.
    */
    public int hashCode ()
    {
        return matriz.hashCode();
    }


    /**
    Gera uma representação textual da classe Gauss.
    Produz e resulta um String com todo o sistema de equações.
    @return um String contendo todo o conteúdo da classe Gauss.
    */
    public String toString()
    {
        char[] incognitas = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        String ret = "Esse é o sistema a ser calculado:\n\n";

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
    Copia os dados da instância atual para a instânicia passada.
    Para tanto, deve ser fornecida uma instância da classe Gauss para ser copiada.
    @param modelo a instância da classe Gauss a ser usada como modelo.
    @throws Exception se o modelo for null.
    */
    protected Gauss (Gauss modelo) throws Exception
    {
        if (modelo == null)
            throw new Exception ("Instância de Gauss inválida");

        this.matriz = modelo.matriz;
    }

    /**
    Constroi uma cópia da instância da classe Gauss dada.
    Para tanto, chama o construtor de cópia.
    @return retorna um objeto construído com todos os dados da instância chamante.
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
