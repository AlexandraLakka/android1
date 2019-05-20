package it21525.dit.hua.gr.firstapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final EditText userId = findViewById(R.id.user_id);
        final Spinner list = findViewById(R.id.list);
        final Button button = findViewById(R.id.button);

        final DBHelper dbHelper = new DBHelper(SecondActivity.this);

        //load dropdown list with the timestamp values from the database
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dbHelper.loadDts());
        //option to not select any of the available timestamps
        dataAdapter.add("<select no timestamp>");
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        list.setAdapter(dataAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClassName("it21525.dit.hua.gr.firstapp2", "it21525.dit.hua.gr.firstapp2.ThirdActivity");
                i.putExtra("userId", userId.getText().toString());
                i.putExtra("dt", list.getSelectedItem().toString());
                startActivity(i);
            }
        });
    }
}
