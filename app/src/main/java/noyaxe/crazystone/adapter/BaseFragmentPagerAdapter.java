package noyaxe.crazystone.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import noyaxe.crazystone.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michael on 15/7/17.
 */
public class BaseFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private List<Fragment> mFragmentList = new ArrayList<>();

    public BaseFragmentPagerAdapter(Context context, FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        mContext = context;
        mFragmentList.clear();
        mFragmentList.addAll(fragmentList);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.market_title);
            case 1:
                return mContext.getString(R.string.info_title);
            case 2:
                return mContext.getString(R.string.explore_title);
            default:
                return "";
        }
    }
}
