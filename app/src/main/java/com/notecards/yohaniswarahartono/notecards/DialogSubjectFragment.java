package com.notecards.yohaniswarahartono.notecards;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
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


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        View v = LayoutInflater.from(getActivity())
                    .inflate(R.layout.add_subject_dialog, null);


        name = (EditText) v.findViewById(R.id.add_subject_edit_name);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.add_subject)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                addSubject(name.toString());
                            }
                        })
                .create();
    }


    private void addSubject(String name)
    {
        Subject newSubject = new Subject();
        newSubject.setTitle(name);
    }

}
