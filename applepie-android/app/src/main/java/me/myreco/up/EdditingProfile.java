package me.myreco.up;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import me.myreco.up.LoginApi.Login;
import me.myreco.up.LoginApi.LoginResult;
import me.myreco.up.LoginApi.LoginApi;
import me.myreco.up.UserInfoApi.UserInfo;
import me.myreco.up.UserInfoApi.UserInfoApi;

import me.myreco.up.R;
import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.internal.ObjectConstructor;

/**
 * Created by hasuikeyuri on 2016/07/08.
 */
public class EdditingProfile extends  Fragment {

    private EditText mailEdit;
    private EditText passEdit;
    private EditText usernametext;
    private EditText introductiontext;
    private EditText areatext;
    private EditText birthdaytext;
    private EditText sextext;
    private EditText twittertext;
    private EditText facebooktext;
    private Button loginButton;
    private UserInfoApi userInfoApi;
    private TextView privacy_link, terms_link, forgotten_link, user_regist;
    private static final int USER_REGIST = 1;
    private com.google.firebase.analytics.FirebaseAnalytics FirebaseAnalytics;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.edittingprofile, container, false);

        FirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
        FirebaseAnalytics.logEvent("LoginFragment", null);


        mailEdit = (EditText) v.findViewById(R.id.mailEdit);
        passEdit = (EditText) v.findViewById(R.id.passEdit);
        usernametext = (EditText) v.findViewById(R.id.usernametext);
        introductiontext = (EditText) v.findViewById(R.id.introductiontext);
        areatext = (EditText) v.findViewById(R.id.areatext);
        birthdaytext = (EditText) v.findViewById(R.id.birthdaytext);
        sextext = (EditText) v.findViewById(R.id.sextext);
        twittertext = (EditText) v.findViewById(R.id.twittertext);
        facebooktext = (EditText) v.findViewById(R.id.facebooktext);

        View.OnTouchListener focus_listener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.requestFocusFromTouch();
                return false;
            }
        };

        mailEdit.setOnTouchListener(focus_listener);

        passEdit.setOnTouchListener(focus_listener);

        usernametext.setOnTouchListener(focus_listener);

        introductiontext.setOnTouchListener(focus_listener);

        areatext.setOnTouchListener(focus_listener);

        birthdaytext.setOnTouchListener(focus_listener);

        sextext.setOnTouchListener(focus_listener);

        twittertext.setOnTouchListener(focus_listener);

        facebooktext.setOnTouchListener(focus_listener);

    UserInfoApi api = ServiceGenerator.createService(UserInfoApi.class);

    Call<UserInfo> call = api.getUserInfo();

    call.enqueue(new Callback<UserInfo>()
    {
        @Override
        public void onResponse(Call<UserInfo> call, Response<UserInfo> UserInfoResponse){
        String email = UserInfoResponse.body().getEmail();
        Integer id = UserInfoResponse.body().getId();
        String username = UserInfoResponse.body().getUsername();
        String nickname = UserInfoResponse.body().getNickname();
        Object area = UserInfoResponse.body().getArea();
        String attribute = UserInfoResponse.body().getAttribute();
        String sex = UserInfoResponse.body().getSex();
        String bio = UserInfoResponse.body().getBio();
        Object birthday = UserInfoResponse.body().getBirthday();
        Object icon = UserInfoResponse.body().getIcon();


        new AlertDialog.Builder(getActivity())
                .show();
    }

        @Override
        public void onFailure(Call<UserInfo> call, Throwable t) {

        }


        public void onFailure(Call<UserInfo> call)
        {
            new AlertDialog.Builder(getActivity())
                    .setTitle("")
                    .setMessage("")
                    .setPositiveButton("OK", null)
                    .show();
        }
    });

     return v;
    }}

