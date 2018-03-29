package fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.meseret.roulette.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import adapterModel.WithdrawalSendAdapterModel;
import adapters.WithdrawalSendRequestRecyclerviewAdapter;
import clients.UsersClient;
import clientsModel.WithdrawalSentModel;
import projectstatic.ProjectStatic;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class SendWithdrawalRequest extends Fragment {

    private RecyclerView recyclerView;
    private List<WithdrawalSendAdapterModel>modelList;
    private WithdrawalSendRequestRecyclerviewAdapter adapter;
    private SharedPreferences preferences;
    private String token;
    public SendWithdrawalRequest() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_send_withdrawal_request, container, false);

        preferences=getActivity().getSharedPreferences("withdrawal",0);
        token=preferences.getString("token","");

        modelList=new ArrayList<>();
        adapter=new WithdrawalSendRequestRecyclerviewAdapter(getActivity(),modelList);
        recyclerView=(RecyclerView)view.findViewById(R.id.send_request_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Retrofit retrofit= getAPIUser();
        UsersClient client=retrofit.create(UsersClient.class);
        Call<List<WithdrawalSentModel>> call=client.get_withdrawal(token);
        call.enqueue(new Callback<List<WithdrawalSentModel>>() {
            @Override
            public void onResponse(Call<List<WithdrawalSentModel>> call, Response<List<WithdrawalSentModel>> response) {
               for (int i=0;i<response.body().size();i++){
                    WithdrawalSendAdapterModel model=new WithdrawalSendAdapterModel();
                    model.setAmount(response.body().get(i).getAmount());
                    model.setStatus(response.body().get(i).getStatus());
                    modelList.add(model);
                }
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<WithdrawalSentModel>> call, Throwable t) {

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
