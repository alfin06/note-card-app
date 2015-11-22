package com.notecards.yohaniswarahartono.notecards;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Alfin Rahardja on 11/21/2015.
 */
public class NoteCard {

    private UUID   mNotecardId; // Notecard ID
    private String mTitle;      // Notecard Title
    private Date   mDate;       // Notecard Date
    private List<OtherSideNote> mOtherSideNote;

    // Constructor
    public NoteCard() {
        mNotecardId    = UUID.randomUUID();
        mDate          = new Date();
        mOtherSideNote = new ArrayList<>();
    }

    // Add verse to chapter
    public void addOtherSideNote(OtherSideNote otherSide) {
        mOtherSideNote.add(otherSide);
    }

    /**********************************************************************/
    /*                         Getter Functions                           */
    /**********************************************************************/
    public List<OtherSideNote> getOtherSideNote() {
        return mOtherSideNote;
    }

    public UUID getNotecardId() {
        return mNotecardId;
    }

    public Date getDate() {
        return mDate;
    }

    public String getTitle() {
        return mTitle;
    }

    /**********************************************************************/
    /*                         Setter Functions                           */
    /**********************************************************************/
    public void setTitle(String title) {
        mTitle = title;
    }
}
