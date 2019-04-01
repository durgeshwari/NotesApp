package com.example.roomdatabase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.support.annotation.NonNull;
import android.util.Log;

public class EditNoteViewModel extends AndroidViewModel {


        private String TAG = this.getClass().getSimpleName();
        private dao mDao;
        private NoteRoomDatabase roomdb;


    public EditNoteViewModel(@NonNull Application application) {
        super(application);
        Log.i(TAG, "Edit ViewModel");
        roomdb=NoteRoomDatabase.getDatabase(application);
        mDao=roomdb.mDao();
    }

    //wrapper fxn for getting note
    public LiveData<Note> getNote(String noteId) {
        return mDao.getNote(noteId);
    }

}
