package com.android.musty;


public class Note {

    String name, tags, date, category;

    Note(String _header, String _tags, String _date, String _category) {
        name = _header;
        tags = _tags;
        date = _date;
        category = _category;
    }
}