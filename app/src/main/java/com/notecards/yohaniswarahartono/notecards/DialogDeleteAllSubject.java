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

import java.util.UUID;

/**
 * Created by Yohan Hartono on 12/4/2015.
 */
public class DialogDeleteAllSubject extends DialogFragment
{

    private static final String SEND_NOTECARD_ID = "NoteCardID";  // Tag to send subject id
    // Member Variables
    private NoteSingleton singleton = NoteSingleton.get();
    private Subject currentSubject;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.delete_dialog, null);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Delete All Subjects")
                .setPositiveButton(R.string.action_ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
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


