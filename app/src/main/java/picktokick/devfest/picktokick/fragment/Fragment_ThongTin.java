package picktokick.devfest.picktokick.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import picktokick.devfest.picktokick.R;
import picktokick.devfest.picktokick.activitys.ListFriendActivity;
import picktokick.devfest.picktokick.activitys.LoginActivity;
import picktokick.devfest.picktokick.activitys.ShowListAddFriend;
import picktokick.devfest.picktokick.adapter.ProfileAdapter;
import picktokick.devfest.picktokick.objects.Constanttt;
import picktokick.devfest.picktokick.objects.Friend;
import picktokick.devfest.picktokick.objects.User;

/**
 * Created by quocb14005xx on 11/24/2017.
 */

public class Fragment_ThongTin extends Fragment implements AdapterView.OnItemClickListener {
    DatabaseReference database;
    User user;
    String mname, linkImg;
    private ListView lvProfile;
    private ProfileAdapter adapter_profile;
    private ArrayList<String> listProfile;
    private EditText edtInputAddFr;

    private TextView name;
    private CircleImageView anhDaiDien;
    private Button btnLogout;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongtin, container, false);
        database = FirebaseDatabase.getInstance().getReference();
        lvProfile = (ListView) view.findViewById(R.id.listviewProfile);
        name = (TextView) view.findViewById(R.id.txtName);
        anhDaiDien = (CircleImageView) view.findViewById(R.id.imgAnhDaiDien);
        btnLogout = (Button) view.findViewById(R.id.btnLogOut);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContext().getSharedPreferences(Constanttt.SHARE_REF_LOGIN,Context.MODE_PRIVATE)
                        .edit().clear().commit();
                disconnectFromFacebook();
                getActivity().startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
            }
        });


        listProfile = new ArrayList<>();
        listProfile.add("Thêm bạn bè");
        listProfile.add("Lời mời kết bạn");
        listProfile.add("Danh sách bạn bè của tôi");
        listProfile.add("Phản hồi và Đánh giá");
        listProfile.add("Hướng dẫn sử dụng");
        adapter_profile = new ProfileAdapter(getContext(), R.layout.item_profile, listProfile);
        lvProfile.setAdapter(adapter_profile);


        TextView txtId = (TextView) view.findViewById(R.id.txtIdUser);
        txtId.setText("ID facebook :" + getContext().getSharedPreferences(Constanttt.SHARE_REF_LOGIN,
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
        switch (i) {
            case 0:
                guiLoiMoiKetBan();
                break;
            case 1:
                xemLoiMoiKetBan();
                break;
            case 2:
                xemDanhSachBanBe();
                break;
            case 3:
                Dialog dialog2 = new Dialog(getContext());
                dialog2.setTitle("Thông tin và phản hồi APP");
                dialog2.setContentView(R.layout.dialog_info_app);
                dialog2.show();
                break;
            case 4:
                Dialog dialog3 = new Dialog(getContext());
                dialog3.setTitle("Hướng dẫn sử dụng");
                dialog3.setContentView(R.layout.dialog_tuts);
                dialog3.show();
                break;
        }
    }

    private void xemDanhSachBanBe()
    {
        startActivity(new Intent(getContext(), ListFriendActivity.class));
    }

    private void xemLoiMoiKetBan() {
        startActivity(new Intent(getContext(), ShowListAddFriend.class));
    }

    private void guiLoiMoiKetBan() {
        final Dialog dialog0 = new Dialog(getContext());
        dialog0.setTitle("Thêm bạn bè");
        dialog0.setContentView(R.layout.dialog_add_friend);
        dialog0.show();
        //anh xa trong dialog add friend
        edtInputAddFr = (EditText) dialog0.findViewById(R.id.edtInputAddFr);
        Button btnXemThu, btnok, btncancel;
        btncancel = (Button) dialog0.findViewById(R.id.btnCancelAddFr);
        btnok = (Button) dialog0.findViewById(R.id.btnOkAddFr);
        btnXemThu = (Button) dialog0.findViewById(R.id.btnXemAvata);


        //btn xem thu avt nguoi ban
        btnXemThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edtInputAddFr.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Vui lòng nhập thông tin cần thiết để tìm người bạn!", Toast.LENGTH_SHORT).show();
                } else {
                           /* String a[] = getUserByID(edtInputAddFr.getText().toString());
                            // Log.e(Constanttt.TAG_APP,"xxxx "+a[1]);
                            txttenXemThu.setText(a[0]);
                            Glide.with(getContext())
                                    .load(a[1])
                                    .into(imgADD);*/
                    //load anh bang glide
                }
            }
        });
        //button cancel ko ket ban
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog0.dismiss();
            }
        });
        //button click gửi lời mới kết bạn
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myID = getContext()
                        .getSharedPreferences(Constanttt.SHARE_REF_LOGIN, Context.MODE_PRIVATE).getString(Constanttt.LOGIN_ID, null);
                String link = getContext()
                        .getSharedPreferences(Constanttt.SHARE_REF_LOGIN, Context.MODE_PRIVATE).getString(Constanttt.LOGIN_LINK_IMG, null);
                String name = getContext()
                        .getSharedPreferences(Constanttt.SHARE_REF_LOGIN, Context.MODE_PRIVATE).getString(Constanttt.LOGIN_NAME, null);

                //A goi B id cua A,,,id A nam trong requestlist cua B
                database.child(Constanttt.USERS)
                        .child(edtInputAddFr.getText().toString())
                        .child(Constanttt.USERS_listWait)
                        .child(myID).setValue(new Friend(myID, link, name));

            }
        });
    }
    public void disconnectFromFacebook() {

        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {

                LoginManager.getInstance().logOut();

            }
        }).executeAsync();
    }
}
