package forest.rice.field.k.barcodexxx.ui.zukan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Arrays;
import java.util.Collections;

import forest.rice.field.k.barcodexxx.R;
import forest.rice.field.k.barcodexxx.db.CaptorFirebaseDB;
import forest.rice.field.k.barcodexxx.db.PokemonFirebaseDB;
import forest.rice.field.k.barcodexxx.ui.ranking.RankingActivity;
import forest.rice.field.k.barcodexxx.ui.captor.CaptorResultActivity;
import forest.rice.field.k.barcodexxx.ui.fragment.PokemonListFragment;
import forest.rice.field.k.barcodexxx.ui.setting.SettingActivity;
import forest.rice.field.k.barcodexxx.util.CaptorUtil;

public class ZukanActivity extends AppCompatActivity {

    private static PokemonFirebaseDB pokemonDb = null;
    private static CaptorFirebaseDB captorDb = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zukan);

        pokemonDb = PokemonFirebaseDB.getInstance(this);
        captorDb = CaptorFirebaseDB.getInstance(this);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        FloatingActionButton actionButton = (FloatingActionButton) findViewById(R.id.fab);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickFab();
            }
        });

//        // 初回のデータ取得をちょっとだけ待つ
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//        }

        initUserId();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, PokemonListFragment.newInstance())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("ランキング");
        menu.add("設定");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getTitle().toString()) {
            case "ランキング": {
                Intent intent = new Intent(getApplicationContext(), RankingActivity.class);
                startActivity(intent);
            }
            break;
            case "設定": {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }
            break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK: {
                IntentResult parseResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
                if (parseResult == null) {
                }
                String code = parseResult.getContents();
                Intent intent = new Intent(this, CaptorResultActivity.class);
                intent.putExtra(CaptorResultActivity.EXTRA_CODE, code);
                startActivity(intent);
            }
            break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void onClickFab() {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan(Collections.unmodifiableList(Arrays.asList("UPC_A", "UPC_E", "EAN_8", "EAN_13", "RSS_14", "QR_CODE")));
    }

    private void initUserId() {

        String myCaptorId = CaptorUtil.getMyCaptorId(this);

    }
}
