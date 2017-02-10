package com.dictionary.awalker.dictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
    private AutoCompleteTextView autoCompleteTextView;
    private ImageButton translateButton;
    private ImageButton addWordButton;
    private WordListAdapter wordListAdapter;
    private ListView listView;
    ArrayList<Word> listData;
    ArrayList<String> autoCompleteTextViewData;
    ArrayList<String> vahLanguageData;
    ArrayList<String> rusLanguageData;
    ArrayList<String> engLanguageData;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        translateButton = (ImageButton)findViewById(R.id.translateButton);
        addWordButton = (ImageButton)findViewById(R.id.addWordButton);
        listView = (ListView)findViewById(R.id.listView);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Word");

        listData = new ArrayList<>();
        vahLanguageData = new ArrayList<>();
        rusLanguageData = new ArrayList<>();
        engLanguageData = new ArrayList<>();
        autoCompleteTextViewData = new ArrayList<>();
        wordListAdapter = new WordListAdapter(this, listData);
        listView.setAdapter(wordListAdapter);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Word word = dataSnapshot.getValue(Word.class);
                wordListAdapter.add(word);
                autoCompleteTextViewData.add(word.getLanguageOne());
                autoCompleteTextViewData.add(word.getLanguageTwo());
                autoCompleteTextViewData.add(word.getLanguageThree());
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

        ArrayAdapter<String> autoCompleteTextViewAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line
                                                                                  ,autoCompleteTextViewData);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(autoCompleteTextViewAdapter);

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
