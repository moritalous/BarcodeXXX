package forest.rice.field.k.barcodexxx.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.UUID;

import forest.rice.field.k.barcodexxx.db.CaptorFirebaseDB;
import forest.rice.field.k.barcodexxx.entity.Captor;
import forest.rice.field.k.barcodexxx.entity.CaptorMap;

public class CaptorUtil {

    public static final String MY_CAPTOR_ID_KEY = "MY-CAPTOR-ID";
    public static final String MY_CAPTOR_NAME_KEY = "MY-CAPTOR-NAME";
    public static String MY_CAPTOR_ID = null;

    private static final String DEFAULT_CAPTOR_NAME = "ななしのごんべえ";

    public static String getMyCaptorId(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);

        CaptorFirebaseDB firebaseDB = CaptorFirebaseDB.getInstance(context);

        String myCaptorId = pref.getString(MY_CAPTOR_ID_KEY, null);
        String myCaptorName;
        if (myCaptorId == null) {
            myCaptorId = UUID.randomUUID().toString();
            myCaptorName = DEFAULT_CAPTOR_NAME;

            Captor captor = new Captor();
            captor.setCaptorId(myCaptorId);
            captor.setCaptorName(myCaptorName);

            firebaseDB.add(captor);
        } else {
            Captor captor = CaptorMap.CAPTOR.get(myCaptorId);
            myCaptorName = captor.getCaptorName();
        }

        pref.edit()
                .putString(MY_CAPTOR_ID_KEY, myCaptorId)
                .putString(MY_CAPTOR_NAME_KEY, myCaptorName)
                .apply();

        MY_CAPTOR_ID = myCaptorId;
        return myCaptorId;
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
        String myCaptorId = pref.getString(MY_CAPTOR_ID_KEY, "");
        String myCaptorName = pref.getString(MY_CAPTOR_NAME_KEY, "");

        Captor captor = new Captor();
        captor.setCaptorId(myCaptorId);
        captor.setCaptorName(myCaptorName);
        return captor;
    }
}
