package com.niwj.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.niwj.adpter.HomePagerAdapter;
import com.niwj.neihandz.R;


public class HomeFragment extends Fragment {
    ViewPager viewPager;
    RadioGroup radioGroup;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.fragment_home,null);
        viewPager= (ViewPager) view.findViewById(R.id.vp_home);
        viewPager.setAdapter(new HomePagerAdapter(HomeFragment.this.getChildFragmentManager()));
        viewPager.addOnPageChangeListener(new MyPagerListener());

        radioGroup= (RadioGroup) view.findViewById(R.id.rg_home);
        radioGroup.setOnCheckedChangeListener(new MyHomeRaidoListener());

        return view;
    }


    class MyPagerListener implements ViewPager.OnPageChangeListener{

        //滑动中。。。
        @Override
        public void onPageScrolled(int i, float v, int i1) {
            System.out.println(i+"     "+v+"     "+i1);
            //setspan
            //PorterDuff及Xfermode
        }
        //滑动后
        @Override
        public void onPageSelected(int i) {
            ((RadioButton) radioGroup.getChildAt(i)).setChecked(true);
        }
        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }


    class MyHomeRaidoListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.online:
                    viewPager.setCurrentItem(0,false);
                    break;
                case R.id.video:
                    viewPager.setCurrentItem(2,false);
                    break;
                case R.id.image:
                    viewPager.setCurrentItem(4,false);
                    break;
                case R.id.joke:
                    viewPager.setCurrentItem(5,false);
                    break;
                case R.id.show:
                    viewPager.setCurrentItem(3,false);
                    break;
                case R.id.recomment:
                    viewPager.setCurrentItem(1,false);
                    break;
            }
        }
    }
}
