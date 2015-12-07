package com.notecards.yohaniswarahartono.notecards;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Alfin Rahardja on 12/4/2015.
 */
public class DialogAddNoteCard extends DialogFragment {
    // Constant Variables
    private static final String ARG_SUBJECT = "subject";
    private static final String DIALOG_DATE = "dialogDatePicker";
    private static final int    REQUEST_DATE= -1 ;

    // Layout
    private EditText topic_edit;
    private EditText front_edit;
    private EditText back_edit;
    private Button   date_button;


    // Member Variables
    private NoteCard newNoteCard;
    private Subject  mSubject;
    private UUID     mSubjectId;
    private NoteSingleton singleton = NoteSingleton.get();
    private Date     currentDate;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.add_notecard_dialog, null);

        mSubjectId = (UUID) getArguments().getSerializable(ARG_SUBJECT);
        mSubject = singleton.getSubject(mSubjectId);

        topic_edit  = (EditText) v.findViewById(R.id.add_note_card_edit);
        front_edit  = (EditText) v.findViewById(R.id.add_note_card_edit_front);
        back_edit   = (EditText) v.findViewById(R.id.add_note_card_edit_back);
        date_button = (Button)   v.findViewById(R.id.add_note_card_date);

        currentDate = new Date();

        date_button.setText(currentDate.toString());
        date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(currentDate);
                dialog.setTargetFragment(DialogAddNoteCard.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }

        });

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.add_note_card)
                .setPositiveButton(R.string.action_add,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                newNoteCard = new NoteCard();
                                newNoteCard.setNoteCardTitle(topic_edit.getText().toString());
                                newNoteCard.setFrontSide(front_edit.getText().toString());
                                newNoteCard.setBackSide(back_edit.getText().toString());
                                newNoteCard.setDate(currentDate);

                                singleton.addNoteCard(newNoteCard, mSubject);
                                notifyToTarget(Activity.RESULT_OK);
                            }
                        })
                .setNegativeButton(R.string.cancel_dialog, null)
                .create();
    }

    // Notify the fragment to refresh after dialog dismissed
    private void notifyToTarget(int code) {
        Fragment targetFragment = getTargetFragment();
        if (targetFragment != null) {
            targetFragment.onActivityResult(getTargetRequestCode(), code, null);
        }
    }

    public static DialogAddNoteCard newInstance(UUID subjectId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_SUBJECT, subjectId);

        DialogAddNoteCard dialog = new DialogAddNoteCard();
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode == Activity.RESULT_OK)
        {
            if(requestCode == REQUEST_DATE)
            {
                Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
                currentDate = date;
                date_button.setText(date.toString());
            }
        }
    }

}



