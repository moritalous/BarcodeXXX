package forest.rice.field.k.barcodexxx.db;

import android.content.Context;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import forest.rice.field.k.barcodexxx.entity.Captor;
import forest.rice.field.k.barcodexxx.entity.CaptorMap;
import forest.rice.field.k.barcodexxx.eventbus.EventBusManager;

public class CaptorFirebaseDB {

    private static final String SCHEME_NAME = "Captor";

    private Firebase firebase = null;

    private static CaptorFirebaseDB db = null;

    private CaptorFirebaseDB(Context context) {
        initFirebaseDB(context);
    }

    public static CaptorFirebaseDB getInstance(Context context) {
        if (db != null) {
            return db;
        }
        db = new CaptorFirebaseDB(context);
        return db;
    }

    private void initFirebaseDB(Context context) {
        firebase = FirebaseManager.getInstance(context);
        firebase.child(SCHEME_NAME).addChildEventListener(childEventListener);
        firebase.child(SCHEME_NAME).keepSynced(true);
    }

    public void add(Captor captor) {
        firebase.child(SCHEME_NAME).child(captor.getCaptorId()).setValue(captor);
    }

    private ChildEventListener childEventListener = new ChildEventListener() {

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            Captor captor = dataSnapshot.getValue(Captor.class);
            CaptorMap.CAPTOR.put(captor.getCaptorId(), captor);
            EventBusManager.getEventBus().post(EventBusManager.Event.POKEMON_ADD);
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            Captor captor = dataSnapshot.getValue(Captor.class);
            CaptorMap.CAPTOR.put(captor.getCaptorId(), captor);
            EventBusManager.getEventBus().post(EventBusManager.Event.POKEMON_ADD);
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {

        }
    };

}
