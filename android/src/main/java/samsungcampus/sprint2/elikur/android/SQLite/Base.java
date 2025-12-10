package samsungcampus.sprint2.elikur.android.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Base {
    private static DatabaseHelper dbHelper;
    private static SQLiteDatabase database;

    public static void init(Context context) {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public static int getBest() {
        if (database == null) {
            throw new IllegalStateException("You forgot Base.init(context)!");
        }

        Cursor cursor = database.query(
            DatabaseHelper.TABLE_NAME,
            new String[]{DatabaseHelper.COLUMN_SCORE},
            null, null, null, null, null
        );

        int bestScore = 0;
        if (cursor.moveToFirst()) {
            bestScore = cursor.getInt(0);
        }
        cursor.close();
        return bestScore;
    }

    public static void saveBest(int point) {
        if (database == null) {
            throw new IllegalStateException("You forgot Base.init(context)!");
        }

        String updateQuery = "UPDATE " + DatabaseHelper.TABLE_NAME +
            " SET " + DatabaseHelper.COLUMN_SCORE + " = " + point;
        database.execSQL(updateQuery);
    }
}

