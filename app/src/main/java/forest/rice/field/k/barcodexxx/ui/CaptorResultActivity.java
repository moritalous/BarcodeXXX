package forest.rice.field.k.barcodexxx.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import forest.rice.field.k.barcodexxx.R;
import forest.rice.field.k.barcodexxx.entity.Pokemon;
import forest.rice.field.k.barcodexxx.entity.PokemonFirebaseDB;
import forest.rice.field.k.barcodexxx.event.Event;
import forest.rice.field.k.barcodexxx.event.Eventbus;
import forest.rice.field.k.barcodexxx.fragment.PokemonListFragment;
import forest.rice.field.k.barcodexxx.net.DetailRequest;

public class CaptorResultActivity extends AppCompatActivity {

    private static PokemonFirebaseDB db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captor_result);

        Eventbus.EVENT_BUS.register(Event.SINGLE_VALUE_EVENT);

        db = PokemonFirebaseDB.getInstance(this);



        new AsyncTask().execute();

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


        private class AsyncTask extends android.os.AsyncTask<String, String, String> {

            @Override
            protected String doInBackground(String... strings) {

                DetailRequest request = new DetailRequest();
                Pokemon pokemon = request.requestDetail(50);
                db.add(pokemon);

//                DetailRequest request = new DetailRequest();
//                Pokemon pokemon = request.requestDetail(3);
//
//                db.add(pokemon);


              System.out.println(pokemon.getName());

                return "";
            }

        @Override
        protected void onPostExecute(String s) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment, PokemonListFragment.newInstance())
                    .commit();
        }
        }


    }
