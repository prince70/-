package com.niwj.adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.niwj.fragment.ImageFragment;
import com.niwj.fragment.JokeFragment;
import com.niwj.fragment.OnLineFragment;
import com.niwj.fragment.RecommentFragment;
import com.niwj.fragment.ShowFragment;
import com.niwj.fragment.VideoFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class HomePagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments=new ArrayList<>();

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
        OnLineFragment onLineFragment=new OnLineFragment();
        VideoFragment videoFragment=new VideoFragment();
        ImageFragment imageFragment=new ImageFragment();
        JokeFragment jokeFragment=new JokeFragment();
        ShowFragment showFragment=new ShowFragment();
        RecommentFragment recommentFragment=new RecommentFragment();

        fragments.add(onLineFragment);
        fragments.add(recommentFragment);
        fragments.add(videoFragment);
        fragments.add(showFragment);
        fragments.add(imageFragment);
        fragments.add(jokeFragment);

    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
