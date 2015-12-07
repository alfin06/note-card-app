package com.notecards.yohaniswarahartono.notecards;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;
import java.util.UUID;

/**********************************************************************/
/*                        Notecard View Pager                         */
/**********************************************************************/
public class NotecardViewPager extends FragmentActivity {

    // Constant Variables
    private static final String EXTRA_SUBJECT_ID = "SubjectID";
                                        // Tag to get Subject ID
    private static final String EXTRA_NOTECARD_ID = "NotecardID";
                                        // Tag to get Notecard ID

    // Member Variables
    private ViewPager      mViewPager;  // The view pager
    private List<NoteCard> mNoteCards;  // List of notecards
    private Subject        mSubject;    // Subject class

    public static Intent newIntent(Context packageContext, UUID notecardId, UUID subjectId)
    {
        Bundle extras = new Bundle();
        Intent intent = new Intent(packageContext, NotecardViewPager.class);
        extras.putSerializable(EXTRA_NOTECARD_ID, notecardId);
        extras.putSerializable(EXTRA_SUBJECT_ID, subjectId);
        intent.putExtras(extras);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notecard_view_pager);

        UUID subjectId = (UUID) getIntent().getSerializableExtra(EXTRA_SUBJECT_ID);
        UUID notecardId = (UUID) getIntent().getSerializableExtra(EXTRA_NOTECARD_ID);

        mSubject = NoteSingleton.get().getSubject(subjectId);
        mViewPager = (ViewPager)findViewById(R.id.view_pager);

        mNoteCards = NoteSingleton.get().getNoteCard(subjectId);
        FragmentManager manager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(manager) {
            @Override
            public Fragment getItem(int position) {
                NoteCard notecard = mNoteCards.get(position);
                return NoteCardFragment.newInstance(notecard.getNoteCardId(), mSubject.getSubjectId());
            }

            @Override
            public int getCount() {
                return mNoteCards.size();
            }
        });

        for (int i = 0; i < mSubject.getTotalNoteCard(); i++)
        {
            if(mNoteCards.get(i).getNoteCardId().equals(notecardId))
            {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_notecard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id)
        {
            case R.id.action_edit:
            //    FragmentManager        manager = getActivity().getSupportFragmentManager();
             /*   DialogAddNoteCard dialog  = DialogAddNoteCard.newInstance(mSubjectId);
                dialog.setTargetFragment(this, REQUEST_CODE);
                dialog.show(manager, DIALOG); */
                return true;

            default:
                return true;
        }
    }
}
