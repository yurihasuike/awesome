package me.myreco.up;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.firebase.analytics.FirebaseAnalytics;

public class UserFragment extends Fragment {

    private WebView wv;
    private View v;
    private FirebaseAnalytics FirebaseAnalytics;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.user_fragment, container, false);

        FirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
        FirebaseAnalytics.logEvent("UserFragment", null);

        return v;
    }

    public void onResume() {
        super.onResume();

        SavePreference savePreference = new SavePreference();

        String user_name = savePreference.load_username(getActivity());
        final String Url = getString(R.string.base_url) + user_name;

        wv = (WebView) v.findViewById(R.id.webView);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

        });

        wv.loadUrl(Url);
        wv.setFocusableInTouchMode(true);
        wv.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    // ページを進んでいたら
                    if (wv.canGoBack()) {
                        wv.goBack();
                        return true;
                    }
                }
                return false;
            }
        });

   }
}