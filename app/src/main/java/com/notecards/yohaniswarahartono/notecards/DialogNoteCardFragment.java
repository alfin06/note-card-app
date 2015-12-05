package com.notecards.yohaniswarahartono.notecards;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by Alfin Rahardja on 12/4/2015.
 */
public class DialogNoteCardFragment extends DialogFragment {
    // Constant Variables
    private static final String ARG_SUBJECT = "subject";

    // Member Variables
    private EditText name;
    private NoteCard newNoteCard;
    private Subject mSubject;
    private UUID     mSubjectId;
    private NoteSingleton singleton = NoteSingleton.get();

    public static DialogNoteCardFragment newInstance(UUID subjectId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_SUBJECT, subjectId);

        DialogNoteCardFragment dialog = new DialogNoteCardFragment();
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.add_subject_dialog, null);

        mSubjectId = (UUID) getArguments().getSerializable(ARG_SUBJECT);
        mSubject = singleton.getSubject(mSubjectId);

        name = (EditText) v.findViewById(R.id.add_subject_edit_name);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.add_note_card)
                .setPositiveButton(R.string.action_add,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                String title = name.getText().toString();
                                newNoteCard = new NoteCard();
                                newNoteCard.setNoteCardTitle(title);
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

}



