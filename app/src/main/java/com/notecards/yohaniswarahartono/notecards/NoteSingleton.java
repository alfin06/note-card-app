package com.notecards.yohaniswarahartono.notecards;

import android.content.Context;
import android.location.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Alfin Rahardja on 11/21/2015.
 */
public class NoteSingleton {
    private static NoteSingleton sNoteSingleton;
    private List<Subject> mSubjects;
    private List<NoteCard> mNoteCards;
    private Context myContext;

    public static NoteSingleton get() {
        if (sNoteSingleton == null) {
            sNoteSingleton = new NoteSingleton();
        }
        return sNoteSingleton;
    }

    private NoteSingleton() {
        mSubjects = new ArrayList<>();
        mNoteCards = new ArrayList<>();
        NoteCard notecard = new NoteCard();
        Subject subject = new Subject();
        subject.addNoteCard(notecard);
        mSubjects.add(subject);
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

    public List<Subject> getSubjects() {
        return mSubjects;
    }

    public List<NoteCard> getNoteCards() {return mSubjects.get(0).getNoteCards();}

    // Get a particular NoteCard
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
