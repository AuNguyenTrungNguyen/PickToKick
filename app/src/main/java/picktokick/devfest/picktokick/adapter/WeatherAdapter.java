package picktokick.devfest.picktokick.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import picktokick.devfest.picktokick.R;
import picktokick.devfest.picktokick.retrofit.ThongTin;

/**
 * Created by banhtrung on 11/23/2017.
 */

public class WeatherAdapter extends ArrayAdapter<ThongTin> {

    public WeatherAdapter(Context context, int resource, List<ThongTin> items) {
        super(context, resource, items);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.weather_day, null);
        }
        ThongTin p = getItem(position);
        //Log.i("hi",getItem(position).getDate() );
        if (p != null) {
            // Anh xa + Gan gia tri
            TextView titleview = (TextView) view.findViewById(R.id.ngayText);
            titleview.setText(p.getDate());

/*
            TextView title = (TextView) view.findViewById(R.id.iconT);
            title.setText(p.getUrl());
*/

            //Toast.makeText(getContext(),p.getUrl(), Toast.LENGTH_SHORT).show();
            //http://cdn.apixu.com/weather/64x64/day/305.png
            ImageView imageView = (ImageView) view.findViewById(R.id.iconW);
            Picasso.with(getContext()).load("http:"+p.getUrl()).into(imageView);
            //compile 'com.squareup.picasso:picasso:2.5.2'
            TextView max = (view.findViewById(R.id.Max));
            max.setText(p.getMaxTem() + "");

            TextView min = (view.findViewById(R.id.Min));
            min.setText(p.getMinTem() + "");

            TextView state = (view.findViewById(R.id.stateW));
            state.setText(p.getState());
        }
        return view;
    }
}
