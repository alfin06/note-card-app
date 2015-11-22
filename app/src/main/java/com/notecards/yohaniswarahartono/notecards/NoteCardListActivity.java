package com.notecards.yohaniswarahartono.notecards;


import android.support.v4.app.Fragment;

public class NoteCardListActivity extends SingleFragmentActivity
{
    @Override
    protected Fragment createFragment() {
        return new NoteCardListFragment();
    }

}
