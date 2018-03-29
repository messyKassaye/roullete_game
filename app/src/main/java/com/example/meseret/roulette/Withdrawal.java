package com.example.meseret.roulette;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import adapters.WithdrawViewPagersAdapter;
import clients.UsersClient;
import fragments.NewWithdrawalFragment;
import fragments.SendWithdrawalRequest;
import projectstatic.ProjectStatic;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Withdrawal extends AppCompatActivity {
   private String token;
    private int amount=0;
    private Intent intent;
    private TabLayout htab_layout;
    private ViewPager pager;
    private WithdrawViewPagersAdapter adapter;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Withdrawal");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        intent=getIntent();
        token= intent.getStringExtra("token");
        amount=Integer.parseInt(intent.getStringExtra("amount"));
        preferences=getSharedPreferences("withdrawal",0);
        editor=preferences.edit();
        editor.putString("amount",intent.getStringExtra("amount"));
        editor.putString("token",token);
        editor.commit();

        pager=(ViewPager)findViewById(R.id.mall_viewpager);
        setUpViewPager(pager);
        htab_layout=(TabLayout)findViewById(R.id.htab_tabs);
        htab_layout.setupWithViewPager(pager);
        for (int i = 0; i < htab_layout.getTabCount(); i++) {
            TabLayout.Tab tab = htab_layout.getTabAt(i);
            RelativeLayout relativeLayout = (RelativeLayout)
                    LayoutInflater.from(this).inflate(R.layout.custom_tablayout, htab_layout, false);

            TextView tabTextView = (TextView) relativeLayout.findViewById(R.id.tab_title);
            tabTextView.setText(tab.getText());
            tab.setCustomView(relativeLayout);
        }




    }
    public void  setUpViewPager(ViewPager viewPager){
        WithdrawViewPagersAdapter adapter=new WithdrawViewPagersAdapter(getSupportFragmentManager());
        NewWithdrawalFragment newWithdrawalFragment=new NewWithdrawalFragment();
        SendWithdrawalRequest sendWithdrawalRequest=new SendWithdrawalRequest();
        adapter.addFrag(newWithdrawalFragment,"New Request");
        adapter.addFrag(sendWithdrawalRequest,"Sented Request");
        viewPager.setAdapter(adapter);
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
