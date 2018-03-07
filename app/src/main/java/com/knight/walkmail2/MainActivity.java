package com.knight.walkmail2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextTo;
    private EditText mEditTextSubject;
    private EditText mEditTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextTo = (EditText) findViewById(R.id.edit_text_to);
        mEditTextSubject = (EditText) findViewById(R.id.edit_text_subject);
        mEditTextMessage = (EditText) findViewById(R.id.edit_text_message);

        Button buttonSend = (Button) findViewById(R.id.button_send);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();
            }
        });


    }

    private void sendMail() {

        String recipientList = mEditTextTo.getText().toString();
        String[] recipients = recipientList.split(","); // Split multiple email ids

        String subject = mEditTextSubject.getText().toString();
        String message = mEditTextMessage.getText().toString();

        // Intent for initializing email send procedure

        Intent intent = new Intent (Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);


        // for directing android to search for the applications installed specifically for handling Emails i.e. rfc822 data

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an application:"));
    }
}
