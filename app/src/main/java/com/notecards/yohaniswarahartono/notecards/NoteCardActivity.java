package com.notecards.yohaniswarahartono.notecards;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by Alfin Rahardja on 12/5/2015.
 */
public class NoteCardActivity extends SingleFragmentActivity {
    public static final String EXTRA_SUBJECT_ID = "SubjectId";
    public static final String EXTRA_NOTECARD_ID = "NotecardId";

    public static Intent newIntent(Context packageContext, UUID notecardId){
        Intent intent = new Intent (packageContext, NoteCardActivity.class);
        intent.putExtra(EXTRA_NOTECARD_ID, notecardId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new NoteCardFragment();
    }
}
