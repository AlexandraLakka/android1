package it21525.dit.hua.gr.firstapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText textId = findViewById(R.id.user_id);
        final EditText textLongitude = findViewById(R.id.longitude);
        final EditText textLatitude = findViewById(R.id.latidude);
        final Button button1 = findViewById(R.id.button1);
        final Button button2 = findViewById(R.id.button2);
        final Button button3 = findViewById(R.id.button3);
        final DBHelper dbHelper = new DBHelper(MainActivity.this);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = textId.getText().toString();
                float lon = Float.valueOf(textLongitude.getText().toString());
                float lan = Float.valueOf(textLatitude.getText().toString());
                ContactsContract contactsContract = new ContactsContract(id, lon, lan, "");
                dbHelper.insert(contactsContract);
                Toast.makeText(MainActivity.this,"Saved successfully", Toast.LENGTH_LONG).show();

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClassName("it21525.dit.hua.gr.firstapp2", "it21525.dit.hua.gr.firstapp2.SecondActivity");
                startActivity(i);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClassName("it21525.dit.hua.gr.firstapp2", "it21525.dit.hua.gr.firstapp2.FourthActivity");
                startActivity(i);
            }
        });

    }
}
