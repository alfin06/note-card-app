package com.notecards.yohaniswarahartono.notecards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

/**
 * Created by Alfin Rahardja on 11/30/2015.
 */
public class NoteCardListFragment extends Fragment {
    // Constant variables
    public static final String EXTRA_SUBJECT_ID = "Subject Id";

    // Member variables
    private RecyclerView    mNoteCardRecyclerView; // Book recycler view
    private NoteCardAdapter  mAdapter;             // Adapter
    private UUID            mSubjectId;           // Unique subject Id
    private Subject         mSubject;             // Specific subject

    /***************************************************************************/
    /*                  Create the layout for book choices                     */
    /***************************************************************************/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notecard, container, false);

        mNoteCardRecyclerView = (RecyclerView) view.findViewById(R.id.notecard_recycler_view);
        mNoteCardRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mSubjectId = (UUID) getActivity().getIntent().getSerializableExtra(EXTRA_SUBJECT_ID);
        mSubject   = NoteSingleton.get().getSubject(mSubjectId);
        Log.d("TESTER", mSubjectId.toString());
        updateUserInterface();

        return view;
    }

    /***************************************************************************/
    /*             Keep track the interface if user make some changes          */
    /***************************************************************************/
    @Override
    public void onResume() {
        super.onResume();
        updateUserInterface();
    }

    /***************************************************************************/
    /*                           Update the user interface                     */
    /***************************************************************************/
    private void updateUserInterface() {
        NoteSingleton lab = NoteSingleton.get();
        List<NoteCard> notecard = lab.getNoteCard(mSubjectId);
        mSubject  = NoteSingleton.get().getSubject(mSubjectId);

        if (mAdapter == null) {
            mAdapter = new NoteCardAdapter(notecard, mSubject);
            mNoteCardRecyclerView.setAdapter(mAdapter);
        }
        else {
            mAdapter.notifyDataSetChanged();
        }
    }

    /***************************************************************************/
    /*  Create the holder for each book and set up an action if the book       */
    /*  is clicked                                                             */
    /***************************************************************************/
    private class NoteCardHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mNoteCardTitle;
        private TextView mDate;
        private NoteCard mNoteCard;       // Chapter class
        private Subject  mSubject;        // Book class

        public NoteCardHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mNoteCardTitle = (TextView)itemView.findViewById(R.id.note_card_title);
            mDate = (TextView)itemView.findViewById(R.id.date);
        }

        public void bindNoteCard(NoteCard notecard, int position, Subject subject) {
            mNoteCard = notecard;
            mSubject  = subject;
            mNoteCardTitle.setText("NoteCard" + Integer.toString(position + 1));
            mDate.setText(mNoteCard.getDate().toString());
        }

        @Override
        public void onClick(View v){

        }
    }

    /***************************************************************************/
    /*                      Create the adapter for each book                   */
    /***************************************************************************/
    private class NoteCardAdapter extends RecyclerView.Adapter<NoteCardHolder> {
        private List<NoteCard> mNoteCards; // Array for each book
        private Subject          mSubject;

        public NoteCardAdapter(List<NoteCard> notecards, Subject subject) {
            mNoteCards = notecards;
            mSubject     = subject;
        }

        @Override
        public NoteCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_notecard, parent, false);
            return new NoteCardHolder(view);
        }

        @Override
        public void onBindViewHolder(NoteCardHolder holder, int position) {
            Subject subject = mSubject;
            NoteCard notecard = mNoteCards.get(position);
            holder.bindNoteCard(notecard, position, subject);
        }

        @Override
        public int getItemCount() {
            return mNoteCards.size();
        }
    }
}

