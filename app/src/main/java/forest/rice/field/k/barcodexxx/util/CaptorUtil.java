package forest.rice.field.k.barcodexxx.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.UUID;

import forest.rice.field.k.barcodexxx.db.CaptorFirebaseDB;
import forest.rice.field.k.barcodexxx.entity.Captor;

public class CaptorUtil {

    private static final String MY_CAPTOR_ID_KEY = "MY-CAPTOR-ID";
    public static String MY_CAPTOR_ID = null;

    public static String getMyCaptorId(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);

        String myCaptorId = pref.getString(MY_CAPTOR_ID_KEY, null);
        if (myCaptorId == null) {
            myCaptorId = UUID.randomUUID().toString();
            pref.edit().putString(MY_CAPTOR_ID_KEY, myCaptorId).apply();

            Captor captor = new Captor();
            captor.setCaptorId(myCaptorId);
            captor.setCaptorName(myCaptorId);

            CaptorFirebaseDB firebaseDB = CaptorFirebaseDB.getInstance(context);
            firebaseDB.add(captor);
        }

        MY_CAPTOR_ID = myCaptorId;

        return myCaptorId;
    }

    public static String getCaptorName(Captor captor) {
        if (captor.getCaptorName().isEmpty()) {
            return captor.getCaptorId().substring(0, 5);
        }
        return captor.getCaptorName();
    }
}
