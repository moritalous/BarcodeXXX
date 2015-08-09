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
import forest.rice.field.k.barcodexxx.ui.fragment.PokemonListFragment;
import forest.rice.field.k.barcodexxx.ui.captor.CaptorResultActivity;

public class ZukanActivity extends AppCompatActivity {

    private static PokemonFirebaseDB db = null;

    private static final int REQUEST_CODE_ = 9999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zukan);

        db = PokemonFirebaseDB.getInstance(this);

        FloatingActionButton actionButton = (FloatingActionButton)findViewById(R.id.fab);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickFab();
            }
        });

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, PokemonListFragment.newInstance())
                .commit();
    }

    private void onClickFab() {
//        Intent intent = new Intent(this, StartScanActivity.class);
//        startActivityForResult(intent, REQUEST_CODE_);
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan(Collections.unmodifiableList(Arrays.asList("EAN_13", "QR_CODE")));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

//        switch (requestCode) {
//            case REQUEST_CODE_:
//            {
                switch (resultCode) {
                    case RESULT_OK:
                    {
                        IntentResult parseResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
                        if(parseResult == null) {
                        }
                        String code =parseResult.getContents();
                        Intent intent = new Intent(this, CaptorResultActivity.class);
                        intent.putExtra(CaptorResultActivity.EXTRA_CODE, code);
                        startActivity(intent);
                    }
                    break;
                }
//            }
//            break;
//            default:
//
//        }



        super.onActivityResult(requestCode, resultCode, data);
    }
}
