package com.notecards.yohaniswarahartono.notecards;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NoteCardListFragment extends Fragment
{
    public RecyclerView NoteCardRecyclerView; // Recycler View for note card list


    /**********************************************************************************************/
    /*                                          Create View                                       */
    /**********************************************************************************************/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.list_view, container, false);

        NoteCardRecyclerView = (RecyclerView)view.findViewById(R.id.note_card_recycler_view);
        NoteCardRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

}
