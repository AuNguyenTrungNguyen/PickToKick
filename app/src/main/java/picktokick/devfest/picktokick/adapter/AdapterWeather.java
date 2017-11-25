package picktokick.devfest.picktokick.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import picktokick.devfest.picktokick.R;
import picktokick.devfest.picktokick.retrofit.ThongTin;

/**
 * Created by buimi on 11/25/2017.
 */

public class AdapterWeather extends RecyclerView.Adapter<AdapterWeather.RecyclerViewHolder>{

    private List<ThongTin> listData = new ArrayList<>();
    private Context context;

    public AdapterWeather(List<ThongTin> p, Context context) {
        this.listData = p;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.weather_day, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.titleview.setText(listData.get(position).getDate());
        holder.max.setText(listData.get(position).getMaxTem() + "");
        holder.min.setText(listData.get(position).getMinTem() + "");
        holder.state.setText(listData.get(position).getState());
        Picasso.with(context).load("http:"+listData.get(position).getUrl()).into(holder.imageView);
    }
    @Override
    public int getItemCount() {
        return listData.size();
    }
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView titleview, max,min ,state ;
        ImageView imageView;
        public RecyclerViewHolder(View view) {
            super(view);
            titleview = (TextView) view.findViewById(R.id.ngayText);
            imageView = (ImageView) view.findViewById(R.id.iconW);
            //compile 'com.squareup.picasso:picasso:2.5.2'
            max = (view.findViewById(R.id.Max));
            min = (view.findViewById(R.id.Min));
            state = (view.findViewById(R.id.stateW));
        }
    }
}