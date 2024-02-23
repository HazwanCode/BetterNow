package com.sp.betternow;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "assignment.db";
    private static final int SCHEMA_VERSION = 1;

    //Context c = null;
    public DataHelper(Context context) {

        super (context, DATABASE_NAME, null, SCHEMA_VERSION);
        //c = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE activity_table ( _id INTEGER PRIMARY KEY AUTOINCREMENT," + " activityName TEXT, activityDesc TEXT, imageData BLOB, activityDate TEXT, activityTime TEXT, activityMood TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getAll() {
        return (getReadableDatabase().rawQuery(
                "SELECT _id, activityName, activityDesc, imageData, activityDate, activityTime, activityMood FROM activity_table ORDER BY _id DESC", null));
    }

    public Cursor getById(String id) {
        String[] args = {id};

        return (getReadableDatabase().rawQuery(
                "SELECT _id, activityName, activityDesc, imageData, activityDate, activityTime, activityMood FROM activity_table WHERE _ID=?", args));
    }

    public void insert(String activityName, String activityDesc, byte[] imageData, String activityDate, String activityTime, String activityMood) {
        ContentValues cv = new ContentValues();

        cv.put("activityName", activityName);
        cv.put("activityDesc", activityDesc);
        cv.put("imageData", imageData);
        cv.put("activityDate", activityDate);
        cv.put("activityTime", activityTime);
        cv.put("activityMood", activityMood);
//        cv.put("lat", lat);
//        cv.put("lon", lon);

        getWritableDatabase().insert("activity_table", "activityName", cv);
        //Toast.makeText(c,activityName,Toast.LENGTH_LONG).show();
    }

    public void update(String id, String activityName, String activityDesc, byte[] imageData, String activityDate, String activityTime, String activityMood) {
        ContentValues cv = new ContentValues();
        String[] args = {id};
        cv.put("activityName", activityName);
        cv.put("activityDesc", activityDesc);
        cv.put("imageData", imageData);
        cv.put("activityDate", activityDate);
        cv.put("activityTime", activityTime);
        cv.put("activityMood", activityMood);

        getWritableDatabase().update("activity_table", cv, " _ID = ?", args);
    }

    public void remove(long id){
        //String[] args = {id};
        //getWritableDatabase().delete("activity_table", " _ID = ?", args);
        String string =String.valueOf(id);
        getWritableDatabase().execSQL("DELETE FROM activity_table WHERE _id = '" + string + "'");
    }

    public String getID(Cursor c) { return(c.getString(0)); }

    public String getActivityName(Cursor c) {
        return (c.getString(1));
    }

    public String getActivityDesc(Cursor c) { return (c.getString(2)); }

    public byte[] getImageData(Cursor c) { return (c.getBlob(3));}

    public String getActivityDate(Cursor c) { return (c.getString(4)); }

    public String getActivityTime(Cursor c) { return (c.getString(5)); }

    public String getActivityMood(Cursor c) { return (c.getString(6)); }

  //  public double getLatitude(Cursor c) {return (c.getDouble(5));}

  //  public double getLongitude(Cursor c) {return (c.getDouble(6));}

}

