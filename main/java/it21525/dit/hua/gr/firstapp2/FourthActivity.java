package it21525.dit.hua.gr.firstapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FourthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        final TextView query = findViewById(R.id.rows);

        final DBHelper dbHelper = new DBHelper(FourthActivity.this);
        ArrayList<ContactsContract> queryList;
        queryList = dbHelper.getAll();
        for(int j = 0; j < queryList.size(); j++){
            query.setText(query.getText().toString()+"\n\n"+queryList.get(j).toString());
        }
    }
}
