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
    private String mOtherSide;  // Other side of notecard

    // Constructor
    public NoteCard() {
        mNotecardId    = UUID.randomUUID();
        mDate          = new Date();
    }

    /**********************************************************************/
    /*                         Getter Functions                           */
    /**********************************************************************/
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
