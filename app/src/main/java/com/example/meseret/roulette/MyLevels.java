package com.example.meseret.roulette;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import adapterModel.LevelsAdapterModel;
import adapters.LevelsRecyclerviewAdapter;
import clients.UsersClient;
import clientsModel.LevelsModel;
import projectstatic.ProjectStatic;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyLevels extends AppCompatActivity {

    private String token;
    private Intent intent;
    private List<LevelsAdapterModel>modelList;
    private LevelsRecyclerviewAdapter adapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_levels);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Your Levels");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent=getIntent();
        token=intent.getStringExtra("token");

        modelList=new ArrayList<>();
        adapter=new LevelsRecyclerviewAdapter(this,modelList);
        recyclerView=(RecyclerView)findViewById(R.id.level_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Retrofit retrofit=getAPIUser();
        UsersClient client=retrofit.create(UsersClient.class);
        Call<List<LevelsModel>> call=client.get_my_levels(token);
        call.enqueue(new Callback<List<LevelsModel>>() {
            @Override
            public void onResponse(Call<List<LevelsModel>> call, Response<List<LevelsModel>> response) {
              for (int i=0;i<response.body().size();i++){
                  LevelsAdapterModel model=new LevelsAdapterModel();
                  model.setUser_name(response.body().get(i).getUser_name());
                  model.setLevels(response.body().get(i).getLevels());
                  modelList.add(model);
              }
              recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<LevelsModel>> call, Throwable t) {

            }
        });

    }

    public Retrofit getAPIUser(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(ProjectStatic.API_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson));

        Retrofit retrofit =builder.build();
        return  retrofit;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
