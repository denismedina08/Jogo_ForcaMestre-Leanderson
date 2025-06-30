import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList; // Importar ArrayList
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Palavra {

    private final List<String> palavras;
    private String palavraSecreta;
    private String palavraComChutes;
     boolean acertou;

    // Construtor que carrega as palavras do arquivo
    public Palavra(String caminhoArquivo) {
        this.palavras = new ArrayList<>();
        try {
            // Lê todas as linhas do arquivo e as adiciona à lista de palavras
            List<String> linhas = Files.readAllLines(Paths.get(caminhoArquivo));
            for (String linha : linhas) {
                if (!linha.trim().isEmpty()) {
                    this.palavras.add(linha.trim()); // Adiciona a palavra, removendo espaços extras
                }
            }
            if (this.palavras.isEmpty()) {
                System.err.println("Aviso: O arquivo de palavras está vazio ou não contém palavras válidas. Usando palavras padrão.");
                carregarPalavrasPadrao(); // Carrega palavras padrão se o arquivo stiver vazio
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar palavras do arquivo: " + e.getMessage());
            System.err.println("Verifique se o arquivo '" + caminhoArquivo + "' existe e está no diretório correto.");
            System.err.println("Usando palavras padrão como fallback.");
            carregarPalavrasPadrao(); //Caso tiver erro, usa palavras padrão HardCode
        }
    }

    // Metodo para carregar palavras padrão
    private void carregarPalavrasPadrao() {
        this.palavras.clear();
        this.palavras.addAll(Arrays.asList("MONITO", "PYTHON", "JAVA", "COMPUTADOR", "PROGRAMACAO", "ALGORITMO"));
    }

    public void gerarPalavraSecreta() {
        if (this.palavras.isEmpty()) {
            System.err.println("Não há palavras disponíveis para o jogo! Adicione palavras no arquivo ou verifique o fallback.");
            this.palavraSecreta = "FORCA"; // Palavra de emergência
            this.palavraComChutes = "_".repeat(this.palavraSecreta.length());
            return;
        }
        Random rand = new Random();
        var randIndex = rand.nextInt(this.palavras.size());

        this.palavraSecreta = this.palavras.get(randIndex).toUpperCase(); // Converte para maiúsculas.
        this.palavraComChutes = "_".repeat(this.palavraSecreta.length());
    }

    public boolean revelarLetra(String letra) {
        char letraMaiuscula = Character.toUpperCase(letra.charAt(0));
        StringBuilder novaPalavraComChutes = new StringBuilder(this.palavraComChutes);

        for (int i = 0; i < palavraSecreta.length(); i++) {
            if (palavraSecreta.charAt(i) == letraMaiuscula) {
                novaPalavraComChutes.setCharAt(i, letraMaiuscula);
                acertou = true;
            }
        }
        this.palavraComChutes = novaPalavraComChutes.toString();
        return acertou;
    }

    public boolean palavraCompleta() {
        return !this.palavraComChutes.contains("_");
    }

    public String getPalavraComChutes() {
        return palavraComChutes;
    }

    public String getPalavraSecreta() {
        return palavraSecreta;
    }

    // Não é mais necessário um getter para 'palavras' se ele não for usado externamente
    // public List<String> getPalavras() {
    //    return palavras;
    // }
}