# Material Preference [ ![Download](https://api.bintray.com/packages/anggrayudi/maven/materialpreference-java/images/download.svg)](https://bintray.com/anggrayudi/maven/materialpreference-java/_latestVersion)
A library designed for people who love simplicity. Hate the old preference style? Try this library.

It combines libraries from `androidx.preference` and `net.xpece.android.support.preference`.
Available from API 17.
<br><a href="https://play.google.com/store/apps/details?id=com.anggrayudi.materialpreference.sample" target="_blank"><img alt="Google Play" height="80" src="https://play.google.com/intl/en_US/badges/images/generic/en_badge_web_generic.png" align="right"/></a><br><br><br>

## Screenshots

![Alt text](art/1-generic.png?raw=true "Material Preference")
![Alt text](art/2-generic.png?raw=true "Material Preference")
![Alt text](art/3-generic.png?raw=true "DatePreference")
![Alt text](art/4-generic.png?raw=true "ListPreference")

## Note

This library is available in 2 versions:
1. [Version `2.x.x`](https://github.com/anggrayudi/MaterialPreference/tree/java), built in Java
2. [Version `3.x.x` and higher](https://github.com/anggrayudi/MaterialPreference), built in Kotlin

This Java library will be the second priority. So I will be more active in Kotlin library. You can fork this Java branch and build your own version if you feel it is slow in maintenance.

Writing code in Java is slow, and that's why I decided to migrate to Kotlin.

## Usage

**Note**: If you encounter error `Failed to resolve com.anggrayudi:materialpreference-java:x.x.x`, then add the following config:

```gradle
repositories {
    maven { url 'https://dl.bintray.com/anggrayudi/maven/' }
}

dependencies {
    implementation 'com.anggrayudi:materialpreference-java:2.2.0'
}
```

From your `preferences.xml`:

```xml
<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android">

    <!-- To make Preferences floating, you must wrap them inside PreferenceCategory -->
    <PreferenceCategory>
        <Preference
            android:key="about"
            android:title="About"
            android:icon="@drawable/..."
            app:tintIcon="?colorAccent"
            app:legacySummary="false"/>
    </PreferenceCategory>
</PreferenceScreen>
```

From your `SettingsFragment`:

```java
public class SettingsFragment extends PreferenceFragmentMaterial {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences); 
    }

    public static SettingsFragment newInstance(String rootKey) {
        Bundle args = new Bundle();
        args.putString(ARG_PREFERENCE_ROOT, rootKey);
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment; 
    }
}
```

From your `SettingsActivity`:

```java
public class SettingsActivity extends PreferenceActivityMaterial {

    private SettingsFragment mSettingsFragment;
    private static final String TAG = "SettingsFragment.YOUR_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        
        if (savedInstanceState == null) {
            mSettingsFragment = SettingsFragment.newInstance(null);
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mSettingsFragment, TAG).commit();
        } else {
            mSettingsFragment = (SettingsFragment) getSupportFragmentManager().findFragmentByTag(TAG);
            setTitle(mSettingsFragment.getPreferenceFragmentTitle());
        }
    }

    @Override
    protected PreferenceFragmentMaterial onBuildPreferenceFragment(String rootKey) {
        return SettingsFragment.newInstance(rootKey);
    }

    @Override
    public void onBackStackChanged() {
        mSettingsFragment = (SettingsFragment) getSupportFragmentManager().findFragmentByTag(TAG);
        setTitle(mSettingsFragment.getPreferenceFragmentTitle());
    }
}
```

## Preferences

- `Preference`
- `CheckBoxPreference`
- `SwitchPreference`
- `EditTextPreference`
- `ListPreference`
- `MultiSelectListPreference`
- `SeekBarDialogPreference`
- `SeekBarPreference`
- `RingtonePreference`
- `IndicatorPreference`
- `DatePreference`
- `TimePreference`
- `FolderPreference` (requires API 21)

### RingtonePreference

`RingtonePreference` will show only system ringtone sounds by default.
If you want to include sounds from the external storage your app needs to request
`android.permission.READ_EXTERNAL_STORAGE` permission in its manifest.
Don't forget to check this runtime permission before opening ringtone picker on API 23.

## Donation
Any donation you give is really helpful for us to develop this library. It feels like energy from power stone.

<a href="https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=TGPGSY66LKUMN&source=url" target="_blank"><img alt="Donate with PayPal" src="https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif" border="0"/></a>

## License

    Copyright 2018-2019 Anggrayudi Hardiannicko A.
 
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
 
        http://www.apache.org/licenses/LICENSE-2.0
 
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
