package noyaxe.crazystone.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import noyaxe.crazystone.R;
import noyaxe.crazystone.fragment.NoteFragment;
import noyaxe.crazystone.fragment.StockFragment;

public class MainActivity extends Activity implements StockFragment.OnFragmentInteractionListener, NoteFragment.OnFragmentInteractionListener {

    private static final String TAG_STOCK_FRAGMENT = "stock_fragment";
    private static final String TAG_NOTE_FRAGMENT = "note_fragment";

    private static final String KEY_CURR_SELECTED_TAB_ID = "key_curr_selected_tab_id";

    private int mCurrSelectedTabId;

    @InjectView(R.id.stock_button)
    Button mStockButton;

    @InjectView(R.id.note_button)
    Button mNoteButton;

    private Fragment mNoteFragment;

    private Fragment mStockFragment;

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initData(savedInstanceState);
    }

    @OnClick(R.id.stock_button)
    protected void stock_button() {
        switchToTab(R.id.stock_button);
    }

    @OnClick(R.id.note_button)
    protected void note_button() {
        switchToTab(R.id.note_button);
    }

    private void initData(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mCurrSelectedTabId = savedInstanceState.getInt(KEY_CURR_SELECTED_TAB_ID);
        } else {
            mCurrSelectedTabId = R.id.stock_button;
        }

        mFragmentManager = getFragmentManager();

        mStockFragment = mFragmentManager.findFragmentByTag(TAG_STOCK_FRAGMENT);
        if (mStockFragment == null) {
            mStockFragment = new StockFragment();
            Bundle args = new Bundle();
            mStockFragment.setArguments(args);

            FragmentTransaction trans = mFragmentManager.beginTransaction();
            trans.add(R.id.fragment_container, mStockFragment, TAG_STOCK_FRAGMENT);
            trans.commitAllowingStateLoss();
        }

        mNoteFragment = mFragmentManager.findFragmentByTag(TAG_NOTE_FRAGMENT);
        if (mNoteFragment == null) {
            mNoteFragment = new NoteFragment();

            FragmentTransaction trans = mFragmentManager.beginTransaction();
            trans.add(R.id.fragment_container, mNoteFragment, TAG_NOTE_FRAGMENT);
            trans.commitAllowingStateLoss();
        }
    }

    private void switchToTab(int tabId) {
        FragmentTransaction tans = mFragmentManager.beginTransaction();
        switch (tabId) {
            case R.id.stock_button:
                mCurrSelectedTabId = R.id.stock_button;

                tans.show(mStockFragment);
                tans.hide(mNoteFragment);
                break;

            case R.id.note_button:
                mCurrSelectedTabId = R.id.note_button;

                tans.hide(mStockFragment);
                tans.show(mNoteFragment);
                break;

        }
        tans.commitAllowingStateLoss();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
