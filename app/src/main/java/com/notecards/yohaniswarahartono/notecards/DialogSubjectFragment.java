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

/**
 * Created by Yohan Hartono on 12/1/2015.
 */
public class DialogSubjectFragment extends DialogFragment
{

    private Subject newSubject;
    private EditText name;
    private NoteSingleton singleton = NoteSingleton.get();


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        View v = LayoutInflater.from(getActivity())
                    .inflate(R.layout.add_subject_dialog, null);


        name = (EditText) v.findViewById(R.id.add_subject_edit_name);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.add_subject)
                .setPositiveButton(R.string.action_add,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                String title = name.getText().toString();
                                newSubject = new Subject();
                                newSubject.setTitle(title);
                                newSubject.setTotalNoteCard(0);
                                singleton.addSubject(newSubject);
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


