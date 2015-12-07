package com.notecards.yohaniswarahartono.notecards;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**********************************************************************/
/*                              Class Subject                         */
/**********************************************************************/
public class Subject {
    // Member variables
    private UUID   mSubjectId;          // Subject ID
    private String mTitle;              // Subject Title
    private int    mTotalNoteCard;      // Total notecards in the subject
    private List<NoteCard> mNoteCards;  // List of notecards

    // Constructor
    public Subject() {
        mSubjectId = UUID.randomUUID();
        mNoteCards = new ArrayList<>();
        mTotalNoteCard = 0;
    }

    // Add NoteCards to subject
    public void addNoteCard(NoteCard notecard)
    {
        mNoteCards.add(notecard);
        mTotalNoteCard = mNoteCards.size();
    }

    // Delete NoteCards from subject
    public void deleteAllNoteCard()
    {
        mNoteCards.removeAll(mNoteCards);
        mTotalNoteCard = 0;
    }


    // Delete NoteCards from subject
    public void deleteNoteCard(NoteCard notecard)
    {
        mNoteCards.remove(notecard);
        mTotalNoteCard = mNoteCards.size();
    }

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

    public int getTotalNoteCard() {return mNoteCards.size();}

    /**********************************************************************/
    /*                         Setter Functions                           */
    /**********************************************************************/
    public void setTitle(String title) {
        mTitle = title;
    }
}
