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

/**********************************************************************/
/*                        Edit the subject name                       */
/**********************************************************************/
public class DialogEditSubject extends DialogFragment
{
    // Constant Variables
    private static final String SEND_SUBJECT_ID = "SubjectID"; // Tag to get the subject ID

    // Member Variables
    private Subject currentSubject; // Current subject
    private EditText name;          // Name of the subject
    private NoteSingleton singleton = NoteSingleton.get(); // Singleton

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        UUID subjectId = (UUID) getArguments().getSerializable(SEND_SUBJECT_ID);
        currentSubject = singleton.getSubject(subjectId);

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.add_subject_dialog, null);

        name  = (EditText) v.findViewById(R.id.add_subject_edit_name);

        // Set the Dialog Title and Current Subject Name
        name.setText(currentSubject.getTitle());

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.edit_name)
                .setPositiveButton(R.string.action_ok,
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
                .setNeutralButton(R.string.action_delete,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                singleton.deleteSubject(currentSubject);
                                notifyToTarget(Activity.RESULT_OK);
                            }
                        })
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
