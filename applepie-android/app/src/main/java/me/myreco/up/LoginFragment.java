package me.myreco.up;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

import me.myreco.up.LoginApi.Login;
import me.myreco.up.LoginApi.LoginResult;
import me.myreco.up.LoginApi.LoginApi;
import me.myreco.up.UserInfoApi.UserInfo;
import me.myreco.up.UserInfoApi.UserInfoApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.google.firebase.analytics.FirebaseAnalytics;

public class LoginFragment extends Fragment {

    private EditText mailEdit;
    private EditText passEdit;
    private Button loginButton;
    private UserInfoApi userInfoApi;
    private TextView privacy_link, terms_link, forgotten_link, user_regist;
    private static final int USER_REGIST = 1;
    private FirebaseAnalytics FirebaseAnalytics;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.login_fragment, container, false);

        FirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
        FirebaseAnalytics.logEvent("LoginFragment", null);

        mailEdit = (EditText)v.findViewById(R.id.mailEdit);
        passEdit = (EditText)v.findViewById(R.id.passEdit);
        loginButton = (Button)v.findViewById(R.id.loginButton);
        privacy_link = (TextView)v.findViewById(R.id.privacy_link);
        terms_link = (TextView)v.findViewById(R.id.terms_link);
        forgotten_link = (TextView)v.findViewById(R.id.forgotten_link);
        user_regist = (TextView)v.findViewById(R.id.user_regist);

        //テキストエリアからタブにフォーカスが移るのを回避
        View.OnTouchListener focus_listener = new View.OnTouchListener()
                {
                    @Override
                    public boolean onTouch(View v, MotionEvent event)
                    {
                    v.requestFocusFromTouch();
                    return false;
                }
                };

        mailEdit.setOnTouchListener(focus_listener);
        passEdit.setOnTouchListener(focus_listener);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name = String.valueOf(mailEdit.getText());
                String pass = String.valueOf(passEdit.getText());

                final SavePreference savePreference = new SavePreference();

                Login login = new Login(name, pass);
                LoginApi api = ServiceGenerator.createService(LoginApi.class);
                Call<LoginResult> call = api.login(login);

                call.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> loginResultResponse) {
                        try {
                            String aToken = loginResultResponse.body().getKey();
                            savePreference.save_aToken(getActivity(), aToken);
                            new AlertDialog.Builder(getActivity())
                                    .setMessage("ログインに成功しました")
                                    .setPositiveButton("OK", null)
                                    .show();

                            userInfoApi = ServiceGenerator.createService(UserInfoApi.class, aToken);
                            Call<UserInfo> userInfoCall = userInfoApi.getUserInfo();

                            userInfoCall.enqueue(new Callback<UserInfo>() {
                                @Override
                                public void onResponse(Call<UserInfo> call, Response<UserInfo> userInfoResponse) {
                                    String user_name = userInfoResponse.body().getUsername();
                                    savePreference.save_username(getActivity(), user_name);

                                    MainActivity activity = (MainActivity) getActivity();
                                    activity.notifyFragmentChange();
                                }

                                @Override
                                public void onFailure(Call<UserInfo> call, Throwable t) {
                                }
                            });

                            FirebaseAnalytics.logEvent("LoginButtonClicked", null);

                        } catch (NullPointerException e) {
                            new AlertDialog.Builder(getActivity())
                                    .setTitle("エラー")
                                    .setMessage("ログイン名、またはパスワードが間違っています")
                                    .setPositiveButton("OK", null)
                                    .show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        new AlertDialog.Builder(getActivity())
                                .setTitle("エラー")
                                .setMessage("ログインに失敗しました")
                                .setPositiveButton("OK", null)
                                .show();
                    }
                });
            }

        });

        MovementMethod movementMethod = LinkMovementMethod.getInstance();
        privacy_link.setMovementMethod(movementMethod);
        terms_link.setMovementMethod(movementMethod);
        forgotten_link.setMovementMethod(movementMethod);

        privacy_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(getActivity(), EachSettingActivity.class);
                nextActivity.putExtra("url", getString(R.string.base_url)+"privacy/");
                startActivity(nextActivity);

                FirebaseAnalytics.logEvent("PrivacyLinkClicked", null);
            }
        });

        terms_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(getActivity(), EachSettingActivity.class);
                nextActivity.putExtra("url", getString(R.string.base_url)+"terms/");
                startActivity(nextActivity);

                FirebaseAnalytics.logEvent("TermsLinkClicked", null);
            }
        });

        forgotten_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(getActivity(), EachSettingActivity.class);
                nextActivity.putExtra("url", getString(R.string.base_url)+"account/password/reset/");
                startActivity(nextActivity);

                FirebaseAnalytics.logEvent("ForgottenLinkClicked", null);
            }
        });

        user_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(getActivity(), UserRegistActivity.class);
                startActivityForResult(nextActivity, USER_REGIST);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == USER_REGIST && resultCode == Activity.RESULT_OK){
            MainActivity activity = (MainActivity) getActivity();
            activity.notifyFragmentChange();
        }
    }

}