package com.retrofit.masum.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.Serializable;

public class ViewData extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        tv=findViewById(R.id.textview);
        Product myData = (Product) getIntent().getExtras().getSerializable("data");
        tv.setText(myData.toString());
    }
}
