package forest.rice.field.k.barcodexxx.util;

import forest.rice.field.k.barcodexxx.entity.Pokemon;

public class PokemonUtil {

    public static String getLargeImageUrl(Pokemon pokemon) {
        return "http://www.pokemon.jp/zukan/images/l/" + pokemon.getImageUrl();
    }

    public static String getMediumImageUrl(Pokemon pokemon) {
        return "http://www.pokemon.jp/zukan/images/m/" + pokemon.getImageUrl();
    }

    public static String getSmallImageUrl(Pokemon pokemon) {
        return "http://www.pokemon.jp/zukan/images/s/" + pokemon.getImageUrl();
    }

    public static String getNoByString(Pokemon pokemon) {
        return Integer.toString(pokemon.getNo());
    }
}
