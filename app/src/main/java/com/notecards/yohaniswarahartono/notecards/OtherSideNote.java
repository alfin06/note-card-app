package com.notecards.yohaniswarahartono.notecards;

import java.util.UUID;

/**
 * Created by Alfin Rahardja on 11/21/2015.
 */
public class OtherSideNote {

    private UUID mOtherSideNoteId; // Notecard ID
    private int  mOtherSideId;     // Notecard Title

    public OtherSideNote(int sideId) {
        mOtherSideNoteId = UUID.randomUUID();
        mOtherSideId     = sideId;
    }

    /**********************************************************************/
    /*                         Getter Functions                           */
    /**********************************************************************/
    public UUID getOtherSideNoteId() {
        return mOtherSideNoteId;
    }
}
