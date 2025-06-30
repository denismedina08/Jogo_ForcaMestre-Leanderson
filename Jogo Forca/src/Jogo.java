import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jogo {
    private final Palavra palavra;
    private final Jogador jogador;

    public Jogo(Palavra palavra, Jogador jogador) {
        this.palavra = palavra;
        this.jogador = jogador;
    }

    public void iniciarJogo() {
        List<String> chutes = new ArrayList<>();
        var numeroChutes = 12;

        this.palavra.gerarPalavraSecreta();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Olá " + this.jogador.getNome() + ", bem-vindo ao Jogo da Forca!");
        System.out.println("Você possui " + numeroChutes + " chutes para adivinhar a palavra oculta.");
        System.out.println(this.palavra.getPalavraComChutes() + "\n");

        while (!this.palavra.palavraCompleta() && chutes.size() < numeroChutes) {
            System.out.println("Informe uma Letra: ");
            String chute = scanner.nextLine();

            if (chutes.contains(chute)) {
                System.out.println("Você já chutou essa letra! Tente colocar outra.");
                continue;
            }
            chutes.add(chute);

            this.palavra.revelarLetra(chute);

            System.out.println("Palavra: " + palavra.getPalavraComChutes());

            if (!this.palavra.getPalavraComChutes().contains("_")) {
                System.out.println("AEEE " + this.jogador.getNome() + "! Você acertou!");
            } else if (chutes.size() == numeroChutes) {
                System.out.println("Poxa " + this.jogador.getNome() + " você perdeu! A palavra secreta era: " + palavra.getPalavraSecreta());
            }
        }
    }

}