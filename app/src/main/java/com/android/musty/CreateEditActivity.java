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
    String noteid = null;
    String categ = null;
    int flag = 0;
    EditText editText;

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

        editText = findViewById(R.id.note_nameIn);

        noteid = getIntent().getStringExtra("id");
        categ = getIntent().getStringExtra("category");
        if (noteid != null)
        {
            TableInteraction TI = new TableInteraction(CreateEditActivity.this.getApplicationContext());
            Object[][] not = TI.readtable(DBHelper.Columns.TABLE_NAME, categ);
            if(not != null) {
                flag = 1;
                for (int i = 0; i < not.length; i++) {
                    if (not[i][0].toString() == noteid) {

                        editText.setHint("");
                        editText.setText(not[i][1].toString());

                        //Надо поменять спинер
                    }
                }
            }


        }

        TagsTable = findViewById(R.id.tags_table);

        AddNote = findViewById(R.id.add_button2);
        AddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText header = findViewById(R.id.note_nameIn);
                String headerstr = header.getText().toString();

                Spinner category = findViewById(R.id.catIn);
                String categorystr = category.getSelectedItem().toString();


                TableLayout tags  = findViewById(R.id.tags_table);
                String tagsstr;

                TableInteraction TI = new TableInteraction(CreateEditActivity.this.getApplicationContext());

                if (flag == 0)
                    TI.addNote(DBHelper.Columns.TABLE_NAME, headerstr, " ", " ", categorystr, " ");
                else
                    TI.editNote(DBHelper.Columns.TABLE_NAME, Integer.parseInt(noteid), headerstr, " ", " ", categorystr, " ");

                Intent intent = new Intent( CreateEditActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Если не хотим сохранять историю
                startActivity(intent);
            }
        });

    }



}
