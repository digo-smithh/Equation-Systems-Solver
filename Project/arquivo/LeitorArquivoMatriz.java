package arquivo;

import java.io.*;
import java.util.StringTokenizer;

/**
A classe ArquivoMatriz acessa os arquivos TXTs que cont�m as matrizes a serem lidas e escritas.
H� os atributos de endere�o dos arquivos de entrada e sa�da, assim como a quantidade de linhas e colunas da matriz.
M�todos dessa classe permitem contar as linhas e colunas da matriz, ler a matriz e print�-la.
@author Rodrigo Smith Rodrigues.
@since 2019.
*/
public class LeitorArquivoMatriz implements Cloneable
{
    protected String enderecoArquivo = "";
    protected int linhas;
    protected int colunas;

    /**
    Constroi uma nova inst�ncia da classe ArquivoMatriz.
    Para tanto, deve ser fornecido duas Strings, que representam o endere�o dos arquivos
    de entrada e sa�da� necess�rios para a inst�ncia ser criada.
    @param enderecoArquivo o local onde se localiza o arquivo com a matriz.
    @param enderecoArquivoSaida o local onde se localiza o arquivo que ser� (sobr)escrito.
    @throws Exception se os par�metros forem Strings vazias.
    */
    public LeitorArquivoMatriz (String enderecoArquivo) throws Exception
    {
        setEnderecoArquivo(enderecoArquivo);
        contarLinhasEColunas();
    }

    /**
    Seta um novo valor como endere�o de arquivo de entrada.
    @param enderecoArquivo o local onde se localiza o arquivo com a matriz.
    @throws Exception se o par�metro for uma String vazia.
    */
    public void setEnderecoArquivo (String enderecoArquivo) throws Exception
    {
        if (enderecoArquivo.equals(""))
            throw new Exception ("Endere�o de arquivo n�o fornecido");

        this.enderecoArquivo = enderecoArquivo;
    }

    /**
    Retorna o local do arquivo de entrada.
    @return uma String que representa o local onde se localiza o arquivo de entrada.
    */
    public String getEnderecoArquivo ()
    {
        return this.enderecoArquivo;
    }

    /**
    Conta quantas linhas e quantas colunas h� no arquivo de entrada.
    Como resultado, seta nas vari�veis globais "linhas" e "colunas" os valores lidos.
    @throws IOException se houver problemas na leitura.
    */
    protected void contarLinhasEColunas () throws Exception // le arquivo para contar o numero de linhas e colunas
    {
        int m = 0;
        int n = 0;
        int qualLinha = 1;

        BufferedReader arquivo = new BufferedReader(new FileReader(enderecoArquivo));
        String linha;
        arquivo.readLine();
        linha = arquivo.readLine();

        if (linha == null || (linha.length() == 0 && arquivo.readLine() == null))
            throw new Exception ("Arquivo vazio!");

        int x = 0, y = 0;
        while ((linha = arquivo.readLine()) != null)
        {
            String[] values = linha.split(" ");

            for (String str : values)
            {
                y = y + 1; // numero total de elementos
            }
            x = x + 1; // numero de linhas

        }
        arquivo.close();

        n = y / x;
        m = x;
        this.linhas = m + 1;
        this.colunas = n;
    }

    public double[][] lerMatriz() throws Exception
    {
        double[][] matriz = new double[this.linhas][this.colunas];

        BufferedReader arquivoEntrada = new BufferedReader(new FileReader(enderecoArquivo));

        StringTokenizer st;
        arquivoEntrada.readLine();

        for (int i = 0; i < this.linhas; i++)
        {
            st = new StringTokenizer(arquivoEntrada.readLine()); // pega as variaveis separadas por um espa�o
            for (int j = 0; j < this.colunas; j++)
            {
                matriz[i][j] = Double.parseDouble(st.nextToken());
            }
        }

        arquivoEntrada.close(); // fecha arquivo de entrada
        return matriz;
    }

    /**
    Verifica a igualdade entre duas inst�ncias de ArquivoMatriz.
    Verifica se o Object fornecido como par�metro representa um
    arquivo de matriz igual �quela representada pela inst�ncia a qual este
    m�todo for aplicado, resultando true em caso afirmativo,
    ou false, caso contr�rio.
    @param  obj o objeto a ser comparado com a inst�ncia a qual esse m�todo
            for aplicado.
    @return true, caso o Object fornecido ao m�todo e a inst�ncia chamante do
            m�todo representarem agendas iguais, ou false, caso contr�rio.
    */
    public boolean equals (LeitorArquivoMatriz obj)
    {
        if (this == obj)
		return true;

        if (obj == null)
                return false;

        if (this.getClass() != obj.getClass())
            return false;

        if (this.enderecoArquivo.equals(((LeitorArquivoMatriz)obj).enderecoArquivo))
                return false;

        return true;
    }

    /**
    Calcula o c�digo de espalhamento (ou c�digo de hash) da classe ArquivoMatriz.
    Calcula e resulta o c�digo de espalhamento (ou c�digo de hash, ou ainda o
    hashcode) da classe ArquivoMatriz representada pela inst�ncia a qual o m�todo for aplicado.
    @return o c�digo de espalhamento da inst�ncia chamante do m�todo.
    */
    public int hashCode ()
    {
            int ret = 1; //qualquer positivo

            ret = ret * 7 + this.enderecoArquivo.hashCode();

            if (ret < 0)
                    ret = -ret;

            return ret;
    }

    /**
    Gera uma representa��o textual da classe ArquivoMatriz.
    Produz e resulta um String com todos os endere�os de arquivo.
    @return um String contendo todo o conte�do da classe ArquivoMatriz.
    */
    public String toString()
    {
        return "Endere�o do arquivo onde se localiza a matriz: " + this.enderecoArquivo;
    }

    /**
    Copia os dados da inst�ncia atual para a inst�nicia passada.
    Para tanto, deve ser fornecida uma inst�ncia da classe ArquivoMatriz para ser copiada.
    @param modelo a inst�ncia da classe ArquivoMatriz a ser usada como modelo.
    @throws Exception se o modelo for null.
    */
    public LeitorArquivoMatriz (LeitorArquivoMatriz modelo) throws Exception
    {
        this.enderecoArquivo = modelo.enderecoArquivo;
    }

    /**
    Constroi uma c�pia da inst�ncia da classe ArquivoMatriz dada.
    Para tanto, chama o construtor de c�pia.
    @return retorna um objeto constru�do com todos os dados da inst�ncia chamante.
    */
    public Object clone ()
    {
        Object ret = null;

        try
        {
            ret = new LeitorArquivoMatriz(this);
        }
        catch (Exception erro)
        {}

        return ret;
    }
}
