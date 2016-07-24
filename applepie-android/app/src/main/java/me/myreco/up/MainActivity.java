package me.myreco.up;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.facebook.stetho.Stetho;

import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentManager;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //通信確認用
//        Stetho.initialize(
//                Stetho.newInitializerBuilder(this)
//                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
//                        .build());

        tabLayout = (TabLayout)findViewById(R.id.tabs);
        viewPager = (ViewPager)findViewById(R.id.pager);

        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(myFragmentPagerAdapter);
        viewPager.addOnPageChangeListener(this);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FE6C01"));
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.tab_home_selector);
        tabLayout.getTabAt(1).setIcon(R.drawable.tab_camera_selector);
        tabLayout.getTabAt(2).setIcon(R.drawable.tab_user_selector);
        tabLayout.getTabAt(3).setIcon(R.drawable.tab_setting_selector);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){

    }

    @Override
    public void onPageSelected(int position){

    }

    @Override
    public void onPageScrollStateChanged(int state){

    }

    public void notifyFragmentChange() {
        myFragmentPagerAdapter.notifyDataSetChanged();
    }

}

class MyFragmentPagerAdapter extends FragmentStatePagerAdapter{

    private Context context;

    public MyFragmentPagerAdapter(FragmentManager fragmentManager, Context c) {
        super(fragmentManager);
        context = c;
    }

    @Override
    public Fragment getItem(int position) {

        SavePreference savePreference = new SavePreference();

        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                if(savePreference.load_aToken(context) == null){
                    return new LoginFragment();
                }else{
                    return new PostFragment();
                }
            case 2:
                if(savePreference.load_aToken(context) == null){
                    return new LoginFragment();
                }else {
                    return new UserFragment();
                }
            case 3:
                if(savePreference.load_aToken(context) == null){
                    return new LoginFragment();
                }else {
                    return new SettingFragment();
                }
        }
        return null;
    }
    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return null;
    }

    //notifyDataSetChangedでのfragment更新用
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
