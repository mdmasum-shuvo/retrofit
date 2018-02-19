package com.retrofit.masum.retrofit;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Product> arrayList;
    CustomizedAdapter adapter;
    RecyclerView reView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reView= (RecyclerView) findViewById(R.id.recycleView);
        reView.setHasFixedSize(true);
        reView.setLayoutManager(new LinearLayoutManager(this));
        arrayList=new ArrayList<>();
        adapter = new CustomizedAdapter(MainActivity.this,arrayList );
        reView.setAdapter(adapter);
        getALlData(0,10);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getALlData(0,10);
                Toast.makeText(MainActivity.this, "view new data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getALlData(int from,int limit) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Product>> call = api.getHeroes(from,limit);

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> heroList = response.body();




/*                //Creating an String array for the ListView
                String[] heroes = new String[heroList.size()];

                //looping through all the heroes and inserting the names inside the string array
                for (int i = 0; i < heroList.size(); i++) {
                    heroes[i] = heroList.get(i).getName();
                    arrayList.add(heroList.get(i).getName());
                    //Toast.makeText(MainActivity.this, " "+heros, Toast.LENGTH_SHORT).show();
                }*/


                //displaying the string array into listview
                //Toast.makeText(MainActivity.this, " "+arrayList, Toast.LENGTH_LONG).show();


                //lv.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, heroes));

                arrayList.addAll(response.body());

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
