package forest.rice.field.k.barcodexxx.entity;

import java.util.ArrayList;
import java.util.Collection;

public class RankingList extends ArrayList<Ranking> {

    public RankingList() {
        super();
    }

    public RankingList(Collection<Ranking> ranking) {
        super(ranking);
    }

    public String getRankingSammaryText() {

        // XX匹発見 XX匹捕獲可能

        int captoredNum = 0;
        int captorableNum = 0;
        int yaseiNum = 0;

        for(Ranking ranking : this) {
            if(ranking.getCaptor() == null) {
                yaseiNum += ranking.getPokemonList().size();
            }
            captoredNum += ranking.getPokemonList().size();
        }

        captorableNum = 719 - captoredNum + yaseiNum;

        return String.format("あと%d匹捕獲可能", captorableNum);
    }
}
