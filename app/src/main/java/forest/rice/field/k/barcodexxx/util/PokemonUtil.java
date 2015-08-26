package forest.rice.field.k.barcodexxx.util;

import forest.rice.field.k.barcodexxx.entity.Pokemon;
import forest.rice.field.k.barcodexxx.entity.PokemonMap;

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

    public static String getNoByStringWithFormat(Pokemon pokemon) {
        return  String.format("No.%03d", pokemon.getNo());
    }

    public static String getNoByStringWithFormat(int no) {
        return  String.format("No.%03d", no);
    }

    public static String getDetailUrl(Pokemon pokemon) {
        return String.format("http://www.pokemon.jp/zukan/detail/%03d.html", pokemon.getNo());
    }

    public static PokemonMap filterPokemonMapMine() {

        PokemonMap map = new PokemonMap();

        for (String key : PokemonMap.POKEMON_MAP.keySet()) {
            Pokemon pokemon = PokemonMap.POKEMON_MAP.get(key);

            String captorId = pokemon.getCaptorId();

            if(CaptorUtil.MY_CAPTOR_ID.equals(captorId)) {
                map.put(key, pokemon);
            }
        }

        return map;
    }
}
