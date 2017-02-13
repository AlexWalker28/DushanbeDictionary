package com.dictionary.awalker.dictionary;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class WordActivity extends AppCompatActivity {

    private ImageButton vahSound;
    private ImageButton rusSound;
    private ImageButton engSound;
    private TextView vahWord;
    private TextView rusWord;
    private TextView engWord;
    private ImageView img;
    private View.OnClickListener onClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        initVars();
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case (R.id.vahSound):
                        Toast.makeText(getApplicationContext(), "Ваханский", Toast.LENGTH_LONG).show();
                        return;
                    case (R.id.rusSound):
                        Toast.makeText(getApplicationContext(), "Русский", Toast.LENGTH_LONG).show();
                        return;
                    case (R.id.engSound):
                        Toast.makeText(getApplicationContext(), "Английский", Toast.LENGTH_LONG).show();
                        return;
                    default:

                        return;
                }
            }
        };
        vahSound.setOnClickListener(onClickListener);
        rusSound.setOnClickListener(onClickListener);
        engSound.setOnClickListener(onClickListener);

        Intent intent = getIntent();
        String vahLang = intent.getExtras().getString("vah");
        String rusLang = intent.getExtras().getString("rus");
        String engLang = intent.getExtras().getString("eng");
        vahWord.setText(vahLang);
        rusWord.setText(rusLang);
        engWord.setText(engLang);
    }

    private void initVars(){
        vahSound = (ImageButton)findViewById(R.id.vahSound);
        vahWord = (TextView)findViewById(R.id.vahText);
        rusSound = (ImageButton)findViewById(R.id.rusSound);
        rusWord = (TextView)findViewById(R.id.rusText);
        engSound = (ImageButton)findViewById(R.id.engSound);
        engWord = (TextView)findViewById(R.id.engText);
        img = (ImageView)findViewById(R.id.imageView2);
    }
}
