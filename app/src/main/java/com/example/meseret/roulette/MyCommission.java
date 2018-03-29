package com.example.meseret.roulette;

import android.content.Intent;
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

import adapterModel.CommissionAdapterModel;
import adapters.CommissionRecyclerviewAdaptor;
import clients.UsersClient;
import clientsModel.CommissionModel;
import projectstatic.ProjectStatic;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyCommission extends AppCompatActivity {
 private RecyclerView recyclerView;
    private String token;
    private List<CommissionAdapterModel> modelList;
    private CommissionRecyclerviewAdaptor adaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_commission);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Commission");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent intent=getIntent();
        token=intent.getStringExtra("token");

        modelList=new ArrayList<>();
        adaptor=new CommissionRecyclerviewAdaptor(this,modelList);

        recyclerView=(RecyclerView)findViewById(R.id.commission_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Retrofit retrofit= getAPIUser();
        UsersClient client= retrofit.create(UsersClient.class);
        Call<List<CommissionModel>> call= client.get_my_commission(token);
        call.enqueue(new Callback<List<CommissionModel>>() {
            @Override
            public void onResponse(Call<List<CommissionModel>> call, Response<List<CommissionModel>> response) {
                if(response.isSuccessful()){
                    for (int i=0;i<response.body().size();i++){
                        CommissionAdapterModel model=new CommissionAdapterModel();
                        model.setUser_name(response.body().get(i).getUser_name());
                        model.setTotal_commission(response.body().get(i).getTotal_commision());
                        modelList.add(model);
                    }
                    recyclerView.setAdapter(adaptor);
                    adaptor.notifyDataSetChanged();
                }else {
                    Toast.makeText(getApplicationContext(),"No commission you have ",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<CommissionModel>> call, Throwable t) {

            }
        });




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
}
