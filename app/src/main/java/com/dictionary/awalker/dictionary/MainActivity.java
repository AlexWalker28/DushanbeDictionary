package com.dictionary.awalker.dictionary;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private AutoCompleteTextView autoCompleteTextView;
    private ImageButton translateButton;
    private ImageButton menuButton;
    private PopupMenu popup;
    private FloatingActionButton addWordButton;
    private WordListAdapter wordListAdapter;
    private ListView listView;
    private ArrayList<Word> listData;
    private ArrayList<String> autoCompleteTextViewData;
    private ArrayAdapter<String> autoCompleteTextViewAdapter;

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private static final int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        translateButton = (ImageButton)findViewById(R.id.translateButton);
        menuButton = (ImageButton)findViewById(R.id.menuImageButton);
        addWordButton = (FloatingActionButton) findViewById(R.id.addWordButton);
        listView = (ListView)findViewById(R.id.listView);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Word");
        auth = FirebaseAuth.getInstance();

        listData = new ArrayList<>();
        autoCompleteTextViewData = new ArrayList<>();
        wordListAdapter = new WordListAdapter(this, listData);
        listView.setAdapter(wordListAdapter);
        listView.setTextFilterEnabled(true);

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

        autoCompleteTextViewAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line
                                                                                  ,autoCompleteTextViewData);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(autoCompleteTextViewAdapter);
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.this.wordListAdapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

       translateButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });
        addWordButton.setVisibility(View.INVISIBLE);
        auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(auth.getCurrentUser() != null){
                    addWordButton.setVisibility(View.VISIBLE);
                }
            }
        });

        addWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddWord.class);
                startActivity(intent);
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, WordActivity.class);
                startActivity(intent);
            }
        });
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup = new PopupMenu(MainActivity.this, v);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu_button, popup.getMenu());
                popup.show();

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                            switch (item.getItemId()){
                                case R.id.sign_in_menu_item:

                                    authListener = new FirebaseAuth.AuthStateListener() {
                                        @Override
                                        public void onAuthStateChanged(@NonNull FirebaseAuth auth) {
                                            FirebaseUser mFirebaseUser = auth.getCurrentUser();
                                            //User is signed out
                                            if (mFirebaseUser == null) {
                                                //   onSignOutCleanUp();
                                                //Starts sign-in flow
                                                startActivityForResult(
                                                        AuthUI.getInstance()
                                                                .createSignInIntentBuilder()
                                                                .setIsSmartLockEnabled(false)
                                                                .setProviders(Arrays.asList(
                                                                        new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                                                        new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                                                                .build(),
                                                        RC_SIGN_IN); //RC_SIGN_IN - request code
                                                //User is signed in
                                            } else {
                                                //    onSignInInit(mFirebaseUser);
                                                Toast.makeText(MainActivity.this, "Your are logged in!", Toast.LENGTH_LONG).show();
                                                addWordButton.setVisibility(View.VISIBLE);

                                            }
                                        }

                                    };
                                    auth.addAuthStateListener(authListener);
                                    break;

                                case R.id.sign_out_menu_item:
                                    auth.signOut();
                                    Toast.makeText(MainActivity.this, "Your are logged out!", Toast.LENGTH_LONG).show();
                                    addWordButton.setVisibility(View.INVISIBLE);
                                    break;
                            }

                        return true;
                    }
                });
            }
        });




    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }
}
