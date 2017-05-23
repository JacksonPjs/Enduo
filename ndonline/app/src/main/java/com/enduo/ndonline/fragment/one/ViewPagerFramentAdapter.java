package com.enduo.ndonline.fragment.one;

import java.util.List;

import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerFramentAdapter extends FragmentPagerAdapter{

	List<Fragment> fragments ;
	
	public ViewPagerFramentAdapter(FragmentManager fm ,List<Fragment> fragments) {
		super(fm);
		this.fragments = fragments;
	}

	@Override
	public Fragment getItem(int arg0) {
		
		return fragments.get(arg0);
	}

	@Override
	public int getCount() {
		
		return fragments.size();
	}

	
	

}
