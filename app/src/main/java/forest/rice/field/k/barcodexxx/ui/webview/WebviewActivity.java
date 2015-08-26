package forest.rice.field.k.barcodexxx.ui.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import forest.rice.field.k.barcodexxx.R;
import forest.rice.field.k.barcodexxx.entity.Pokemon;
import forest.rice.field.k.barcodexxx.util.PokemonUtil;

public class WebviewActivity extends AppCompatActivity {

    public static final String EXTRA_URL = "extra_url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();

            Intent intent = getIntent();
            String url = intent.getStringExtra(EXTRA_URL);

            savedInstanceState.putString(EXTRA_URL, url);
        }

        String url = savedInstanceState.getString(EXTRA_URL);

        WebView webview = (WebView) findViewById(R.id.webview);
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(url);
    }

    public static Intent createStartIntent(Context context, Pokemon pokemon) {
        Intent intent = new Intent(context, WebviewActivity.class);
        intent.putExtra(WebviewActivity.EXTRA_URL, PokemonUtil.getDetailUrl(pokemon));
        return intent;

    }


}
