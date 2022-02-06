package com.example.nodeappmvvm;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application){
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note){

        new InsertNoteAsyncTAsk(noteDao).execute(note);

    }
    public void update(Note note){
        new UpdateNoteAsyncTAsk(noteDao).execute(note);
    }
    public void delete(Note note){
        new DeleteNoteAsyncTAsk(noteDao).execute(note);
    }
    public void deleteAllNotes(){
        new DeleteAllNotesAsyncTAsk(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }

    private static class InsertNoteAsyncTAsk extends AsyncTask<Note,Void, Void>{
        private NoteDao noteDao;

        private InsertNoteAsyncTAsk(NoteDao noteDao){
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }
    private static class UpdateNoteAsyncTAsk extends AsyncTask<Note,Void, Void>{
        private NoteDao noteDao;

        private UpdateNoteAsyncTAsk(NoteDao noteDao){
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }
    private static class DeleteNoteAsyncTAsk extends AsyncTask<Note,Void, Void>{
        private NoteDao noteDao;

        private DeleteNoteAsyncTAsk(NoteDao noteDao){
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }
    private static class DeleteAllNotesAsyncTAsk extends AsyncTask<Void,Void, Void>{
        private NoteDao noteDao;

        private DeleteAllNotesAsyncTAsk(NoteDao noteDao){
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}
