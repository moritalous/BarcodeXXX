package forest.rice.field.k.barcodexxx.ui.zukan;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import forest.rice.field.k.barcodexxx.R;
import forest.rice.field.k.barcodexxx.entity.PokemonFirebaseDB;
import forest.rice.field.k.barcodexxx.event.Event;
import forest.rice.field.k.barcodexxx.event.Eventbus;
import forest.rice.field.k.barcodexxx.fragment.PokemonListFragment;

public class ZukanActivity extends AppCompatActivity {

    private static PokemonFirebaseDB db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zukan);

        Eventbus.EVENT_BUS.register(Event.SINGLE_VALUE_EVENT);

        db = PokemonFirebaseDB.getInstance(this);
    }

    @Nullable
    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, PokemonListFragment.newInstance())
                .commit();

        return super.onCreateView(name, context, attrs);
    }
}
