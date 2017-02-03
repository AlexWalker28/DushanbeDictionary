package com.example.lol.dictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddWord extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    EditText editText3;
    Button saveButton;
    Word newWord;

    FirebaseDatabase database;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        editText1 = (EditText)findViewById(R.id.wordOne);
        editText2 = (EditText)findViewById(R.id.wordTwo);
        editText3 = (EditText)findViewById(R.id.wordThree);
        saveButton = (Button)findViewById(R.id.saveButton);



        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                databaseReference = database.getReference().child("Word");
                newWord = new Word(editText1.getText().toString(), editText2.getText().toString(),
                                   editText3.getText().toString());
                databaseReference.push().setValue(newWord);
                Toast.makeText(getApplicationContext(), "Word saved", Toast.LENGTH_LONG).show();
            }
        });




    }
}
