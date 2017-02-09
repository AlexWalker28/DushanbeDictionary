package com.dictionary.awalker.dictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText searchEditText;
    private Button buttonVah;
    private ImageButton translateButton;
    private ImageButton addWordButton;
    private WordListAdapter wordListAdapter;
    private ListView listView;
    ArrayList<Word> listData;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEditText = (EditText)findViewById(R.id.searchEditText);
        buttonVah = (Button)findViewById(R.id.languageOneButton);
        translateButton = (ImageButton)findViewById(R.id.translateButton);
        addWordButton = (ImageButton)findViewById(R.id.addWordButton);
        listView = (ListView)findViewById(R.id.listView);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Word");

        listData = new ArrayList<>();
        wordListAdapter = new WordListAdapter(this, listData);
        listView.setAdapter(wordListAdapter);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Word word = dataSnapshot.getValue(Word.class);
                wordListAdapter.add(word);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        buttonVah.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });
       translateButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });
        addWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddWord.class);
                startActivity(intent);
            }
        });




    }
}
