package com.example.roomdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewNoteActivity extends AppCompatActivity {

    private static final String TAG = "NewNoteActivity";

    public static final String NOTE_ADDED = "new_note";
    private EditText etNewNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        etNewNote=findViewById(R.id.etNewNote);
        Button button=findViewById(R.id.bAdd);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent resultIntent=new Intent();
//FOR ADDING A NOTE
                if(TextUtils.isEmpty(etNewNote.getText())){
                    setResult(RESULT_CANCELED,resultIntent);
                }
                else{
                    String note=etNewNote.getText().toString();
                    resultIntent.putExtra(NOTE_ADDED,note);
                    setResult(RESULT_OK,resultIntent);
                }
                finish();

            }
        });
    }

}
