package com.example.roomdatabase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Insert;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

public class NoteVieModel extends AndroidViewModel {

    private String TAG = this.getClass().getSimpleName();
    private dao mDao;
    private NoteRoomDatabase roomdb;
    private LiveData<List<Note>> mAllNotes;

    public NoteVieModel(@NonNull Application application) {
        super(application);

        roomdb=NoteRoomDatabase.getDatabase(application);   //instance of db
        mDao=roomdb.mDao();  //dao object
        mAllNotes=mDao.getAllNotes();


    }

    //wrapper class of insert in non UI thread so use Async task

     public void insert(Note note) {
            new InsertAsyncTask(mDao).execute(note);
       }
       //wrapper fxn for getting all the data
       LiveData<List<Note>> getAllNotes() {         //since liveData,its to be observed in main activity
           return mAllNotes;
       }

    //wrapper class for updating note
    public  void update(Note note){
        new UpdateAsyncTask(mDao).execute(note);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG,"viewModel distroyed");
    }


    private class OperationsAsyncTask extends AsyncTask<Note, Void, Void> {

      dao mAsyncTaskDao;

        OperationsAsyncTask(dao mDao) {
            this.mAsyncTaskDao = mDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            return null;
        }
    }

    private class InsertAsyncTask extends OperationsAsyncTask {

        InsertAsyncTask(dao mNoteDao) {
            super(mNoteDao);
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mAsyncTaskDao.insert(notes[0]);
            return null;
        }
    }

    private class UpdateAsyncTask extends OperationsAsyncTask {

        UpdateAsyncTask(dao mDao) {
            super(mDao);
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mAsyncTaskDao.update(notes[0]);
            return null;
        }
    }

}

