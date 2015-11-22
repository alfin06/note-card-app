package com.notecards.yohaniswarahartono.notecards;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alfin Rahardja on 11/21/2015.
 */
public class NoteSingleton {
    private static NoteSingleton sNoteSingleton;
    private List<NoteCard> mNotecards;

    public static NoteSingleton get() {
        if (sNoteSingleton == null) {
            sNoteSingleton = new NoteSingleton();
        }
        return sNoteSingleton;
    }

    private NoteSingleton() {
        mNotecards = new ArrayList<>();

        // Create note
        NoteCard firstNote = new NoteCard();
        //    firstNote.addOtherSideNote(R.string.);   // Add string for other side note
    }
}
