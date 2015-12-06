package com.notecards.yohaniswarahartono.notecards;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**********************************************************************/
/*                             Class Notecard                         */
/**********************************************************************/
public class NoteCard {
    // Member variables
    private String mBackSide;      // Back side of the notecard
    private UUID   mNoteCardId;    // Unique Notecard Id
    private String mNoteCardTitle; // NoteCard title
    private Date   mDate;          // The date notecard created
    private String mFrontSide;     // Front side of the notecard

    // Constructor
    public NoteCard() {
        mNoteCardId = UUID.randomUUID();
        mDate = new Date();
        mBackSide = "";
        mFrontSide = "";
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

    public String getBackSide() {
        return mBackSide;
    }

    public String getFrontSide() {
        return mFrontSide;
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

    public void setBackSide(String backSide) {
        mBackSide = backSide;
    }

    public void setFrontSide(String frontSide) {
        mFrontSide = frontSide;
    }

    public void setDate(Date date) {
        mDate = date;
    }
}