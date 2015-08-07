package forest.rice.field.k.barcodexxx.ui.zukan2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import forest.rice.field.k.barcodexxx.R;
import forest.rice.field.k.barcodexxx.fragment.PokemonListFragment;

public class ZukanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zukan);

        ArrayList<String> pokemonList = new ArrayList<>();
        pokemonList.add("001");
        pokemonList.add("002");
        pokemonList.add("003");

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, PokemonListFragment.newInstance(pokemonList))
                .commit();
    }
}
