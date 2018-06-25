package com.LearningLeaflets.IntentApp.Manager.data;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

@Database(entities = {AppAllocation.class}, version = 1)
public abstract class AllocationDatabase extends RoomDatabase {

    public abstract AllocationDao wordDao();

    private static AllocationDatabase INSTANCE;


    public static AllocationDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AllocationDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AllocationDatabase.class, "word_database")
                            .allowMainThreadQueries()
                            .addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static String TAG = "ALLOCATION DATABASE";
    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
        }


        @Override
        public void onCreate (@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background.
     * If you want to start with more words, just add them.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AllocationDao mDao;

        PopulateDbAsync(AllocationDatabase db) {
            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            //mDao.deleteAll();

            AppAllocation allocation = new AppAllocation(
                    "Camera",
                    "android.media.action.IMAGE_CAPTURE",
                    1,
                    true);
            mDao.insert(allocation);
            return null;
        }
    }
}

