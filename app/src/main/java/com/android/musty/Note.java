package com.android.musty;


public class Note {

    String name, tags, date, category, id;

    Note(String _header, String _tags, String _date, String _category, String _id) {
        name = _header;
        tags = _tags;
        date = _date;
        category = _category;
        id = _id;
    }
}