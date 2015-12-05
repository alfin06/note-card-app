package com.notecards.yohaniswarahartono.notecards;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
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

    // Member Variables
    private NoteCard mNoteCard;     // Subject class
    private EditText mTopic;        // Topic of the notecard
    private Button   mDate;         // Date notecard created

    public static NoteCardFragment newInstance(UUID notecardId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_NOTECARD_ID, notecardId);

        NoteCardFragment fragment = new NoteCardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_notecard, container, false);

        UUID notecardId = (UUID)getArguments().getSerializable(ARG_NOTECARD_ID);
        mNoteCard = NoteSingleton.get().getParticularNoteCard(notecardId);
        Log.d("AA", notecardId.toString());
        Log.d("AB", mNoteCard.getNoteCardId().toString());

        mTopic = (EditText)v.findViewById(R.id.notecard_topic);

        mDate = (Button)v.findViewById(R.id.notecard_date);
        mDate.setText(mNoteCard.getDate().toString());
        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = new DatePickerFragment();
            }
        });

        return v;
    }
}
