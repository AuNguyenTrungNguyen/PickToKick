package picktokick.devfest.picktokick.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import picktokick.devfest.picktokick.R;

/**
 * Created by quocb14005xx on 11/24/2017.
 */

public class Fragment_ThoiTiet extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thoitiet,container,false);
    }
}
