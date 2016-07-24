package me.myreco.up;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.firebase.analytics.FirebaseAnalytics;

public class HomeFragment extends Fragment{

    private WebView wv;
    private FirebaseAnalytics FirebaseAnalytics;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.home_fragment, container, false);

        FirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
        FirebaseAnalytics.logEvent("HomeFragment", null);

        wv = (WebView) v.findViewById(R.id.webView);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

        });
        wv.loadUrl(getString(R.string.base_url));
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
                    // トップページならActivity終了
                    else {
                        getActivity().finish();
                        return true;
                    }
                }
                return false;
            }
        });


        return v;
    }
}
