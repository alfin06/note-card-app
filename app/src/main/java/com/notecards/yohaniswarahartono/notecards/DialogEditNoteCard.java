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
 * Created by Yohan Hartono on 12/6/2015.
 */
public class DialogEditNoteCard extends DialogFragment
{
    // Constant Variables
    private static final String SEND_NOTECARD_ID = "NoteCardID";
    private static final String DIALOG_DATE = "dialogDatePicker";
    private static final int    REQUEST_DATE= -1 ;

    // Layout
    private EditText topic_edit;
    private EditText front_edit;
    private EditText back_edit;
    private Button date_button;


    // Member Variables
    private NoteCard      current_notecard;
    private NoteSingleton singleton = NoteSingleton.get();

    private UUID          notecard_Id;
    private Date          currentDate;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {

        notecard_Id      = (UUID) getArguments().getSerializable(SEND_NOTECARD_ID);
        current_notecard = singleton.getParticularNoteCard(notecard_Id);

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.add_notecard_dialog, null);


        topic_edit  = (EditText) v.findViewById(R.id.add_note_card_edit);
        front_edit  = (EditText) v.findViewById(R.id.add_note_card_edit_front);
        back_edit   = (EditText) v.findViewById(R.id.add_note_card_edit_back);
        date_button = (Button)   v.findViewById(R.id.add_note_card_date);

         topic_edit.setText(current_notecard.getNoteCardTitle());
         front_edit.setText(current_notecard.getFrontSide());
          back_edit.setText(current_notecard.getBackSide());
        date_button.setText(current_notecard.getDate().toString());

        currentDate = current_notecard.getDate();

        date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager   = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(currentDate);
                dialog.setTargetFragment(DialogEditNoteCard.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }

        });

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.edit_note_card)
                .setPositiveButton(R.string.action_edit,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                current_notecard.setNoteCardTitle(topic_edit.getText().toString());
                                current_notecard.setFrontSide(front_edit.getText().toString());
                                current_notecard.setBackSide(back_edit.getText().toString());
                                current_notecard.setDate(currentDate);

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

    public static DialogEditNoteCard newInstance(UUID notecard_Id){
        Bundle args = new Bundle();
        args.putSerializable(SEND_NOTECARD_ID, notecard_Id);

        DialogEditNoteCard dialog = new DialogEditNoteCard();
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




