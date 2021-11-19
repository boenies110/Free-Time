package boenies.pokemonhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.net.IDN;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "databasePkmnHelper01.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_DEX = "dex";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_CONTENT = "content";
    public static final String COLUMN_IMAGE = "image";

    public static final String SQL_CREATE =
            "create table " + TABLE_DEX + "(" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_TITLE + "text not null, " +
                    COLUMN_CONTENT + "text " +
                    COLUMN_IMAGE + "text);";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
