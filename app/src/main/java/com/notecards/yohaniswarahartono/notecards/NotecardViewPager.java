package com.notecards.yohaniswarahartono.notecards;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.UUID;

public class NotecardViewPager extends FragmentActivity {

    // Constant Variables
    private static final String EXTRA_NOTECARD_ID = "NotecardID";

    // Member Variables
    private ViewPager mViewPager;
    private List<NoteCard> mNoteCards;

    public static Intent newIntent(Context packageContext, UUID notecardId)
    {
        Intent intent = new Intent(packageContext, NotecardViewPager.class);
        intent.putExtra(EXTRA_NOTECARD_ID, notecardId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notecard_view_pager);

        UUID notecardId = (UUID) getIntent().getSerializableExtra(EXTRA_NOTECARD_ID);

        mViewPager = (ViewPager)findViewById(R.id.view_pager);

        mNoteCards = NoteSingleton.get().getNoteCards();
        FragmentManager manager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(manager) {
            @Override
            public Fragment getItem(int position) {
                NoteCard notecard = mNoteCards.get(position);
                return NoteCardFragment.newInstance(notecard.getNoteCardId());
            }

            @Override
            public int getCount() {
                return mNoteCards.size();
            }
        });

        for (int i = 0; i < mNoteCards.size(); i++)
        {
            if(mNoteCards.get(i).getNoteCardId().equals(notecardId))
            {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
