package forest.rice.field.k.barcodexxx.ui.setting;

import android.content.Context;
import android.preference.EditTextPreference;
import android.util.AttributeSet;

public class DummyEditTextPreference extends EditTextPreference {
    public DummyEditTextPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DummyEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DummyEditTextPreference(Context context) {
        super(context);
    }

    @Override
    protected void onClick() {
    }

}
