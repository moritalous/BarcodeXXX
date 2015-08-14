package forest.rice.field.k.barcodexxx.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import forest.rice.field.k.barcodexxx.R;

public class RankingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, RankinkgFragment.newInstance("", ""))
                .commit();
    }

}
