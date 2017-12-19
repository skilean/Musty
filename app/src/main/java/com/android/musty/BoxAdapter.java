package com.android.musty;

import java.util.ArrayList;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class BoxAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Note> objects;
    private Button EditButton, DeleteButton, InfoButton;


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
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item, parent, false);
        }

        Note p = getNotes(position);

        // заполняем View в пункте списка данными
        ((TextView) view.findViewById(R.id.item_name)).setText(p.name);
        ((TextView) view.findViewById(R.id.item_tags)).setText(p.tags);

        //обработчик клавиши
        EditButton = view.findViewById(R.id.edit_button);
        EditButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent = new Intent(ctx, CreateEditActivity.class);
                ctx.startActivity(intent);
            }
        });

        DeleteButton = view.findViewById(R.id.delete_button);
        DeleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent = new Intent(ctx, CreateEditActivity.class);
                ctx.startActivity(intent);
            }
        });

        InfoButton = view.findViewById(R.id.info_button);
        InfoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent = new Intent(ctx, CreateEditActivity.class);
                ctx.startActivity(intent);
            }
        });

        return view;
    }

    // заметка по позиции
    Note getNotes(int position) {
        return (Note) getItem(position);
    }
}