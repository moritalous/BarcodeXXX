package forest.rice.field.k.barcodexxx.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import forest.rice.field.k.barcodexxx.db.CaptorFirebaseDB;
import forest.rice.field.k.barcodexxx.entity.Captor;
import forest.rice.field.k.barcodexxx.entity.Pokemon;
import forest.rice.field.k.barcodexxx.entity.PokemonMap;

public class CaptorUtil {

    public static final String MY_CAPTOR_ID_KEY = "MY-CAPTOR-ID";
    public static final String MY_CAPTOR_NAME_KEY = "MY-CAPTOR-NAME";
    public static String MY_CAPTOR_ID = null;

    private static final String DEFAULT_CAPTOR_NAME = "ななしのごんべえ";

    public static String getMyCaptorId(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);

        CaptorFirebaseDB firebaseDB = CaptorFirebaseDB.getInstance(context);
        Captor captor = createCaptor(pref);
        firebaseDB.add(captor);

        pref.edit()
                .putString(MY_CAPTOR_ID_KEY, captor.getCaptorId())
                .putString(MY_CAPTOR_NAME_KEY, captor.getCaptorName())
                .apply();

        MY_CAPTOR_ID = captor.getCaptorId();
        return MY_CAPTOR_ID;
    }

    public static String getCaptorName(Captor captor) {
        if (captor.getCaptorName().isEmpty()) {
            return captor.getCaptorId().substring(0, 5);
        }
        return captor.getCaptorName();
    }

    public static void updateCaptor(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);

        Captor captor = createCaptor(pref);

        CaptorFirebaseDB firebaseDB = CaptorFirebaseDB.getInstance(context);
        firebaseDB.add(captor);
    }

    public static Captor createCaptor(SharedPreferences pref) {
        String myCaptorId = pref.getString(MY_CAPTOR_ID_KEY, UUID.randomUUID().toString());
        String myCaptorName = pref.getString(MY_CAPTOR_NAME_KEY, DEFAULT_CAPTOR_NAME);

        Captor captor = new Captor();
        captor.setCaptorId(myCaptorId);
        captor.setCaptorName(myCaptorName);
        return captor;
    }

    public static boolean isFlee() {
        Random random = new Random();

        int num = random.nextInt(10);

        return num < 4;
    }

    public static ArrayList<Integer> createFleePokemonNo(int count) {

        ArrayList<Integer> pokemonNoList = new ArrayList<>();

        String[] keySet = PokemonMap.POKEMON_MAP.keySet().toArray(new String[0]);

        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int no = random.nextInt(keySet.length);
            Pokemon pokemon = PokemonMap.POKEMON_MAP.get(keySet[no]);
            pokemonNoList.add(pokemon.getNo());
        }
        return pokemonNoList;
    }

}
