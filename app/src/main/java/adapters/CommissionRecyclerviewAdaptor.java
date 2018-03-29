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

import adapterModel.CommissionAdapterModel;
import adapterModel.LevelsAdapterModel;
import projectstatic.ProjectStatic;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Meseret on 11/3/2017.
 */

public class CommissionRecyclerviewAdaptor extends RecyclerView.Adapter<CommissionRecyclerviewAdaptor.MyViewHolder>{

    private Context mContext;
    private List<CommissionAdapterModel> albumList;
    private SharedPreferences preferences;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView user_name, total_commission;



        public MyViewHolder(View view) {
            super(view);
            user_name = (TextView) view.findViewById(R.id.user_name);
            total_commission = (TextView) view.findViewById(R.id.level_no);



        }

    }


    public CommissionRecyclerviewAdaptor(Context mContext, List<CommissionAdapterModel> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.levels_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final CommissionAdapterModel model = albumList.get(position);
        holder.user_name.setText(model.getUser_name());
        holder.total_commission.setText(model.getTotal_commission());
    }


    /**
     * Click listener for popup menu items
     */

    @Override
    public int getItemCount() {
        return albumList.size();
    }

}