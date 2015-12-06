package com.notecards.yohaniswarahartono.notecards;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.UUID;

public class BackSideNotecard extends AppCompatActivity {
    // Constant Variables
    private static final String EXTRA_SUBJECT_ID = "SubjectID";
                                        // Tag to get Subject ID
    private static final String EXTRA_NOTECARD_ID = "NotecardID";
                                        // Tag to get Notecard ID

    // Member Variables
    private NoteCard mNoteCard;     // Notecard class
    private Subject  mSubject;      // Subject class
    private TextView mSubjectName;  // Subject name
    private TextView mDate;         // Date of notecard
    private TextView mAnswer;       // Answer
    private Button   mFlip;         // Flip button

    public static Intent newIntent(Context packageContext, UUID subjectId, UUID notecardId)
    {
        Bundle extras = new Bundle();
        Intent intent = new Intent(packageContext, BackSideNotecard.class);
        extras.putSerializable(EXTRA_NOTECARD_ID, notecardId);
        extras.putSerializable(EXTRA_SUBJECT_ID, subjectId);
        intent.putExtras(extras);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_side_notecard);

        UUID subjectId = (UUID) getIntent().getSerializableExtra(EXTRA_SUBJECT_ID);
        UUID notecardId = (UUID) getIntent().getSerializableExtra(EXTRA_NOTECARD_ID);

        mSubject = NoteSingleton.get().getSubject(subjectId);
        mNoteCard = NoteSingleton.get().getParticularNoteCard(notecardId);

        mSubjectName = (TextView)findViewById(R.id.subject);
        mSubjectName.setText(mSubject.getTitle());

        mDate = (TextView)findViewById(R.id.notecard_date);
        mDate.setText(mNoteCard.getDate().toString());

        mAnswer = (TextView)findViewById(R.id.answer);
        mAnswer.setText(mNoteCard.getBackSide());

    }

}
