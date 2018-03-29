package adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.meseret.roulette.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import adapterModel.LevelsAdapterModel;
import adapterModel.WithdrawalSendAdapterModel;
import clientsModel.WithdrawalSentModel;
import projectstatic.ProjectStatic;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Meseret on 11/3/2017.
 */

public class WithdrawalSendRequestRecyclerviewAdapter extends RecyclerView.Adapter<WithdrawalSendRequestRecyclerviewAdapter.MyViewHolder>{

    private Context mContext;
    private List<WithdrawalSendAdapterModel> albumList;
    private SharedPreferences preferences;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView amount, status;



        public MyViewHolder(View view) {
            super(view);
            amount = (TextView) view.findViewById(R.id.request_amount);
            status = (TextView) view.findViewById(R.id.status);



        }

    }


    public WithdrawalSendRequestRecyclerviewAdapter(Context mContext, List<WithdrawalSendAdapterModel> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.send_request_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final WithdrawalSendAdapterModel model = albumList.get(position);
        holder.amount.setText(model.getAmount());
        if (!model.getStatus().equals("0")){
            holder.status.setText("done");
        }else {
            holder.status.setText("waiting");
        }




    }


    /**
     * Click listener for popup menu items
     */

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public Retrofit getAPIUser() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(ProjectStatic.API_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson));

        Retrofit retrofit = builder.build();
        return retrofit;
    }
}
