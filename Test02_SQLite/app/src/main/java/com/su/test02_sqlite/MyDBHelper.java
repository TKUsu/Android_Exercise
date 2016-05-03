package com.su.test02_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by ibelieveican on 2015/7/7.
 */
public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "todo";
    private static final int DATABASE_VERSION = 1;
    EditText tid, name, score, newScore, sql;
    TextView output;
    private SQLiteDatabase db;

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public void setValue(SQLiteDatabase db, EditText tid, EditText name, EditText score, TextView output) {
        this.db = db;
        this.tid = tid;
        this.name = name;
        this.score = score;
        this.output = output;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE road (" +
                " _id INTEGER PRIMARY KEY," +
                " lat DOUBLE," +
                " lng DOUBLE)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }





        public void checkout(String temp) {
            ContentValues cv;
            int count, id;
            switch (temp) {
                case "insert":
                    long idtemp;
                    cv = new ContentValues();
                    cv.put("_id", Integer.parseInt(tid.getText().toString()));
                    cv.put("name", name.getText().toString());
                    cv.put("score", Double.parseDouble(score.getText().toString()));
                    idtemp = db.insert("road", null, cv);
                    output.setText("新增記錄成功" + idtemp);
                    break;
                case "search":
                    SqlQuery("SELECT * FROM " + "road");
                    break;
//                case "update":
//                    id = Integer.parseInt(tid.getText().toString());
//                    cv = new ContentValues();
//                    cv.put("score", Double.parseDouble(score.getText().toString()));
//                    count = db.update("road", cv, "_id=" + id, null);
//                    output.setText("更新記錄成功" + count);
//                    break;

//                case "delete":
//                    id = Integer.parseInt(tid.getText().toString());
//                    count = db.delete("road" +
//                            "", "_id=" + id, null);
//                    output.setText("刪除記錄成功" + count);
//                    break;
            }
        }

        public void SqlQuery(String sql) {
            String[] colNames;
            String str = "";
            Cursor c = db.rawQuery(sql, null);
            colNames = c.getColumnNames();
            for (int i = 0; i < colNames.length; i++) {
                str += colNames[i] + "\t\t";
            }
            str += "\n";
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
                str += c.getString(0) + "\t";
                str += c.getString(1) + "\t";
                str += c.getString(2) + "\n";
                c.moveToNext();
            }
            output.setText(str.toString());
        }
}
