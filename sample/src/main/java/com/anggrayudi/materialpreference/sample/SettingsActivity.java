package com.anggrayudi.materialpreference.sample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;

import com.anggrayudi.materialpreference.PreferenceActivityMaterial;
import com.anggrayudi.materialpreference.PreferenceFragmentMaterial;

public class SettingsActivity extends PreferenceActivityMaterial {

    private static final String TAG = "Settings";

    private SettingsFragment mSettingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            mSettingsFragment = SettingsFragment.newInstance(null);
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mSettingsFragment, TAG).commit();
        } else {
            // refresh this activity's title
            onBackStackChanged();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        menu.findItem(R.id.action_donate).setIntent(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=TGPGSY66LKUMN&source=url")));
        return super.onCreateOptionsMenu(menu);
    }
}
