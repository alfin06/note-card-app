package com.notecards.yohaniswarahartono.notecards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

public class NoteCardListFragment extends Fragment {
    private RecyclerView NoteCardRecyclerView; // Recycler View for note card list
    private NoteCardAdapter Adapter;


    /**********************************************************************************************/
    /*                                          Create View                                       */

    /**********************************************************************************************/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view, container, false);

        NoteCardRecyclerView = (RecyclerView) view.findViewById(R.id.note_card_recycler_view);
        NoteCardRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        NoteSingleton noteSingleton = NoteSingleton.get();
        List<NoteCard> notecard = noteSingleton.getNoteCards();
        Adapter = new NoteCardAdapter(notecard);
        NoteCardRecyclerView.setAdapter(Adapter);

        return view;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_add:
                mNoteCard = new NoteCard();
                mNoteCard.setTitle("NoteCard #" + addIndex);
                mNoteSingleton.addNoteCard(mNoteCard);
                mNoteCardTitle.setText(mNoteSingleton.showNoteCard());
                addIndex++;
                return true;

            case R.id.action_del:
                mNoteSingleton.deleteNoteCard(mNoteCard);
                mNoteCardTitle.setText(mNoteSingleton.showNoteCard());
                addIndex--;
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
        NoteSingleton lab = NoteSingleton.get();
        List<NoteCard> notecards = lab.getNoteCards();
        super.onResume();
        if (Adapter == null) {
            Adapter = new NoteCardAdapter(notecards);
            NoteCardRecyclerView.setAdapter(Adapter);
        } else {
            Adapter.notifyDataSetChanged();
        }
    }

    /***************************************************************************/
    /*  Create the holder for each book and set up an action if the book       */
    /*  is clicked                                                             */

    /***************************************************************************/
    private class NoteCardHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private TextView mTotalNoteCard;  // Total notecards
        private NoteCard mNoteCard;       // Book class
        private TextView mNoteCardTitle;  // NoteCard title
        private int addIndex = 10;
        private NoteSingleton mNoteSingleton;

        public NoteCardHolder(View itemView) {
            super(itemView);
            mNoteCardTitle = (TextView) itemView.findViewById(R.id.note_card_title);
            mTotalNoteCard = (TextView) itemView.findViewById(R.id.total_note_card);
        }

        public void bindNoteCard(NoteCard notecard) {
            mNoteCard = notecard;
            mNoteCardTitle.setText(mNoteCard.getTitle());
            int total = 5;//(mBooks.getTotalChaptersFinished() * 100) / mBooks.getTotalChapter();
            mTotalNoteCard.setText(total + " ");
        }

        @Override
        public void onClick(View v) {
 /* */
            Toast.makeText(getActivity(), mNoteCard.getTitle() + "clicked", Toast.LENGTH_SHORT).show();
        }
    }

    /***************************************************************************/
    /*                      Create the adapter for each book                   */
    /***************************************************************************/
    private class NoteCardAdapter extends RecyclerView.Adapter<NoteCardHolder> {
        private List<NoteCard> mNoteCards; // Array for each book

        public NoteCardAdapter(List<NoteCard> notecards) {
            mNoteCards = notecards;
        }

        @Override
        public NoteCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_view, parent, false);
            return new NoteCardHolder(view);
        }

        @Override
        public void onBindViewHolder(NoteCardHolder holder, int position) {
            NoteCard notecard = mNoteCards.get(position);
            holder.bindNoteCard(notecard);
        }

        @Override
        public int getItemCount() {
            return mNoteCards.size();
        }
    }
}
