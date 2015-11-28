package com.notecards.yohaniswarahartono.notecards;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Alfin Rahardja on 11/21/2015.
 */
public class NoteSingleton {
    private static NoteSingleton sNoteSingleton;
    private List<NoteCard> mNotecards;
    private Context myContext;

    public static NoteSingleton get(Context context) {
        if (sNoteSingleton == null) {
            sNoteSingleton = new NoteSingleton(context);
        }
        return sNoteSingleton;
    }

    private NoteSingleton(Context context) {
        myContext = context.getApplicationContext();
        mNotecards = new ArrayList<>();
    }

    public NoteCard getNoteCard(UUID id) {
        for (NoteCard notecard : getNoteCards()) {
            if(notecard.getNotecardId().equals(id))
                return notecard;
        }
        return null;
    }

    public List<NoteCard> getNoteCards() {
        return mNotecards;
    }

    public String showNoteCard() {
        String title = "";

        for (NoteCard notecard : getNoteCards()) {
            title += notecard.getTitle() + "\n";
        }
        return title;
    }

    public void addNoteCard(NoteCard notecard) {
        mNotecards.add(notecard);
    }

    public void deleteNoteCard(NoteCard n) {
        int location = 0;
        for (NoteCard notecard : getNoteCards()) {
            if(notecard.getNotecardId().equals(n.getNotecardId()))
                mNotecards.remove(location);

            location++;
        }
    }
}
