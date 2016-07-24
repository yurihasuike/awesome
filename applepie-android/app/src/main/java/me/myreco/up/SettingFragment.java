package me.myreco.up;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.preference.Preference;

import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

public class SettingFragment extends PreferenceFragmentCompat{

    private FirebaseAnalytics FirebaseAnalytics;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String s) {
        addPreferencesFromResource(R.xml.pref);

        FirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
        FirebaseAnalytics.logEvent("SettingFragment", null);
    }

    @Override
    public void onResume(){
        super.onResume();

        final SavePreference savePreference = new SavePreference();
        PreferenceScreen pro_account = (PreferenceScreen)findPreference("pro_account");
        PreferenceScreen contact = (PreferenceScreen)findPreference("contact");
        PreferenceScreen policy = (PreferenceScreen)findPreference("policy");
        PreferenceScreen terms = (PreferenceScreen)findPreference("terms");
        PreferenceScreen logout = (PreferenceScreen)findPreference("logout");
        PreferenceScreen profile = (PreferenceScreen)findPreference("profile");

        pro_account.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                String username;
                if((username = savePreference.load_username(getActivity())) != null){
                    username = "?username=" + username;
                }else{
                    username = "";
                }
                Intent nextActivity = new Intent(getActivity(), EachSettingActivity.class);
                nextActivity.putExtra("url", getString(R.string.base_url)+"proaccount/"+ username);
                startActivity(nextActivity);

                FirebaseAnalytics.logEvent("ProaccountLinkClicked", null);

                return true;
            }
        });

        contact.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent nextActivity = new Intent(getActivity(), EachSettingActivity.class);
                nextActivity.putExtra("url", getString(R.string.base_url)+"contact/");
                startActivity(nextActivity);

                FirebaseAnalytics.logEvent("ContactLinkClicked", null);

                return true;
            }
        });

        policy.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent nextActivity = new Intent(getActivity(), EachSettingActivity.class);
                nextActivity.putExtra("url", getString(R.string.base_url)+"privacy/");
                startActivity(nextActivity);

                FirebaseAnalytics.logEvent("PrivacyLinkClicked", null);

                return true;
            }
        });

        terms.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent nextActivity = new Intent(getActivity(), EachSettingActivity.class);
                nextActivity.putExtra("url", getString(R.string.base_url)+"terms/");
                startActivity(nextActivity);

                FirebaseAnalytics.logEvent("TermsLinkClicked", null);

                return true;
            }
        });

        logout.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if(savePreference.load_aToken(getActivity()) == null){
                    new AlertDialog.Builder(getActivity())
                            .setTitle("エラー")
                            .setMessage("ログインされていません。")
                            .setPositiveButton("OK", null)
                            .show();
                }else{
                    savePreference.save_aToken(getActivity(), null);
                    new AlertDialog.Builder(getActivity())
                            .setMessage("ログアウトしました。")
                            .setPositiveButton("OK", null)
                            .show();

                    savePreference.save_username(getActivity(), null);

                    MainActivity activity = (MainActivity)getActivity();
                    activity.notifyFragmentChange();

                    FirebaseAnalytics.logEvent("Logout", null);
                }
                return true;

            }
        });
        profile.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent nextActivity = new Intent(getActivity(), EdditingProfile.class);

                startActivity(nextActivity);

                FirebaseAnalytics.logEvent("PrivacyLinkClicked", null);

                return true;
            }
        });
    }
}
