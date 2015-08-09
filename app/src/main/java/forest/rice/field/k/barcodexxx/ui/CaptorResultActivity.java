package forest.rice.field.k.barcodexxx.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import forest.rice.field.k.barcodexxx.R;
import forest.rice.field.k.barcodexxx.entity.Pokemon;
import forest.rice.field.k.barcodexxx.entity.PokemonFirebaseDB;
import forest.rice.field.k.barcodexxx.entity.PokemonMap;
import forest.rice.field.k.barcodexxx.fragment.PokemonListFragment;
import forest.rice.field.k.barcodexxx.net.DetailRequest;

public class CaptorResultActivity extends AppCompatActivity {

    public static final String EXTRA_CODE = "code";

    private static PokemonFirebaseDB db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captor_result);

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
//        int numCode = Integer.parseInt(code);
        Double numCode =  Double.parseDouble(code);

        int count = (int)(numCode % 10) + 1;

        for(int i = 0; i < count; i++) {
            int tmpCode = (int)((numCode + (count* i)) % 719) + 1;
            captorPokemonNo.add(tmpCode);
        }



        new CaptorAsyncTaks().execute(captorPokemonNo);

//        ArrayList<String> pokemonList = new ArrayList<>();
//        pokemonList.add("001");
//        pokemonList.add("002");
//        pokemonList.add("003");

    }

//    @Subscribe
//    public void event(Event event) {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fragment, PokemonListFragment.newInstance())
//                .commit();
//    }

    private class CaptorAsyncTaks extends  AsyncTask<List<Integer>, Integer, HashMap<String, Pokemon>> {

        @Override
        protected HashMap<String, Pokemon> doInBackground(List<Integer>... lists) {
            HashMap<String, Pokemon> result = new HashMap<>();

            DetailRequest request = new DetailRequest();

            for(int i = 0; i < lists[0].size(); i++){

                int no = lists[0].get(i);

                Pokemon pokemon;

                if(PokemonMap.POKEMON_MAP.containsKey(Integer.toString(no))) {
                    pokemon = PokemonMap.POKEMON_MAP.get(Integer.toString(no));
                } else {
                    pokemon = request.requestDetail(no);
                    if(pokemon != null) {
                        db.add(pokemon);
                    }
                }

                result.put(Integer.toString(i+1), pokemon);
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



//    private class AsyncTask extends android.os.AsyncTask<String, String, String> {
//
//        @Override
//        protected String doInBackground(String... strings) {
//
//            DetailRequest request = new DetailRequest();
//            Pokemon pokemon = request.requestDetail(50);
//            db.add(pokemon);
//
////                DetailRequest request = new DetailRequest();
////                Pokemon pokemon = request.requestDetail(3);
////
////                db.add(pokemon);
//
//
//            System.out.println(pokemon.getName());
//
//            return "";
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.fragment, PokemonListFragment.newInstance())
//                    .commit();
//        }
//    }


}
