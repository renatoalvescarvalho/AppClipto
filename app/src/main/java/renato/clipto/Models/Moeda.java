package renato.clipto.Models;

/**
 * Created by Renato on 13/01/2018.
 */

public class Moeda {

    private  String imagem;
    private  String nome;
    private  String valor;
    private  String change_24h;


    public Moeda (String imagem, String nome, String valor, String change_24h){
        this.imagem = imagem;
        this.nome = nome;
        this.valor = valor;
        this.change_24h = change_24h;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getChange_24h() {
        return change_24h;
    }

    public void setChange_24h(String descricao) {
        this.change_24h = descricao;
    }
}
