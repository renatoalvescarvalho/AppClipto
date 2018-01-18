package renato.clipto.Models;

/**
 * Created by Renato on 13/01/2018.
 */

public class Moeda {

    private  String imagem;
    private  String name;
    private  String price_brl;
    private  String percent_change_24h;
    private  String symbol;


    public Moeda (String imagem, String name, String price_brl, String percent_change_24h, String symbol){
        this.imagem = imagem;
        this.name = name;
        this.price_brl = price_brl;
        this.percent_change_24h = percent_change_24h;
        this.symbol = symbol;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    public String getPrice_brl() {
        return price_brl;
    }

    public void setPrice_brl(String valor) {
        this.price_brl = valor;
    }

    public String getPercent_change_24h() {
        return percent_change_24h;
    }

    public void setPercent_change_24h(String descricao) {
        this.percent_change_24h = descricao;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
