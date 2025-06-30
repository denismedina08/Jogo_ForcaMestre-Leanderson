import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Palavra {

    private final List<String> palavras =
            Arrays.asList("faca", "garfo", "colher", "prato", "pote", "tigela", "panela");
    private String palavraSecreta;
    public String palavraComChutes;

    public void gerarPalavraSecreta() {
        var range = this.palavras.size() - 1;
        var randIndex = (int) (Math.random() * range);

        this.palavraSecreta = this.palavras.get(randIndex);
        this.palavraComChutes = "_".repeat(this.palavraSecreta.length());
    }

    public void revelarLetra(String letra) {
        for (int i = 0; i < palavraSecreta.length(); i++) {
            if (palavraSecreta.charAt(i) == letra.charAt(0)) {
                this.palavraComChutes = this.palavraComChutes.substring(0, i) + letra + palavraComChutes.substring(i + 1);
            }
        }
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

    public List<String> getPalavras() {
        return palavras;
    }

}
