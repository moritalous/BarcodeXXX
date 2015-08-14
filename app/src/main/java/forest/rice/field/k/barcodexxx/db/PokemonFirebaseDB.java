package forest.rice.field.k.barcodexxx.db;

import android.content.Context;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import forest.rice.field.k.barcodexxx.entity.Pokemon;
import forest.rice.field.k.barcodexxx.entity.PokemonMap;
import forest.rice.field.k.barcodexxx.util.PokemonUtil;

public class PokemonFirebaseDB {

    private static final String SCHEME_NAME = "Pokemon";

    private Firebase firebase = null;

    private static PokemonFirebaseDB pokeDic = null;

    private PokemonFirebaseDB(Context context) {
        initFirebaseDB(context);
    }

    public static PokemonFirebaseDB getInstance(Context context) {
        if (pokeDic != null) {
            return pokeDic;
        }

        pokeDic = new PokemonFirebaseDB(context);
        return pokeDic;
    }

    public void add(Pokemon pokemon) {
        firebase.child(SCHEME_NAME).child(PokemonUtil.getNoByString(pokemon)).setValue(pokemon);
    }

    private void initFirebaseDB(Context context) {

        firebase = FirebaseManager.getInstance(context);//.child(SCHEME_NAME);
        firebase.child(SCHEME_NAME).addChildEventListener(childEventListener);
        firebase.child(SCHEME_NAME).keepSynced(true);

    }

    private ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            Pokemon pokemon = dataSnapshot.getValue(Pokemon.class);
            PokemonMap.POKEMON_MAP.put(PokemonUtil.getNoByString(pokemon), pokemon);
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            Pokemon pokemon = dataSnapshot.getValue(Pokemon.class);
            PokemonMap.POKEMON_MAP.put(PokemonUtil.getNoByString(pokemon), pokemon);
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            Pokemon pokemon = dataSnapshot.getValue(Pokemon.class);
            PokemonMap.POKEMON_MAP.remove(PokemonUtil.getNoByString(pokemon));
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {

        }
    };
}
