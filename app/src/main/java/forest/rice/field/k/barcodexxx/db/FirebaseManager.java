package forest.rice.field.k.barcodexxx.db;

import android.content.Context;

import com.firebase.client.Firebase;

public class FirebaseManager {

    private static Firebase firebase = null;

    private FirebaseManager() {
    }

    public static Firebase getInstance(Context context) {
        if (firebase != null) {
            return firebase;
        }

        Firebase.getDefaultConfig().setPersistenceEnabled(true);
        Firebase.setAndroidContext(context);
        firebase = new Firebase("https://barcodepokemon.firebaseio.com/");

        return firebase;
    }
}
