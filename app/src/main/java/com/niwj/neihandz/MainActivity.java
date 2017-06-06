package com.niwj.neihandz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.niwj.fragment.CheckFragment;
import com.niwj.fragment.DiscoveryFragment;
import com.niwj.fragment.HomeFragment;
import com.niwj.fragment.MessageFragment;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    HomeFragment homeFragment;
    DiscoveryFragment discoveryFragment;
    CheckFragment checkFragment;
    MessageFragment messageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup= (RadioGroup) this.findViewById(R.id.rg_main);
//        radioGroup.setOnClickListener(new MyClickListener());
        radioGroup.setOnCheckedChangeListener(new MyClickListener());
//默认首页
        homeFragment=new HomeFragment();
        this.getSupportFragmentManager().beginTransaction().add(R.id.main_content,homeFragment).commit();
    }


    //radiogroup的点击事件
    class MyClickListener implements RadioGroup.OnCheckedChangeListener{


        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
//            FragmentManager fragmentManager=this.get
//            FragmentTransaction fragmentTransaction

            android.support.v4.app.FragmentManager fragmentManager= MainActivity.this.getSupportFragmentManager();
            //一个事务只能提交一次，不可重复使用
            android.support.v4.app.FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
//            fragmentTransaction.add();
//            fragmentTransaction.remove();
//            fragmentTransaction.show();
//            fragmentTransaction.hide();
//            fragmentTransaction.replace();
//隐藏所有的碎片
            if (homeFragment!=null){
                fragmentTransaction.hide(homeFragment);
            }

            if (discoveryFragment!=null){
                fragmentTransaction.hide(discoveryFragment);
            }
            if (checkFragment!=null){
                fragmentTransaction.hide(checkFragment);
            }

            if (messageFragment!=null){
                fragmentTransaction.hide(messageFragment);
            }


            switch (checkedId){

                case R.id.home:
                    if (homeFragment==null){
                        homeFragment=new HomeFragment();
                        fragmentTransaction.add(R.id.main_content,homeFragment);
                    }else {
                        fragmentTransaction.show(homeFragment);
                    }
                    break;

                case R.id.discovery:

                    if (discoveryFragment==null){
                        discoveryFragment=new DiscoveryFragment();
                        fragmentTransaction.add(R.id.main_content,discoveryFragment);
                    }else {
                        fragmentTransaction.show(discoveryFragment);
                    }
                    break;

                case R.id.check:
                    if (checkFragment==null){
                        checkFragment=new CheckFragment();
                        fragmentTransaction.add(R.id.main_content,checkFragment);
                    }else {
                        fragmentTransaction.show(checkFragment);
                    }
                    break;

                case R.id.message:
                    if (messageFragment==null){
                        messageFragment=new MessageFragment();
                        fragmentTransaction.add(R.id.main_content,messageFragment);
                    }else {
                        fragmentTransaction.show(messageFragment);
                    }
                    break;
            }


            fragmentTransaction.commit();



        }
    }
}