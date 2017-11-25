package picktokick.devfest.picktokick.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import picktokick.devfest.picktokick.R;

import java.util.ArrayList;

import picktokick.devfest.picktokick.adapter.AdapterWeather;
import picktokick.devfest.picktokick.adapter.WeatherAdapter;
import picktokick.devfest.picktokick.retrofit.ThongTin;
import picktokick.devfest.picktokick.retrofit.Weather;
import picktokick.devfest.picktokick.retrofit.useApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by quocb14005xx on 11/24/2017.
 */

public class Fragment_ThoiTiet extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    // TODO: Rename and change types and number of parameters
    public static Fragment_ThoiTiet newInstance(int section) {
        Fragment_ThoiTiet fragment = new Fragment_ThoiTiet();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, section);
        fragment.setArguments(args);
        return fragment;
    }

    private WeatherAdapter weatherAdapter;
    private AdapterWeather adapterWeather;
    private RecyclerView recyclerView;
    private ArrayList<ThongTin> arrayList  = new ArrayList<>() ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thoitiet, container, false);

        TextView textView = (TextView) view.findViewById(R.id.cityName);

        //Lay du lieu thoi tiet
        callApi ();

        recyclerView = (RecyclerView) view.findViewById(R.id.lisWea);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return  view;
    }


    void callApi () {
        useApi.using().getApi().enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                String Date ;
                double MaxTem ;
                double MinTem ;
                String url ;
                String state ;

                arrayList = new ArrayList<ThongTin>();

                for (int i = 0 ; i < response.body().getForecast().getForecastday().size();i++ ) {
                    Date = response.body().getForecast().getForecastday().get(i).getDate();
                    MaxTem = response.body().getForecast().getForecastday().get(i).getDay().getMaxtempC();
                    MinTem = response.body().getForecast().getForecastday().get(i).getDay().getMintempC();
                    url = response.body().getForecast().getForecastday().get(i).getDay().getCondition().getIcon();
                    state = response.body().getForecast().getForecastday().get(i).getDay().getCondition().getText();

                    //ThongTin (String Date , String state , String url , double Max , double Min)
                    arrayList.add(new ThongTin(Date,state,url,MaxTem,MinTem));

                    Log.i("hi",url);
                }
                adapterWeather = new AdapterWeather(arrayList,getContext());
                recyclerView.setAdapter(adapterWeather);
                adapterWeather.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
            }
        });
    }



}
