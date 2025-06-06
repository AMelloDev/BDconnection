package com.example.aula0506;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;

    Button buttonInsere;
    EditText editText;
    ListView listview;

    ArrayList

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        buttonInsere = findViewById(R.id.bntInsere);
        editText = (EditText) findViewById(R.id.edText);
        listview = findViewById(R.id.listView);

        db = openOrCreateDatabase("minhasnotinhas", MODE_PRIVATE,null );
        db.execSQL("CREATE TABLE IF NOT EXISTS notas (id INTEGER PRIMARY KEY AUTOINCREMENT, txt TEXT)");

        buttonInsere.setOnClickListener(v->{
            insereNota(editText.getText().toString());
        });
        listview.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View View, int i, long l ) {
                Nota n = (Nota)adapterView.getItemAtPosition(i);
                db.delete("notas", "id=?", new String[]{Integer.toString(n.id)});
                Toast.makeText(getApplicationContext(),Integer.toString(i),Toast.LENGTH_LONG).show();

            }
        });
        carregaNota();


    }

    public String insereNota(String txt){
        ContentValues cv = new ContentValues();
        cv.put("txt", txt);
        db.insert("notas", null, cv);
        return "Nota inserida";
    }
    public void carregaNota(){
        Cursor cursor = db.rawQuery("SELECT * FROM notas", null);
        cursor.moveToFirst();
        notas.clear;
        while(!cursor.isAfterLast()){
            int columnid = cursor.getColumnIndex("id");
            int columnTxt= cursor.getColumnIndex("txt");
            notas.add("select-notas",cursor.getString(collumn).toString());
            cursor.moveToFirst();
        }
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                notas);
        listview.setAdapter(adapter);
    }
}