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
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by Yohan Hartono on 12/2/2015.
 */
public class DialogEditSubjectName extends DialogFragment
{

    private static final String SEND_SUBJECT_ID = "SubjectID";
    private Subject currentSubject;
    private EditText name;
    private TextView title;
    private NoteSingleton singleton = NoteSingleton.get();


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        UUID subjectId = (UUID) getArguments().getSerializable(SEND_SUBJECT_ID);

        currentSubject = singleton.getSubject(subjectId);

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.add_subject_dialog, null);

        title = (TextView) v.findViewById(R.id.add_subject_text);
        name  = (EditText) v.findViewById(R.id.add_subject_edit_name);

        // Set the Dialog Title and Current Subject Name
        name.setText(currentSubject.getTitle());

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Edit Text")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                String title = name.getText().toString();
                                currentSubject.setTitle(title);
                                notifyToTarget(Activity.RESULT_OK);
                            }
                        })
                .setNegativeButton(R.string.cancel_dialog, null)
                .create();
    }

    // Notify the fragment to refresh after dialog
    private void notifyToTarget(int code) {
        Fragment targetFragment = getTargetFragment();
        if (targetFragment != null) {
            targetFragment.onActivityResult(getTargetRequestCode(), code, null);
        }
    }

}
