package com.notecards.yohaniswarahartono.notecards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    NoteSingleton mNoteSingleton;
    NoteCard mNotecard;
    int addIndex = 1;
    TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        mNoteSingleton.get();
        mText = (TextView)findViewById(R.id.note_card_recycler_view);
        mText.setText(mNoteSingleton.showNoteCard());
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
                mNotecard = new NoteCard();
                mNotecard.setTitle("Crime #" + addIndex);
                mNoteSingleton.addNoteCard(mNotecard);
                mText.setText(mNoteSingleton.showNoteCard());
                addIndex++;
                return true;

            case R.id.action_del:
                mNoteSingleton.deleteNoteCard(mNotecard);
                mText.setText(mNoteSingleton.showNoteCard());
                addIndex--;
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
