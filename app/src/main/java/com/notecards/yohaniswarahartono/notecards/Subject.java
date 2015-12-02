package com.notecards.yohaniswarahartono.notecards;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Alfin Rahardja on 11/21/2015.
 */
public class Subject {

    private UUID   mSubjectId;    // Notecard ID
    private String mTitle;        // Notecard Title
    private int    mTotalNoteCard; // Total notecard
    private List<NoteCard> mNoteCards;

    // Constructor
    public Subject() {
        mSubjectId = UUID.randomUUID();
        mNoteCards = new ArrayList<>();
    }

    // Add NoteCards to subject
    public void addNoteCard(NoteCard notecard) {mNoteCards.add(notecard);}

    /**********************************************************************/
    /*                         Getter Functions                           */
    /**********************************************************************/
    public UUID getSubjectId() {
        return mSubjectId;
    }

    public String getTitle() {
        return mTitle;
    }

    public List<NoteCard> getNoteCards() {return mNoteCards;}

    public int getTotalNoteCard() {return mTotalNoteCard;}

    public void setTotalNoteCard(int TotalNoteCard)
    {
        mTotalNoteCard = TotalNoteCard;
    }

    /**********************************************************************/
    /*                         Setter Functions                           */
    /**********************************************************************/
    public void setTitle(String title) {
        mTitle = title;
    }
}
