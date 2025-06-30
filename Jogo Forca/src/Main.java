public class Main {
    public static void main(String[] args) {
        // Caminho do arquivo TXT para o construtor de Palavra
        var palavra = new Palavra("Jogo Forca/palavras.txt");
        var jogador = new Jogador("Cholito");
        var jogo = new Jogo(palavra, jogador);

        jogo.iniciarJogo();
    }
}