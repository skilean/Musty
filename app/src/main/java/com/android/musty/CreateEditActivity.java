package com.android.musty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.util.Log;


public class CreateEditActivity extends AppCompatActivity {

    private Button AddNote;
    private CheckBox CheckBox;
    private CheckBox CheckBox2;
    private CheckBox CheckBox3;
    private CheckBox CheckBox4;
    private CheckBox CheckBox5;
    private CheckBox CheckBox6;

    public void onTagsClick(View view)
    {
        CheckBox = findViewById(R.id.checkBox);
        CheckBox2 = findViewById(R.id.checkBox2);
        CheckBox3 = findViewById(R.id.checkBox3);
        CheckBox4 = findViewById(R.id.checkBox4);
        CheckBox5 = findViewById(R.id.checkBox5);
        CheckBox6 = findViewById(R.id.checkBox6);

        if (CheckBox.getVisibility() == View.VISIBLE)
        {   CheckBox.setVisibility(View.GONE);
            CheckBox2.setVisibility(View.GONE);
            CheckBox3.setVisibility(View.GONE);
            CheckBox4.setVisibility(View.GONE);
            CheckBox5.setVisibility(View.GONE);
            CheckBox6.setVisibility(View.GONE);}
        else
        {   CheckBox.setVisibility(View.VISIBLE);
            CheckBox2.setVisibility(View.VISIBLE);
            CheckBox3.setVisibility(View.VISIBLE);
            CheckBox4.setVisibility(View.VISIBLE);
            CheckBox5.setVisibility(View.VISIBLE);
            CheckBox6.setVisibility(View.VISIBLE);}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit);



        AddNote = findViewById(R.id.add_button2);
        AddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText header = findViewById(R.id.note_nameIn);
                String headerstr = header.getText().toString();

                Spinner category = findViewById(R.id.catIn);
                String categorystr = category.getSelectedItem().toString();
                String tags = "";

                if(CheckBox.isChecked()){tags = tags + CheckBox.getText().toString() + " ";}
                if(CheckBox2.isChecked()){tags = tags + CheckBox2.getText().toString() + " ";}
                if(CheckBox3.isChecked()){tags = tags + CheckBox3.getText().toString() + " ";}
                if(CheckBox4.isChecked()){tags = tags + CheckBox4.getText().toString() + " ";}
                if(CheckBox5.isChecked()){tags = tags + CheckBox5.getText().toString() + " ";}
                if(CheckBox6.isChecked()){tags = tags + CheckBox6.getText().toString() + " ";}



                TableInteraction TI = new TableInteraction(CreateEditActivity.this.getApplicationContext());
                TI.addNote(DBHelper.Columns.TABLE_NAME, headerstr, tags, " ", categorystr, " ");
            }
        });

    }



}
