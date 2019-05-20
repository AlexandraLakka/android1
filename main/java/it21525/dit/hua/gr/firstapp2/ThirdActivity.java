package it21525.dit.hua.gr.firstapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        final TextView query = findViewById(R.id.query);

        Intent i = getIntent();
        String userId = i.getStringExtra("userId");
        String dt = i.getStringExtra("dt");

        final DBHelper dbHelper = new DBHelper(ThirdActivity.this);
        ArrayList<ContactsContract> queryList;
        queryList = dbHelper.getQuery(userId, dt);

        for(int j = 0; j < queryList.size(); j++){
            query.setText(query.getText().toString()+"\n\n"+queryList.get(j).toString());
        }

    }
}
