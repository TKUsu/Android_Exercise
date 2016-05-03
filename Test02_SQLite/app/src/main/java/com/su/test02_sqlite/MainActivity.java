package com.su.test02_sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyDBHelper dbHelper;
    EditText tid, name, score, newScore, sql;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tid = (EditText)findViewById(R.id.editText);
        name = (EditText)findViewById(R.id.editText2);
        score = (EditText)findViewById(R.id.editText3);

        output = (TextView)findViewById(R.id.textView6);

        dbHelper = new MyDBHelper(this);
        dbHelper.setValue(dbHelper.getWritableDatabase(), tid, name, score, output);

    }

    public void OnClick(View view){

        switch (view.getId()){
            case R.id.button:
//                Toast.makeText(MainActivity.this, "this is insert", Toast.LENGTH_LONG).show();
                dbHelper.checkout("insert");
                break;
            case R.id.button2:
//                Toast.makeText(MainActivity.this,"this is search",Toast.LENGTH_LONG).show();
                dbHelper.checkout("search");
                break;
//            case R.id.button3:
//                Toast.makeText(MainActivity.this,"this is update",Toast.LENGTH_LONG).show();
//                dbHelper.checkout("update");
//                break;
//            case R.id.button4:
//                Toast.makeText(MainActivity.this,"this is delete",Toast.LENGTH_LONG).show();
//                dbHelper.checkout("delete");
//                break;
        }
    }
}
