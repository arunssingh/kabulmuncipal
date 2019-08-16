package com.rams.kabulmuncipal;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.Timer;

import Adapter.Tutorialpageradapter;
import Utils.Logger1;


public class Tutorial_Firstscreen extends BaseActivity {
	
	ViewPager viewPager;
	Tutorialpageradapter adapter;



	Timer timer;
	TextView tv_main;
	ArrayList<Integer> list=new ArrayList<Integer>();
	int pos=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tutorial);
		list=new ArrayList<Integer>();

		list.add(R.mipmap.driver_1);
		list.add(R.mipmap.driver_2);
		list.add(R.mipmap.driver_3);
		list.add(R.mipmap.driver_4);
	
	
		

		viewPager = (ViewPager) findViewById(R.id.pager);



		adapter = new Tutorialpageradapter(this,list,viewPager,false);
		// Binds the Adapter to the ViewPager
		viewPager.setAdapter(adapter);

		viewPager.setCurrentItem(0);
		Logger1.e("crnt", pos+"");
		TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
		tabLayout.setupWithViewPager(viewPager, true);



	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (timer!=null) {
			timer.cancel();

		}
	}

}
