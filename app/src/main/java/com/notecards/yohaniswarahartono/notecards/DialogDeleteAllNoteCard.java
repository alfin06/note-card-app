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

/**********************************************************************/
/*                          Delete all notecards                      */
/**********************************************************************/
public class DialogDeleteAllNoteCard extends DialogFragment
{
    // Member Variables
    private static final String SEND_NOTECARD_ID = "NoteCardID";  // Tag to send subject id
    private NoteSingleton singleton = NoteSingleton.get();
    private Subject currentSubject;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        UUID subjectId = (UUID) getArguments().getSerializable(SEND_NOTECARD_ID);
        currentSubject = singleton.getSubject(subjectId);

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.delete_dialog, null);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Delete All Note Cards")
                .setPositiveButton(R.string.action_ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                singleton.deleteAllNoteCard(currentSubject);
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