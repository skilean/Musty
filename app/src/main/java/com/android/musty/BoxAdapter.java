package com.android.musty;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import com.sun.javafx.scene.layout.region.Margins;

public class BoxAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Note> objects;
    private Button EditButton, DeleteButton, CategoryButton;

    String noteid, categ;

    ListView mListView;

    BoxAdapter(Context context, ArrayList<Note> notes) {
        ctx = context;
        objects = notes;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;

        if (view == null) {
            view = lInflater.inflate(R.layout.item, parent, false);
        }

        Note p = getNotes(position);
        noteid = p.id;
        categ = p.category;

        // заполняем View в пункте списка данными
        ((TextView) view.findViewById(R.id.item_name)).setText(p.name);
        ((TextView) view.findViewById(R.id.item_tags)).setText(p.tags);
        ((TextView) view.findViewById(R.id.item_id)).setText(p.id);

        //обработчик клавиши
        EditButton = view.findViewById(R.id.edit_button);
        EditButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent = new Intent(ctx, CreateEditActivity.class);
                intent.putExtra("id", noteid);
                intent.putExtra("category", categ);
                ctx.startActivity(intent);
            }
        });

        DeleteButton = view.findViewById(R.id.delete_button);
        DeleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v) {
                TableInteraction TI = new TableInteraction(ctx);
                TI.deleteNote(DBHelper.Columns.TABLE_NAME, Integer.parseInt(noteid));
                notifyDataSetChanged();
                Toast toast = Toast.makeText(ctx, "Заметка удалена", Toast.LENGTH_SHORT);
                toast.show();

                switch (categ) {
                    case "Фильмы":
                        CategoryButton = (Button) ((Activity) ctx).findViewById(R.id.film_button);
                        CategoryButton.performClick();
                        break;

                    case "Сериалы":
                        CategoryButton = (Button) ((Activity) ctx).findViewById(R.id.series_button);
                        CategoryButton.performClick();
                        break;

                    case "Книги":
                        CategoryButton = (Button) ((Activity) ctx).findViewById(R.id.books_button);
                        CategoryButton.performClick();
                        break;

                    case "Игры":
                        CategoryButton = (Button) ((Activity) ctx).findViewById(R.id.games_button);
                        CategoryButton.performClick();
                        break;
                }
            }
        });


        return view;
    }

    public void clearData() {
        // clear the data
        objects.clear();
    }

    // заметка по позиции
    Note getNotes(int position) {
        return (Note) getItem(position);
    }
}