package com.example.lol.dictionary;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button buttonVah;
    private ImageButton translateButton;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=(EditText)findViewById(R.id.editText);
        buttonVah=(Button)findViewById(R.id.buttonVah);
        translateButton=(ImageButton) findViewById(R.id.translateButton);
        listView=(ListView)findViewById(R.id.listView);

       buttonVah.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Word word=new Word(editText.getText().toString(),listView.toString());
           }
       });
       translateButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Word word = new Word(editText.getText().toString(),listView.toString());
           }
       });


    }
}
