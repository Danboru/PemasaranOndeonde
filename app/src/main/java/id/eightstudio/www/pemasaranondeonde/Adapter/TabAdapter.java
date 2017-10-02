package id.eightstudio.www.pemasaranondeonde.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import id.eightstudio.www.pemasaranondeonde.Fragment.TabDua;
import id.eightstudio.www.pemasaranondeonde.Fragment.TabSatu;
import id.eightstudio.www.pemasaranondeonde.Fragment.TabTiga;

/**
 * Created by danbo on 10/2/17.
 */

public class TabAdapter extends FragmentPagerAdapter {

    //4
    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    //2
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0 : {
                return new TabSatu();
            }
            case 1 : {
                return new TabDua();
            }
            default : {
                return new TabTiga();
            }
        }
    }

    //1
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0: {
                return "Input Data";
            }
            case 1: {
                return "List Data";
            }
            default: {
                return "Hasil";
            }
        }

    }
}
