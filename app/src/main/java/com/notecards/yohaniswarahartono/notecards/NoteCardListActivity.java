package com.notecards.yohaniswarahartono.notecards;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by Alfin Rahardja on 11/3/2015.
 */
public class NoteCardListActivity extends SingleFragmentActivity {

    public static final String EXTRA_SUBJECT_ID = "Subject Id";

    public static Intent newIntent(Context packageContext, UUID subjectId){
        Intent intent = new Intent (packageContext, NoteCardListActivity.class);
        intent.putExtra(EXTRA_SUBJECT_ID, subjectId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new NoteCardListFragment();
    }
}