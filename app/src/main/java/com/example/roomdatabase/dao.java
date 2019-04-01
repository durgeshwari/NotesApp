package com.example.roomdatabase;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface dao {

    @Insert
    void insert(Note note);

    //for fetching all the words
    @Query("SELECT * FROM notes")
    LiveData<List<Note>> getAllNotes();

    //for fetching note fron note id

    @Query("SELECT * FROM notes WHERE id=:noteId")
    LiveData<Note> getNote(String noteId);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

}
