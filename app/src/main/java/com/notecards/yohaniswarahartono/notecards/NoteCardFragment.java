package com.notecards.yohaniswarahartono.notecards;

import android.content.Intent;
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

/**********************************************************************/
/*                          Notecard Fragment                         */
/**********************************************************************/
public class NoteCardFragment extends Fragment {

    // Constant Variables
    private static final String ARG_NOTECARD_ID = "NotecardID";     // Argument to get notecard ID
    private static final String ARG_SUBJECT_ID  = "SubjectID";      // Argument to get subject ID
    private static final String DIALOG_DATE     = "DialogDate";     // Tag for add dialog date
    private static final String DIALOG          = "DialogNotecard"; // Tag for add subject dialog
    private static final int    REQUEST_CODE    = -1;               // Request Code for receive notification

    // Member Variables
    private NoteCard mNoteCard;     // Notecard class
    private Subject  mSubject;      // Subject class
    private TextView mQuestion;     // Topic of the notecard
    private TextView mDate;         // Date of notecard created
    private TextView mSubjectName;  // The subject name
    private Button   mFlip;         // Flip to back side of notecard button

    public static NoteCardFragment newInstance(UUID notecardId, UUID subjectId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_NOTECARD_ID, notecardId);
        args.putSerializable(ARG_SUBJECT_ID, subjectId);
        NoteCardFragment fragment = new NoteCardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /***************************************************************************/
    /*                    Create the layout for Notecard                       */
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

        UUID subjectId = (UUID)getArguments().getSerializable(ARG_SUBJECT_ID);
        UUID notecardId = (UUID)getArguments().getSerializable(ARG_NOTECARD_ID);

        mSubject = NoteSingleton.get().getSubject(subjectId);
        mNoteCard = NoteSingleton.get().getParticularNoteCard(notecardId);

        mSubjectName = (TextView)v.findViewById(R.id.subject);
        mSubjectName.setText(mSubject.getTitle());

        mQuestion = (TextView)v.findViewById(R.id.question);
        mQuestion.setText(mNoteCard.getFrontSide());

        mDate = (TextView) v.findViewById(R.id.notecard_date);
        mDate.setText(mNoteCard.getDate().toString());

        mFlip = (Button) v.findViewById(R.id.flip);
        mFlip.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent moveLayout = BackSideNotecard.newIntent(getActivity(),
                        mSubject.getSubjectId(), mNoteCard.getNoteCardId());
                startActivity(moveLayout);
            }
        });


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
             /*   DialogAddNoteCard dialog  = DialogAddNoteCard.newInstance(mSubjectId);
                dialog.setTargetFragment(this, REQUEST_CODE);
                dialog.show(manager, DIALOG); */
                return true;

            default:
                return true;
        }
    }
}
