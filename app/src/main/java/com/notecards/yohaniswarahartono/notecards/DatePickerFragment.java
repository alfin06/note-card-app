package com.notecards.yohaniswarahartono.notecards;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by Alfin Rahardja on 12/5/2015.
 */
public class DatePickerFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        return new AlertDialog.Builder(getActivity()).setTitle(R.string.date_picker_label)
                    .setPositiveButton(android.R.string.ok, null).create();
    }
}
