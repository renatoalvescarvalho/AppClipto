package renato.clipto;

/**
 * Created by Renato on 29/11/2017.
 */


import renato.clipto.Models.Coinmarketcap;
import renato.clipto.Models.Mercado;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MercadoService {

    public static final String BASE_URL = "https://www.mercadobitcoin.net/api/";
    public static final String BASE_URLCOINMARKETCAP = "https://api.coinmarketcap.com/v1/";

    @GET("BTC/ticker")
    Call<Mercado>  getBitcoin();

    @GET("LTC/ticker")
    Call<Mercado>  getLitecoin();

    @GET("BCH/ticker")
    Call<Mercado>  getBCash();

    @GET("ticker/ethereum/?convert=brl")
    Call<Coinmarketcap[]>  getEthereum();

    @GET("ticker/dash/?convert=brl")
    Call<Coinmarketcap[]>  getDash();

    @GET("ticker/iota/?convert=brl")
    Call<Coinmarketcap[]>  getIOTA();

    @GET("ticker/ripple/?convert=brl")
    Call<Coinmarketcap[]>  getRipple();

}
