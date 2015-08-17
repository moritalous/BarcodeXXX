package forest.rice.field.k.barcodexxx.ui.setting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;

import forest.rice.field.k.barcodexxx.R;
import forest.rice.field.k.barcodexxx.entity.Captor;
import forest.rice.field.k.barcodexxx.util.CaptorUtil;

public class SettingActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private SharedPreferences pref;

    EditTextPreference id;
    EditTextPreference name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting_prefference);

        pref = getPreferenceManager().getSharedPreferences();

        name = (EditTextPreference) getPreferenceScreen().findPreference(CaptorUtil.MY_CAPTOR_NAME_KEY);
        id = (EditTextPreference) getPreferenceScreen().findPreference(CaptorUtil.MY_CAPTOR_ID_KEY);

        Captor captor = CaptorUtil.createCaptor(pref);
        id.setSummary(captor.getCaptorId());
        name.setSummary(captor.getCaptorName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        switch (key) {
            case CaptorUtil.MY_CAPTOR_NAME_KEY: {
                Captor captor = CaptorUtil.createCaptor(sharedPreferences);
                name.setSummary(captor.getCaptorName());
                CaptorUtil.updateCaptor(this);
            }
            break;
            default:
                break;
        }
    }
}
