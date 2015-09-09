package forest.rice.field.k.barcodexxx.ui.swap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Random;

import forest.rice.field.k.barcodexxx.R;
import forest.rice.field.k.barcodexxx.db.PokemonFirebaseDB;
import forest.rice.field.k.barcodexxx.entity.Pokemon;
import forest.rice.field.k.barcodexxx.entity.PokemonMap;
import forest.rice.field.k.barcodexxx.util.CaptorUtil;
import forest.rice.field.k.barcodexxx.util.PokemonUtil;

public class SwapActivity extends AppCompatActivity {

    public static final String EXTRA_SWAP_TARGET_NO = "swap_target_no";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swap);

        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
            savedInstanceState.putString(EXTRA_SWAP_TARGET_NO, getIntent().getStringExtra(EXTRA_SWAP_TARGET_NO));
        }
        String pokemonNo = savedInstanceState.getString(EXTRA_SWAP_TARGET_NO);

        final Pokemon yourPokemon = PokemonMap.POKEMON_MAP.get(pokemonNo);
        final Pokemon myPokemon = getMySwapPokemon();
        {
            View layout = findViewById(R.id.your_pokemon);
            TextView textNo = (TextView) layout.findViewById(R.id.text_no);
            textNo.setText(PokemonUtil.getNoByStringWithFormat(yourPokemon));
            TextView textName = (TextView) layout.findViewById(R.id.text_name);
            textName.setText(yourPokemon.getName());
            ImageView imageView = (ImageView) layout.findViewById(R.id.image_view);
            Glide.with(this).load(PokemonUtil.getLargeImageUrl(yourPokemon)).into(imageView);
        }
        {
            View layout = findViewById(R.id.my_pokemon);
            TextView textNo = (TextView) layout.findViewById(R.id.text_no);
            textNo.setText(PokemonUtil.getNoByStringWithFormat(myPokemon));
            TextView textName = (TextView) layout.findViewById(R.id.text_name);
            textName.setText(myPokemon.getName());
            ImageView imageView = (ImageView) layout.findViewById(R.id.image_view);
            Glide.with(this).load(PokemonUtil.getLargeImageUrl(myPokemon)).into(imageView);
        }

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PokemonFirebaseDB db = PokemonFirebaseDB.getInstance(getBaseContext());
                String yourCaptorId = yourPokemon.getCaptorId();
                String myCaptorId = CaptorUtil.MY_CAPTOR_ID;
                String message;
                int num = new Random().nextInt(4);
                switch (num) {
//                    case 0: {
//                        // 交換失敗。逃げ出す
//                        message = "こうかんしっぱい！！\r\nきみのポケモンはにげだした";
//                        myPokemon.setCaptorId(null);
//                        db.add(myPokemon);
//                    }
//                    break;
//                    case 1: {
//                        // 交換失敗。奪われる
//                        message = "こうかんしっぱい！！\nきみのポケモンはうばわれた";
//                        myPokemon.setCaptorId(yourCaptorId);
//                        db.add(myPokemon);
//                    }
//                    break;
                    default: {
                        // 交換成功
                        message = "こうかんせいこう！！";

                        yourPokemon.setCaptorId(myCaptorId);
                        myPokemon.setCaptorId(yourCaptorId);

                        db.add(yourPokemon);
                        db.add(myPokemon);
                    }
                    break;
                }

                Toast.makeText(SwapActivity.this, message, Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }

    public static Intent createStartIntent(Context context, String pokemonNo) {
        Intent intent = new Intent(context, SwapActivity.class);
        intent.putExtra(EXTRA_SWAP_TARGET_NO, pokemonNo);
        return intent;
    }

    public Pokemon getMySwapPokemon() {
        PokemonMap map = PokemonUtil.filterPokemonMapMine();
        String[] keySet = map.keySet().toArray(new String[map.keySet().size()]);

        Random random = new Random();
        int no = random.nextInt(keySet.length);
        return map.get(keySet[no]);
    }
}
