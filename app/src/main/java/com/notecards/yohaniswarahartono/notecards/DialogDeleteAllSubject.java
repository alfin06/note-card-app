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

/**
 * Created by Yohan Hartono on 12/4/2015.
 */
public class DialogDeleteAllSubject extends DialogFragment
{
    // Constant Variables
    private static final String SEND_SUBJECT_ID = "SubjectID";

    // Member Variables
    private Subject       currentSubject;
    private EditText name;
    private TextView title;
    private NoteSingleton singleton = NoteSingleton.get();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.add_subject_dialog, null);

        title = (TextView) v.findViewById(R.id.add_subject_text);
        name  = (EditText) v.findViewById(R.id.add_subject_edit_name);

        // Set the Dialog Title and Current Subject Name
        name.setVisibility(View.INVISIBLE);
        title.setText("Are you sure ?");

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Delete All Subjects")
                .setPositiveButton(R.string.action_ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                singleton.deleteAllSubject();
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


