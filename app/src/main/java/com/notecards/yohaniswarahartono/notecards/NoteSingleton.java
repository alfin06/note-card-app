package com.notecards.yohaniswarahartono.notecards;

import android.content.Context;
import android.location.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**********************************************************************/
/*                           Notecard Singleton                       */
/**********************************************************************/
public class NoteSingleton {
    // Member variables
    private static NoteSingleton sNoteSingleton;    // The singleton
    private List<Subject> mSubjects;                // List of subjects
    private List<NoteCard> mNoteCards;              // List of notecards
    private Context myContext;                      // Context

    public static NoteSingleton get() {
        if (sNoteSingleton == null) {
            sNoteSingleton = new NoteSingleton();
        }
        return sNoteSingleton;
    }

    private NoteSingleton() {
        mSubjects  = new ArrayList<>();
        mNoteCards = new ArrayList<>();
    }

    // Get a particular subject
    public Subject getSubject(UUID id) {
        for (Subject subject : getSubjects()) {
            if(subject.getSubjectId().equals(id))
                return subject;
        }
        return null;
    }

    public void addSubject(Subject subject)
    {
        mSubjects.add(subject);
    }

    public void deleteSubject(Subject subject)
    {
        mSubjects.remove(subject);
    }

    public void deleteAllSubject()
    {
        mSubjects.clear();
    }


    public void deleteAllNoteCard(Subject subject)
    {
        subject.deleteNoteCard();
    }

    public List<Subject> getSubjects() {
        return mSubjects;
    }

    public List<NoteCard> getNoteCards() {return mSubjects.get(0).getNoteCards();}

    // Get a particular NoteCard
    public  NoteCard getParticularNoteCard(UUID notecardId) {
        for(Subject subject: mSubjects) {
            for(NoteCard notecard: subject.getNoteCards())
                if(notecard.getNoteCardId().equals(notecardId)){
                    return notecard;
            }
        }

        return null;
    }

    // Get a list of notecard
    public  List<NoteCard> getNoteCard(UUID subjectId) {
        for(Subject subject: mSubjects) {
            if(subject.getSubjectId().equals(subjectId)){
                return subject.getNoteCards();
            }
        }

        return null;
    }

    public String showSubject() {
        String title = "";

        for (Subject subject : getSubjects()) {
            title += subject.getTitle() + "\n";
        }
        return title;
    }

    public void addNoteCard(NoteCard noteCard, Subject subject) {
        subject.addNoteCard(noteCard);
    }

    public void deleteNoteCard(Subject n) {
        int location = 0;
        for (Subject subject : getSubjects()) {
            if(subject.getSubjectId().equals(n.getSubjectId()))
                mSubjects.remove(location);

            location++;
        }
    }
}
