package forest.rice.field.k.barcodexxx.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import forest.rice.field.k.barcodexxx.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        /*
//        Forebase初期化
//         */
//        Firebase.setAndroidContext(this);
//        Firebase myFirebaseRef = new Firebase("https://barcodepokemon.firebaseio.com/");
//
//        /*
//        DB値取得
//         */
//        myFirebaseRef.child("Pokemon").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                PokemonFirebaseDB.pokeDic = dataSnapshot.getValue(PokemonFirebaseDB.class);
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//            }
//        });
//
//        Query queryRef = myFirebaseRef.child("Pokemon").orderByChild("No");
//        queryRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                Pokemon p = dataSnapshot.getValue(Pokemon.class);
//                System.out.println(p.getName());
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });


//        PokemonFirebaseDB pokeDic = new PokemonFirebaseDB();
//
//        Pokemon pokemon = new Pokemon();
////        pokemon.setNo("1");
////        pokemon.setName("ポケモン１");
////        pokemon.setImageUrlMedium("");
//        pokeDic.add(pokemon);
//
//        myFirebaseRef.child("Pokemon").setValue(pokeDic);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
