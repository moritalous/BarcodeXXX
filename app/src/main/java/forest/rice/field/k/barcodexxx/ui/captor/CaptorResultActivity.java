package forest.rice.field.k.barcodexxx.ui.captor;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import forest.rice.field.k.barcodexxx.R;
import forest.rice.field.k.barcodexxx.entity.Pokemon;
import forest.rice.field.k.barcodexxx.db.PokemonFirebaseDB;
import forest.rice.field.k.barcodexxx.entity.PokemonMap;
import forest.rice.field.k.barcodexxx.ui.fragment.PokemonListFragment;
import forest.rice.field.k.barcodexxx.net.DetailRequest;
import forest.rice.field.k.barcodexxx.util.CaptorUtil;

public class CaptorResultActivity extends AppCompatActivity {

    public static final String EXTRA_CODE = "code";

    private static PokemonFirebaseDB db = null;

    private CaptorResultActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captor_result);

        activity = this;

        db = PokemonFirebaseDB.getInstance(this);

        String code;
        if (savedInstanceState == null) {
            code = getIntent().getStringExtra(EXTRA_CODE);

            Bundle bundle = new Bundle();
            bundle.putString(EXTRA_CODE, code);
        } else {
            code = savedInstanceState.getString(EXTRA_CODE);
        }

        List<Integer> captorPokemonNo = new ArrayList<>();
        Double numCode = Double.parseDouble(code);

        int count = (int) (numCode % 10) + 1;

        for (int i = 0; i < count; i++) {
            int tmpCode = (int) ((numCode + (count * i)) % 719) + 1;
            captorPokemonNo.add(tmpCode);
        }

        TextView textview = (TextView) findViewById(R.id.text_name);
        textview.setText(String.format("%dひきつかまえたよ", count));


        new CaptorAsyncTaks().execute(captorPokemonNo);

    }

    private class CaptorAsyncTaks extends AsyncTask<List<Integer>, Integer, HashMap<String, Pokemon>> {

        @Override
        protected HashMap<String, Pokemon> doInBackground(List<Integer>... lists) {
            HashMap<String, Pokemon> result = new HashMap<>();

            DetailRequest request = new DetailRequest();

            for (int i = 0; i < lists[0].size(); i++) {

                int no = lists[0].get(i);

                Pokemon pokemon;

                if (PokemonMap.POKEMON_MAP.containsKey(Integer.toString(no))) {
                    pokemon = PokemonMap.POKEMON_MAP.get(Integer.toString(no));
                    if (pokemon.getCaptorId() == null || pokemon.getCaptorId().isEmpty()) {
                        String myCaptorId = CaptorUtil.getMyCaptorId(activity);
                        pokemon.setCaptorId(myCaptorId);
                        db.add(pokemon);
                    }
                } else {
                    pokemon = request.requestDetail(no);
                    if (pokemon != null) {
                        db.add(pokemon);
                    }
                }
                result.put(Integer.toString(i + 1), pokemon);
            }

            return result;
        }

        @Override
        protected void onPostExecute(HashMap<String, Pokemon> pokemonHashMap) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment, PokemonListFragment.newInstance(pokemonHashMap))
                    .commit();
        }
    }
}
