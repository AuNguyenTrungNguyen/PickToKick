package picktokick.devfest.picktokick.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import picktokick.devfest.picktokick.R;
import picktokick.devfest.picktokick.adapter.ProfileAdapter;
import picktokick.devfest.picktokick.objects.Constanttt;

/**
 * Created by quocb14005xx on 11/24/2017.
 */

public class Fragment_ThongTin extends Fragment implements AdapterView.OnItemClickListener{

    ListView lvProfile;
    ProfileAdapter adapter_profile;
    ArrayList<String> listProfile;




    TextView name;
    CircleImageView anhDaiDien;
    Button btnLogout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongtin, container, false);
        lvProfile = (ListView) view.findViewById(R.id.listviewProfile);
        name = (TextView) view.findViewById(R.id.txtName);
        anhDaiDien = (CircleImageView) view.findViewById(R.id.imgAnhDaiDien);
        btnLogout = (Button) view.findViewById(R.id.btnLogOut);

        listProfile = new ArrayList<>();
        listProfile.add("Thêm bạn bè");
        listProfile.add("Phản hồi và Đánh giá");
        listProfile.add("Hướng dẫn sử dụng");
        adapter_profile = new ProfileAdapter(getContext(), R.layout.item_profile, listProfile);
        lvProfile.setAdapter(adapter_profile);



        TextView txtId = (TextView) view.findViewById(R.id.txtIdUser);
        txtId.setText("ID facebook :"+getContext().getSharedPreferences(Constanttt.SHARE_REF_LOGIN,
                Context.MODE_PRIVATE).getString(Constanttt.LOGIN_ID, null));
        name.setText(getContext().getSharedPreferences(Constanttt.SHARE_REF_LOGIN,
                Context.MODE_PRIVATE).getString(Constanttt.LOGIN_NAME, null));
        Glide.with(this)
                .load(getContext().getSharedPreferences(Constanttt.SHARE_REF_LOGIN,
                        Context.MODE_PRIVATE).getString(Constanttt.LOGIN_LINK_IMG, null))
                .into(anhDaiDien);

        lvProfile.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i)
        {
            case 0:

                break;
            case 1:
                Dialog dialog = new Dialog(getContext());
                dialog.setTitle("Thông tin và phản hồi APP");
                dialog.setContentView(R.layout.dialog_info_app);
                dialog.show();
                break;
            case 2:
                Dialog dialog2 = new Dialog(getContext());
                dialog2.setTitle("Hướng dẫn sử dụng");
                dialog2.setContentView(R.layout.dialog_tuts);
                dialog2.show();
                break;
        }
    }
}
