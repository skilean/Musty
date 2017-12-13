package com.android.musty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.util.Log;

public class CreateEditActivity extends AppCompatActivity {

    private Button AddNote;
    private TableLayout TagsTable;

    public void onTagsClick(View view)
    {
        if (TagsTable.getVisibility() == View.VISIBLE)
            TagsTable.setVisibility(View.GONE);
        else
            TagsTable.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit);

        TagsTable = findViewById(R.id.tags_table);

        AddNote = findViewById(R.id.add_button2);
        AddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText header = findViewById(R.id.note_nameIn);
                String headerstr = header.getText().toString();

                Spinner category = findViewById(R.id.catIn);
                String categorystr = category.getSelectedItem().toString();;

                TableLayout tags  = findViewById(R.id.tags_table);
                String tagsstr;

                TableInteraction TI = new TableInteraction(CreateEditActivity.this.getApplicationContext());
                TI.addNote(DBHelper.Columns.TABLE_NAME, headerstr, "", "", categorystr, "");
            }
        });

    }



}
