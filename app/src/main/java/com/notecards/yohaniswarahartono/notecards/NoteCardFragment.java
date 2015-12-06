package com.notecards.yohaniswarahartono.notecards;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Alfin Rahardja on 12/5/2015.
 */
public class NoteCardFragment extends Fragment {

    // Constant Variables
    private static final String ARG_NOTECARD_ID = "NotecardID";
    private static final String DIALOG_DATE     = "DialogDate";
    private static final String DIALOG          = "DialogNotecard"; // Tag for add subject dialog
    private static final int    REQUEST_CODE    = -1;           // Request Code for receive notification

    // Member Variables
    private NoteCard mNoteCard;     // Subject class
    private TextView mQuestion;     // Topic of the notecard
    private TextView mDate;         // Date of notecard created
    private Button   mFlip;         // Date notecard created

    public static NoteCardFragment newInstance(UUID notecardId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_NOTECARD_ID, notecardId);

        NoteCardFragment fragment = new NoteCardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /***************************************************************************/
    /*                    Create the layout for Notecard                      */
    /***************************************************************************/
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_notecard, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_notecard, container, false);

        UUID notecardId = (UUID)getArguments().getSerializable(ARG_NOTECARD_ID);
        mNoteCard = NoteSingleton.get().getParticularNoteCard(notecardId);

        mQuestion = (TextView)v.findViewById(R.id.question);
        mQuestion.setText(mNoteCard.getFrontSide());

        mDate = (TextView) v.findViewById(R.id.notecard_date);
        mDate.setText("Date: " + mNoteCard.getDate().toString());

        mFlip = (Button) v.findViewById(R.id.flip);


        return v;
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
                FragmentManager        manager = getActivity().getSupportFragmentManager();
             /*   DialogNoteCardFragment dialog  = DialogNoteCardFragment.newInstance(mSubjectId);
                dialog.setTargetFragment(this, REQUEST_CODE);
                dialog.show(manager, DIALOG); */
                return true;

            default:
                return true;
        }
    }
}
