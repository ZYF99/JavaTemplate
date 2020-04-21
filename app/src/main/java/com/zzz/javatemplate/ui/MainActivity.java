package com.zzz.javatemplate.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import com.zzz.javatemplate.R;
import com.zzz.javatemplate.ui.guide.GuideViewPagerAdapter;
import com.zzz.javatemplate.ui.guide.PagerFragment;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;

    List<Fragment> fragmentList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.vp_guide);
        fragmentList.add(new PagerFragment(this,0,false));
        fragmentList.add(new PagerFragment(this,1,false));
        fragmentList.add(new PagerFragment(this,2,true));
        viewPager.setAdapter(new GuideViewPagerAdapter(getSupportFragmentManager(),fragmentList));
        viewPager.setCurrentItem(0);
    }
}
