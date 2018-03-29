package fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meseret.roulette.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import clients.UsersClient;
import clientsModel.WithdrawalRequest;
import model.ResponseMessage;
import projectstatic.ProjectStatic;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewWithdrawalFragment extends Fragment {
  private EditText withdrawal_amount;
    private TextView error;
    private Button send;
    private int amount;
    private String token;
    private SharedPreferences preferences;
    private LinearLayout first_view,second_view;
    private TextView success_message;

    public NewWithdrawalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_new_withdrawal, container, false);

        preferences=getActivity().getSharedPreferences("withdrawal",0);
        amount=Integer.parseInt(preferences.getString("amount",""));
        token=preferences.getString("token","");
        first_view=(LinearLayout)view.findViewById(R.id.first_layout);
        second_view=(LinearLayout)view.findViewById(R.id.success_layout);
        success_message=(TextView)view.findViewById(R.id.success_message);

        withdrawal_amount=(EditText)view.findViewById(R.id.withdrawal_edittext);
        error=(TextView)view.findViewById(R.id.withdrawal_error);
        send=(Button)view.findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String withdrawal_value=withdrawal_amount.getText().toString();
                if (withdrawal_value.equals("")){
                    error.setVisibility(View.VISIBLE);
                    error.setText("Please enter the amount you want to withdrawal");
                }else {
                    int coin_amount= Integer.parseInt(withdrawal_value);
                    if (coin_amount>amount){
                        error.setVisibility(View.VISIBLE);
                        error.setText("You do not have this much amount in your account");
                    }else {
                      Retrofit retrofit=getAPIUser();
                        UsersClient client=retrofit.create(UsersClient.class);
                        WithdrawalRequest request=new WithdrawalRequest(withdrawal_value);
                        Call<ResponseMessage> call=client.send_request_withdrawal(token,request);
                        call.enqueue(new Callback<ResponseMessage>() {
                            @Override
                            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                               if (response.body().getStatus().equals("ok")){
                                   first_view.setVisibility(View.GONE);
                                   second_view.setVisibility(View.VISIBLE);
                                   success_message.setText("Your request is sent successfully");
                               }else {

                               }
                            }

                            @Override
                            public void onFailure(Call<ResponseMessage> call, Throwable t) {

                            }
                        });
                    }
                }
            }
        });

        return view;
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
