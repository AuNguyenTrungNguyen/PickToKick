package picktokick.devfest.picktokick.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import picktokick.devfest.picktokick.R;
/**
 * Created by quocb14005xx on 11/19/2017.
 */

public class ProfileAdapter extends ArrayAdapter<String>{
    Context ctx;
    int res;
    List<String >obj;
    public ProfileAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        ctx=context;
        res=resource;
        obj=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view =inflater.inflate(res,null);

        TextView txt = (TextView) view.findViewById(R.id.txtProfile);
        txt.setText(obj.get(position));
        return view;
    }
}
