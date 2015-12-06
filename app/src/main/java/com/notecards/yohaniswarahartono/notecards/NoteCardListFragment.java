package com.notecards.yohaniswarahartono.notecards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import java.util.UUID;

/**********************************************************************/
/*                          Notecard List Fragment                    */
/**********************************************************************/

public class NoteCardListFragment extends Fragment {
    // Constant variables
    public static final String  EXTRA_SUBJECT_ID  = "Subject Id"; // Extra to get subject ID
    private static final String ADD_DIALOG       = "AddNoteCard"; // Tag for add subject dialog
    private static final String DELETE_DIALOG   = "DeleteDialog"; // Tag for add subject dialog
    private static final String SEND_NOTECARD_ID = "NoteCardID";  // Tag to send subject id
    private static final int    REQUEST_CODE     = -1;            // Request Code for receive notification

    // Member variables
    private RecyclerView    mNoteCardRecyclerView; // Notecard recycler view
    private NoteCardAdapter mAdapter;              // Adapter
    private UUID            mSubjectId;            // Unique subject Id
    private Subject         mSubject;              // Specific subject

    /***************************************************************************/
    /*                  Create the layout for book choices                     */
    /***************************************************************************/
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notecard_list, container, false);
        mNoteCardRecyclerView = (RecyclerView) view.findViewById(R.id.notecard_recycler_view);
        mNoteCardRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mSubjectId = (UUID) getActivity().getIntent().getSerializableExtra(EXTRA_SUBJECT_ID);
        mSubject   = NoteSingleton.get().getSubject(mSubjectId);

        onResume();

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id)
        {
            case R.id.action_add:
                FragmentManager        manager = getActivity().getSupportFragmentManager();
                DialogNoteCardFragment dialog  = DialogNoteCardFragment.newInstance(mSubjectId);
                dialog.setTargetFragment(this, REQUEST_CODE);
                dialog.show(manager, ADD_DIALOG);
                return true;

            case R.id.action_delete_all:
                Bundle subject_id = new Bundle();
                subject_id.putSerializable(SEND_NOTECARD_ID, mSubject.getSubjectId());

                FragmentManager        delete_manager = getFragmentManager();
                DialogDeleteAllNoteCard delete_dialog  = new DialogDeleteAllNoteCard();
                delete_dialog.setArguments(subject_id);
                delete_dialog.setTargetFragment(this, REQUEST_CODE);
                delete_dialog.show(delete_manager, DELETE_DIALOG);
                return true;

            default:
                return true;
        }
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
        List<NoteCard> notecards = lab.getNoteCard(mSubjectId);
        mSubject = NoteSingleton.get().getSubject(mSubjectId);

        if (mAdapter == null) {
            mAdapter = new NoteCardAdapter(notecards, mSubjectId);
            mNoteCardRecyclerView.setAdapter(mAdapter);
        }
        else
        {
            mAdapter.notifyDataSetChanged();
        }
    }

    /***************************************************************************/
    /*                      Refresh the list after dialog                      */
    /***************************************************************************/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        onResume();
    }

    /***************************************************************************/
    /*  Create the holder for each book and set up an action if the book       */
    /*  is clicked                                                             */
    /***************************************************************************/
    private class NoteCardHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mHeadTitle;
        private TextView mNoteCardTitle;
        private TextView mDate;
        private NoteCard mNoteCard;
        private Subject  mSubject;

        public NoteCardHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mNoteCardTitle = (TextView)itemView.findViewById(R.id.note_card_title);
            mDate = (TextView)itemView.findViewById(R.id.note_card_date);
        }

        public void bindNoteCard(NoteCard notecard, int position, Subject subject) {
            mNoteCard = notecard;
            mSubject  = subject;
            mNoteCardTitle.setText(mNoteCard.getNoteCardTitle());
            mDate.setText(mNoteCard.getDate().toString());
        }

        @Override
        public void onClick(View v){
            Intent moveLayout = NotecardViewPager.newIntent(getActivity(), mNoteCard.getNoteCardId());
            startActivity(moveLayout);
        }
    }

    /***************************************************************************/
    /*                      Create the adapter for each book                   */
    /***************************************************************************/
    private class NoteCardAdapter extends RecyclerView.Adapter<NoteCardHolder> {
        private List<NoteCard> mNoteCards; // Array for each book
        private Subject        mSubject;

        public NoteCardAdapter(List<NoteCard> notecards, UUID subjectId) {
            mNoteCards = notecards;
            mSubject   = NoteSingleton.get().getSubject(subjectId);
        }

        @Override
        public NoteCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_notecard, parent, false);
            return new NoteCardHolder(view);
        }

        @Override
        public void onBindViewHolder(NoteCardHolder holder, int position) {
            NoteCard notecard = mNoteCards.get(position);
            holder.bindNoteCard(notecard, position, mSubject);
        }

        @Override
        public int getItemCount() {
            return mNoteCards.size();
        }
    }
}