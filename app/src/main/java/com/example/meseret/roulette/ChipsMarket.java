package com.example.meseret.roulette;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import adapterModel.ChipsMarketAdapterModel;
import clients.UsersClient;
import clientsModel.ChipsMarketModel;
import clientsModel.CompanyBitcoinAddress;
import clientsModel.MaxocoinChipsValue;
import model.ResponseMessage;
import projectstatic.ProjectStatic;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChipsMarket extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<ChipsMarketAdapterModel> adapterModels;

    private TextView title;
    private String token;
    private Intent intent;
    private ProgressBar pr;
    private SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private TextView company_bitcoin_address,chips_maxocoin_value,maxocoin_link;

    private EditText chips_value_input,transaction_id_input;
    private Button send_request;
    private TextView error,text_success_hower;
    private ProgressBar progressBar;
    private LinearLayout main_layout,success_layout;
    private TimerTask timerTask;
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chips_market);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(android.graphics.Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        title=(TextView)findViewById(R.id.title);
        title.setText("Chips Market");

        intent=getIntent();
        token=intent.getStringExtra("token");
        preferences=getSharedPreferences("token_value",0);
        editor=preferences.edit();
        editor.putString("token",token);
        editor.commit();





        main_layout=(LinearLayout)findViewById(R.id.main_layout);
        success_layout=(LinearLayout)findViewById(R.id.success_shower_layout);

        text_success_hower=(TextView)findViewById(R.id.success_shower_textview);

        company_bitcoin_address=(TextView)findViewById(R.id.company_bitcoin_address);
        chips_maxocoin_value=(TextView)findViewById(R.id.chips_maxocoin_value);
        maxocoin_link=(TextView)findViewById(R.id.maxocoin_link);

        chips_value_input=(EditText)findViewById(R.id.chips_amount_value);
        transaction_id_input=(EditText)findViewById(R.id.transaction_value);

        send_request=(Button)findViewById(R.id.send_request_btn);
        error=(TextView)findViewById(R.id.chipsMarket_error);
        progressBar=(ProgressBar)findViewById(R.id.chips_pr);

        Retrofit retrofit=getAPIUser();
        UsersClient client = retrofit.create(UsersClient.class);
        Call<List<CompanyBitcoinAddress>> call=client.getccompany_address(token);
        call.enqueue(new Callback<List<CompanyBitcoinAddress>>() {
            @Override
            public void onResponse(Call<List<CompanyBitcoinAddress>> call, Response<List<CompanyBitcoinAddress>> response) {
                company_bitcoin_address.setText("Our Maxocoin address: "+response.body().get(0).getAddress());
            }

            @Override
            public void onFailure(Call<List<CompanyBitcoinAddress>> call, Throwable t) {

            }
        });

        Retrofit retrofit1= getAPIUser();
        UsersClient client1= retrofit1.create(UsersClient.class);
        Call<List<MaxocoinChipsValue>> call1= client1.getMaxocoinChipsValue(token);
        call1.enqueue(new Callback<List<MaxocoinChipsValue>>() {
            @Override
            public void onResponse(Call<List<MaxocoinChipsValue>> call, Response<List<MaxocoinChipsValue>> response) {
               chips_maxocoin_value.setText(response.body().get(0).getChips()+" chips = "+response.body().get(0).getMaxocoin()+" maxocoin");
            }

            @Override
            public void onFailure(Call<List<MaxocoinChipsValue>> call, Throwable t) {

            }
        });

        send_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String chips_value= chips_value_input.getText().toString();
                String transaction_value= transaction_id_input.getText().toString();
                if (chips_value.equals("")){
                    error.setVisibility(View.VISIBLE);
                    error.setText("Please enter how many chips you need");
                }else if (transaction_value.equals("")){
                    error.setVisibility(View.VISIBLE);
                    error.setText("Please enter your maxocoin transaction id");
                }else {
                    progressBar.setVisibility(View.VISIBLE);
                    ChipsMarketModel model=new ChipsMarketModel(chips_value,transaction_value);
                    Retrofit retrofit2= getAPIUser();
                    UsersClient client2=retrofit2.create(UsersClient.class);
                    Call<ResponseMessage> call2=client2.send_chips_request(token,model);
                    call2.enqueue(new Callback<ResponseMessage>() {
                        @Override
                        public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                           if (response.body().getStatus().equals("ok")){
                               progressBar.setVisibility(View.GONE);
                               main_layout.setVisibility(View.GONE);
                               success_layout.setVisibility(View.VISIBLE);
                               text_success_hower.setText("Your request is submitted successfully.We will update your account after checking your maxocoin transaction");
                           }
                        }

                        @Override
                        public void onFailure(Call<ResponseMessage> call, Throwable t) {

                        }
                    });
                }
            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.show_address:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void setPr(){
        pr.setVisibility(View.VISIBLE);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chips_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
