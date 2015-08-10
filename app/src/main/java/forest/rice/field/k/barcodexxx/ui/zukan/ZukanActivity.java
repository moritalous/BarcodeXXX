package forest.rice.field.k.barcodexxx.ui.zukan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Arrays;
import java.util.Collections;

import forest.rice.field.k.barcodexxx.R;
import forest.rice.field.k.barcodexxx.db.PokemonFirebaseDB;
import forest.rice.field.k.barcodexxx.ui.captor.CaptorResultActivity;
import forest.rice.field.k.barcodexxx.ui.fragment.PokemonListFragment;

public class ZukanActivity extends AppCompatActivity {

    private static PokemonFirebaseDB db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        supportRequestWindowFeature(WindowCompat.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_zukan);

        db = PokemonFirebaseDB.getInstance(this);

//        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));

//        ActionBar actionbar = getSupportActionBar();
//        if (actionbar != null) {
//            actionbar.setHideOnContentScrollEnabled(true);
//            actionbar.setShowHideAnimationEnabled(true);
//        }

        FloatingActionButton actionButton = (FloatingActionButton) findViewById(R.id.fab);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickFab();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        // 初回のデータ取得をちょっとだけ待つ
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, PokemonListFragment.newInstance())
                .commit();
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
        intentIntegrator.initiateScan(Collections.unmodifiableList(Arrays.asList("EAN_13", "QR_CODE")));
    }
}
