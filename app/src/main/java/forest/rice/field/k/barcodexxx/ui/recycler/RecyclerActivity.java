package forest.rice.field.k.barcodexxx.ui.recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import forest.rice.field.k.barcodexxx.R;

public class RecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        recyclerView.setHasFixedSize(true);
//
//        List<Pokemon> pokemonList = new ArrayList<>();
//
//        Pokemon p1 = new Pokemon();
//        p1.setNo(001);
//        p1.setName("フシギダネ");
//        p1.setImageUrl("ff08ec6198db300abc91e69605469427.png");
//        pokemonList.add(p1);
//
//        Pokemon p2 = new Pokemon();
//        p2.setNo(717);
//        p2.setName("イベルタル");
//        p2.setImageUrl("8585e72ad94fb68c414d913437e04eb5.png");
//        pokemonList.add(p2);
//
//        Pokemon p3 = new Pokemon();
//        p3.setNo(719);
//        p3.setName("ディアンシー");
//        p3.setImageUrl("97710b36442c58188f479b4794b2d570.png");
//        pokemonList.add(p3);
//
//        final CaptorResultAdapter adapter = new CaptorResultAdapter(this, pokemonList);
//        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recycler, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
