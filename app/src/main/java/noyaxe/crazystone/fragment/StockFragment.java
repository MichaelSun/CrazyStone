package noyaxe.crazystone.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import noyaxe.crazystone.R;
import noyaxe.crazystone.adapter.BaseFragmentPagerAdapter;

import java.util.ArrayList;

public class StockFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    @InjectView(R.id.tablayout)
    TabLayout mTablayout;

    @InjectView(R.id.vp_main)
    ViewPager mViewPager;

    @InjectView(R.id.search)
    ImageView mSearchImageView;

    private Fragment mMarketFragment;
    private Fragment mInfoFragment;
    private Fragment mExploreFragment;

    private BaseFragmentPagerAdapter mFragmentPagerAdapter;

    private OnFragmentInteractionListener mListener;

    public static StockFragment newInstance(String param1, String param2) {
        StockFragment fragment = new StockFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public StockFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_stock, container, false);
        ButterKnife.inject(this, ret);

        initUI();

        return ret;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                                             + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_main, menu);
//    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    private void initUI() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        mMarketFragment = new MarketFragment();
        mInfoFragment = new InfoFragment();
        mExploreFragment = new ExploreFragment();

        fragments.add(mMarketFragment);
        fragments.add(mInfoFragment);
        fragments.add(mExploreFragment);

        mFragmentPagerAdapter = new BaseFragmentPagerAdapter(getActivity().getApplicationContext(), getActivity().getSupportFragmentManager(), fragments);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mTablayout.setupWithViewPager(mViewPager);
    }

    @OnClick(R.id.search)
    public void search() {
        Toast.makeText(getActivity(), android.R.string.search_go, Toast.LENGTH_LONG).show();
    }

}
