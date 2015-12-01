package com.notecards.yohaniswarahartono.notecards;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Alfin Rahardja on 11/30/2015.
 */
public class NoteCard {

    // Member variables
    private String mOtherSide;     // Other side array
    private UUID mNoteCardId;    // Unique NoteCard Id
    private String mNoteCardTitle; // NoteCard title
    private Date mDate;         // Notecard Date


    // Constructor
    public NoteCard() {
        mNoteCardId = UUID.randomUUID();
        mDate = new Date();
    }

    /**********************************************************************/
    /*                         Getter Functions                           */
    /**********************************************************************/
    public Date getDate() {
        return mDate;
    }

    public UUID getNoteCardId() {
        return mNoteCardId;
    }

    public String getNoteCardTitle() {
        return mNoteCardTitle;
    }

    public String getOtherSide() {
        return mOtherSide;
    }

    /**********************************************************************/
    /*                         Setter Functions                           */
    /**********************************************************************/
    public void setNoteCardId(UUID noteCardId) {
        mNoteCardId = noteCardId;
    }

    public void setNoteCardTitle(String noteCardTitle) {
        mNoteCardTitle = noteCardTitle;
    }

    public void setOtherSide(String otherSide) {
        mOtherSide = otherSide;
    }
}