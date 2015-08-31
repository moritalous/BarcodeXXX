package forest.rice.field.k.barcodexxx.util;

import java.util.Random;

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

    public static PokemonMap filterPokemonMap(String captorId) {
        PokemonMap map = new PokemonMap();

        for (String key : PokemonMap.POKEMON_MAP.keySet()) {
            Pokemon pokemon = PokemonMap.POKEMON_MAP.get(key);

            String tmpPaptorId = pokemon.getCaptorId();

            if(captorId ==null) {
                if(tmpPaptorId ==null) {
                    map.put(key, pokemon);
                }
            }
            else if(captorId.equals(tmpPaptorId)) {
                map.put(key, pokemon);
            }
        }
        return map;
    }

    public static PokemonMap filterPokemonMapMine() {
        return  filterPokemonMap(CaptorUtil.MY_CAPTOR_ID);
    }

    public static PokemonMap filterPokemonMapWild() {
        return  filterPokemonMap(null);
    }

    public static Pokemon getWildPokemonByRandom() {
        return getPokeminByRandom(PokemonUtil.filterPokemonMapWild());
    }

    public static Pokemon getPokeminByRandom(PokemonMap map) {
        String[] keySet = map.keySet().toArray(new String[0]);

        Random random = new Random();
        int no = random.nextInt(keySet.length);
        return map.get(keySet[no]);
    }
}
