package com.jskgmail.bvpieee;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainChatActivity extends AppCompatActivity {

    // TODO: Add member variables here:
    private String mDisplayName;
     String forum ;
private String society_name="messages";
    private ListView mChatListView;
    private EditText mInputText;
    private ImageButton mSendButton;
    private DatabaseReference mDatabaseReference;
    private chatListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setDisplayShowHomeEnabled(true);
        Intent intent = getIntent();

         forum = intent.getStringExtra("forum");

        // TODO: Set up the display name and get the Firebase reference
        setupDisplayName();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        // Link the Views in the layout to the Java code
        mInputText = (EditText) findViewById(R.id.messageInput);
        mSendButton = (ImageButton) findViewById(R.id.sendButton);
        mChatListView = (ListView) findViewById(R.id.chat_list_view);

        // TODO: Send the message when the "enter" button is pressed
        mInputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                sendMessage();
                return true;
            }
        });


        // TODO: Add an OnClickListener to the sendButton to send a message
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    // TODO: Retrieve the display name from the Shared Preferences
  private void setupDisplayName(){
      SharedPreferences prefs = getSharedPreferences("ieee",MODE_PRIVATE);
      String mynaam = prefs.getString("name", "Anonymous");
;
    mDisplayName=mynaam;
    }








    private void sendMessage() {




        Log.d("FlashChat","I sent message");
        // TODO: Grab the text the user typed in and push the message to Firebase
        String input=mInputText.getText().toString();
        if(!input.equals("")){
            InstantMessage chat = new InstantMessage(input, mDisplayName);

            switch (forum) {
                case "ras": {
                    society_name="msg_ras";
                    mDatabaseReference.child("msg_ras").push().setValue(chat);
                    mInputText.setText("");
                    break;
                }
                case "cs": {
                    society_name="msg_cs";
                    mDatabaseReference.child("msg_cs").push().setValue(chat);
                    mInputText.setText("");
                    break;
                }
                case "wie": {
                    society_name="msg_wie";
                    mDatabaseReference.child("msg_wie").push().setValue(chat);
                    mInputText.setText("");
                    break;
                }
                case "ias": {
                    society_name="msg_ias";
                    mDatabaseReference.child("msg_ias").push().setValue(chat);
                    mInputText.setText("");
                    break;
                }
                case "hkn": {
                    society_name="msg_hkn";
                    mDatabaseReference.child("msg_hkn").push().setValue(chat);
                    mInputText.setText("");
                    break;
                }
                case "codex": {
                    society_name="msg_codex";
                    mDatabaseReference.child("msg_codex").push().setValue(chat);
                    mInputText.setText("");
                    break;
                }
                case "drishti": {
                    society_name="msg_drishti";
                    mDatabaseReference.child("msg_drishti").push().setValue(chat);
                    mInputText.setText("");
                    break;
                }
                case "rau": {
                    society_name="msg_rau";

                    chat = new InstantMessage(input, mDisplayName);
                    mDatabaseReference.child("msg_rau").push().setValue(chat);
                    mInputText.setText("");
                    break;
                }
                case "ecell": {
                    society_name="msg_ecell";

                    mDatabaseReference.child("msg_ecell").push().setValue(chat);
                    mInputText.setText("");
                    break;
                }
                case "gamma": {
                    society_name="msg_gamma";

                    mDatabaseReference.child("msg_gamma").push().setValue(chat);
                    mInputText.setText("");
                    break;
                }
                case "bqc": {
                    society_name="msg_bqc";

                    mDatabaseReference.child("msg_bqc").push().setValue(chat);
                    mInputText.setText("");
                    break;
                }
                case "makers": {
                    society_name="msg_maker";

                    mDatabaseReference.child("msg_maker").push().setValue(chat);
                    mInputText.setText("");
                    break;
                }
                case "gen": {
                    society_name="messages";

                    mDatabaseReference.child("messages").push().setValue(chat);
                    mInputText.setText("");
                    break;
                }
            }




        }

    }

    // TODO: Override the onStart() lifecycle method. Setup the adapter here.
    @Override
    public void onStart() {
        super.onStart();

        switch (forum) {
            case "ras": {
                society_name="msg_ras";

                break;
            }
            case "cs": {
                society_name="msg_cs";

                break;
            }
            case "wie": {
                society_name="msg_wie";

                break;
            }
            case "ias": {
                society_name="msg_ias";

                break;
            }
            case "hkn": {
                society_name="msg_hkn";

                break;
            }
            case "codex": {
                society_name="msg_codex";

                break;
            }
            case "drishti": {
                society_name="msg_drishti";

                break;
            }
            case "rau": {
                society_name="msg_rau";


                break;
            }
            case "ecell": {
                society_name="msg_ecell";


                break;
            }
            case "gamma": {
                society_name="msg_gamma";


                break;
            }
            case "bqc": {
                society_name="msg_bqc";


                break;
            }
            case "makers": {
                society_name="msg_maker";


                break;
            }
            case "gen": {
                society_name="messages";


                break;
            }
        }
















        mAdapter=new chatListAdapter(this,mDatabaseReference,mDisplayName,society_name);
        mChatListView.setAdapter(mAdapter);
    }

    @Override
    public void onStop() {
        super.onStop();
        // TODO: Remove the Firebase event listener on the adapter.
        mAdapter.cleanup();
    }

}
