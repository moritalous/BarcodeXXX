package forest.rice.field.k.barcodexxx.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import forest.rice.field.k.barcodexxx.entity.CaptorMap;
import forest.rice.field.k.barcodexxx.entity.Pokemon;
import forest.rice.field.k.barcodexxx.entity.PokemonMap;
import forest.rice.field.k.barcodexxx.entity.Ranking;
import forest.rice.field.k.barcodexxx.entity.RankingList;

public class RankingUtil {

    public static RankingList getRankingList() {
        HashMap<String, Ranking> rankingMap = new HashMap<>();

        for (String key : PokemonMap.POKEMON_MAP.keySet()) {
            Pokemon pokemon = PokemonMap.POKEMON_MAP.get(key);

            String captorId = pokemon.getCaptorId();
            Ranking ranking;
            if (rankingMap.containsKey(captorId)) {
                ranking = rankingMap.get(captorId);
            } else {
                ranking = new Ranking();
                ranking.setCaptor(CaptorMap.CAPTOR.get(captorId));
                rankingMap.put(captorId, ranking);
            }
            ranking.getPokemonList().add(pokemon);
        }

        RankingList rankingList = new RankingList(rankingMap.values());
        Collections.sort(rankingList, new Comparator<Ranking>() {
            @Override
            public int compare(Ranking ranking1, Ranking ranking2) {
                return (ranking2.getPokemonList().size() - ranking1.getPokemonList().size());
            }
        });

        return rankingList;
    }
}
