package arquivo;

import java.io.*;
import java.util.StringTokenizer;

/**
A classe ArquivoMatriz acessa os arquivos TXTs que contém as matrizes a serem lidas e escritas.
Há os atributos de endereço dos arquivos de entrada e saída, assim como a quantidade de linhas e colunas da matriz.
Métodos dessa classe permitem contar as linhas e colunas da matriz, ler a matriz e printá-la.
@author Rodrigo Smith Rodrigues.
@since 2019.
*/
public class LeitorArquivoMatriz implements Cloneable
{
    protected String enderecoArquivo = "";
    protected int linhas;
    protected int colunas;

    /**
    Constroi uma nova instância da classe ArquivoMatriz.
    Para tanto, deve ser fornecido duas Strings, que representam o endereço dos arquivos
    de entrada e saída¡ necessários para a instância ser criada.
    @param enderecoArquivo o local onde se localiza o arquivo com a matriz.
    @param enderecoArquivoSaida o local onde se localiza o arquivo que será (sobr)escrito.
    @throws Exception se os parâmetros forem Strings vazias.
    */
    public LeitorArquivoMatriz (String enderecoArquivo) throws Exception
    {
        setEnderecoArquivo(enderecoArquivo);
        contarLinhasEColunas();
    }

    /**
    Seta um novo valor como endereço de arquivo de entrada.
    @param enderecoArquivo o local onde se localiza o arquivo com a matriz.
    @throws Exception se o parâmetro for uma String vazia.
    */
    public void setEnderecoArquivo (String enderecoArquivo) throws Exception
    {
        if (enderecoArquivo.equals(""))
            throw new Exception ("Endereço de arquivo não fornecido");

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
    Conta quantas linhas e quantas colunas há no arquivo de entrada.
    Como resultado, seta nas variáveis globais "linhas" e "colunas" os valores lidos.
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
            st = new StringTokenizer(arquivoEntrada.readLine()); // pega as variaveis separadas por um espaço
            for (int j = 0; j < this.colunas; j++)
            {
                matriz[i][j] = Double.parseDouble(st.nextToken());
            }
        }

        arquivoEntrada.close(); // fecha arquivo de entrada
        return matriz;
    }

    /**
    Verifica a igualdade entre duas instâncias de ArquivoMatriz.
    Verifica se o Object fornecido como parâmetro representa um
    arquivo de matriz igual àquela representada pela instância a qual este
    método for aplicado, resultando true em caso afirmativo,
    ou false, caso contrário.
    @param  obj o objeto a ser comparado com a instância a qual esse método
            for aplicado.
    @return true, caso o Object fornecido ao método e a instância chamante do
            método representarem agendas iguais, ou false, caso contrário.
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
    Calcula o código de espalhamento (ou código de hash) da classe ArquivoMatriz.
    Calcula e resulta o código de espalhamento (ou código de hash, ou ainda o
    hashcode) da classe ArquivoMatriz representada pela instância a qual o método for aplicado.
    @return o código de espalhamento da instância chamante do método.
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
    Gera uma representação textual da classe ArquivoMatriz.
    Produz e resulta um String com todos os endereços de arquivo.
    @return um String contendo todo o conteúdo da classe ArquivoMatriz.
    */
    public String toString()
    {
        return "Endereço do arquivo onde se localiza a matriz: " + this.enderecoArquivo;
    }

    /**
    Copia os dados da instância atual para a instânicia passada.
    Para tanto, deve ser fornecida uma instância da classe ArquivoMatriz para ser copiada.
    @param modelo a instância da classe ArquivoMatriz a ser usada como modelo.
    @throws Exception se o modelo for null.
    */
    public LeitorArquivoMatriz (LeitorArquivoMatriz modelo) throws Exception
    {
        this.enderecoArquivo = modelo.enderecoArquivo;
    }

    /**
    Constroi uma cópia da instância da classe ArquivoMatriz dada.
    Para tanto, chama o construtor de cópia.
    @return retorna um objeto construído com todos os dados da instância chamante.
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
