package com.example.meseret.roulette;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.andengine.entity.text.Text;

import clients.UsersClient;
import clientsModel.ChangePassword;
import model.ResponseMessage;
import projectstatic.ProjectStatic;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Settings extends AppCompatActivity {
  private Button change_password,change_username;

    private EditText new_password;
    private Button change;
    private TextView error_password;
    private String token;

    private EditText new_username;
    private Button change_username_btn;
    private TextView error_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Setting");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent=getIntent();
        token=intent.getStringExtra("token");

        change_password=(Button)findViewById(R.id.change_password);
        change_username=(Button)findViewById(R.id.change_user_name);

        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog=new Dialog(Settings.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.change_password_dialog_layout);

                new_password=(EditText)dialog.findViewById(R.id.new_password);
                error_password=(TextView)dialog.findViewById(R.id.newpass_error);
                change=(Button)dialog.findViewById(R.id.change_password_btn);
                change.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      String new_pass_value= new_password.getText().toString();
                        if (new_pass_value.equals("")){
                            error_password.setVisibility(View.VISIBLE);
                            error_password.setText("PLease enter new password");
                        }else {
                            ChangePassword change=new ChangePassword(new_pass_value);
                            Retrofit retrofit=getAPIUser();
                            UsersClient client=retrofit.create(UsersClient.class);
                            Call<ResponseMessage>call=client.changePassword(token,change);
                            call.enqueue(new Callback<ResponseMessage>() {
                                @Override
                                public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                                    if (response.body().getStatus().equals("ok")){
                                        Toast.makeText(getApplicationContext(),"Password changed successfully",Toast.LENGTH_LONG).show();
                                        dialog.dismiss();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseMessage> call, Throwable t) {

                                }
                            });
                        }
                    }
                });
                dialog.show();
            }
        });

        change_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog=new Dialog(Settings.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.change_username_layout);

                new_username=(EditText)dialog.findViewById(R.id.new_username);
                error_username=(TextView)dialog.findViewById(R.id.newuser_error);
                change_username_btn=(Button)dialog.findViewById(R.id.change_user_name_btn);
                change_username_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String username_value=new_username.getText().toString();
                        if (username_value.equals("")){
                            error_username.setText("Please enter new user name");
                        }else {
                            ChangePassword change=new ChangePassword(username_value);
                            Retrofit retrofit=getAPIUser();
                            UsersClient client=retrofit.create(UsersClient.class);
                            Call<ResponseMessage>call=client.changeUsername(token,change);
                            call.enqueue(new Callback<ResponseMessage>() {
                                @Override
                                public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                                    if (response.body().getStatus().equals("ok")){
                                        Toast.makeText(getApplicationContext(),"Username changed successfully",Toast.LENGTH_LONG).show();
                                        dialog.dismiss();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseMessage> call, Throwable t) {

                                }
                            });
                        }
                    }
                });
                dialog.show();
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
