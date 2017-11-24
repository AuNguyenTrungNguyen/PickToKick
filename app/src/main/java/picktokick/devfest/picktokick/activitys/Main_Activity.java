package picktokick.devfest.picktokick.activitys;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import picktokick.devfest.picktokick.R;
import picktokick.devfest.picktokick.fragment.Fragment_Home;
import picktokick.devfest.picktokick.fragment.Fragment_ThoiTiet;
import picktokick.devfest.picktokick.fragment.Fragment_ThongTin;
import picktokick.devfest.picktokick.objects.Constanttt;
import picktokick.devfest.picktokick.objects.Match;
import picktokick.devfest.picktokick.objects.Yard;

public class Main_Activity extends AppCompatActivity implements View.OnClickListener {
    int soluongYard;
    ArrayList<Yard> listYard;
    ArrayList<String> listNameYard;
    ArrayAdapter<String> adapteryard;
    Date dateFinish;
    Date hourFinish;
    DatabaseReference database;
    TextView txtTime, txtDate, txtTimeketthuc;
    private FragmentTransaction ft;
    private FragmentManager fm;
    private Dialog dialog;
    private ProgressDialog progressDialog;
    private Button btnChooseTime, btnCancelMatch, btnOkmatch, btnNgayDa, btnGioDa, btnGioKetThuc;
    private EditText edtName, edtMota;
    private AutoCompleteTextView autoCompleteTextViewMatch;
    private ImageView imgmatch;
    private RadioButton rd55, rd77, rd1111;
    private Calendar today;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportActionBar().setTitle("Thời tiết");
                    ft = fm.beginTransaction();
                    ft.replace(R.id.container_frame, new Fragment_ThoiTiet()).commit();
                    return true;
                case R.id.navigation_dashboard:
                    getSupportActionBar().setTitle("Trang chủ");
                    ft = fm.beginTransaction();
                    ft.replace(R.id.container_frame, new Fragment_Home()).commit();
                    return true;
                case R.id.navigation_notifications:
                    getSupportActionBar().setTitle("Thông tin ứng dụng");
                    ft = fm.beginTransaction();
                    ft.replace(R.id.container_frame, new Fragment_ThongTin()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_);
        database = FirebaseDatabase.getInstance().getReference();
        init();

    }

    private void init() {
        today = Calendar.getInstance();
        listYard = new ArrayList<>();

        database.child(Constanttt.FOOTBALL_YARD).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Yard yard = dataSnapshot.getValue(Yard.class);
                listYard.add(yard);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        database.child(Constanttt.FOOTBALL_YARD).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e(Constanttt.TAG_APP, listYard.size() + "");
                //list data cua auto complete
                listNameYard = new ArrayList<>();
                for (int i = 0; i < listYard.size(); i++) {
                    listNameYard.add(listYard.get(i).getTenSan() + "-" + listYard.get(i).getDiaChiSan());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportActionBar().setTitle("Trang chủ");
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.container_frame, new Fragment_Home());
        ft.commit();
        SharedPreferences ref = getSharedPreferences(Constanttt.SHARE_REF_LOGIN, MODE_PRIVATE);
        Log.e(Constanttt.TAG_APP
                , ref.getString(Constanttt.LOGIN_ID, null)
                        + "\n" + ref.getString(Constanttt.LOGIN_NAME, null)
                        + "\n" + ref.getString(Constanttt.LOGIN_LINK_IMG, null)
                        + "\n" + ref.getString(Constanttt.LOGIN_LATITUDE, null)
                        + "\n" + ref.getString(Constanttt.LOGIN_LONGITUDE, null)
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.create_match, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_create_match:
                ShowDialog_CreateTeam();
                break;
            default:
                break;
        }
        return true;
    }

    private void ShowDialog_CreateTeam() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_create_match);
        dialog.setTitle("TẠO TRẬN BANH");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        btnCancelMatch = (Button) dialog.findViewById(R.id.btnCancelMatch);
        btnOkmatch = (Button) dialog.findViewById(R.id.btnOkMatch);
        btnNgayDa = (Button) dialog.findViewById(R.id.btnNgayDa);
        btnGioDa = (Button) dialog.findViewById(R.id.btnGioDa);
        btnGioKetThuc = (Button) dialog.findViewById(R.id.btnGioKetThuc);
        btnGioKetThuc.setEnabled(false);

        txtDate = (TextView) dialog.findViewById(R.id.txtDate);
        txtTime = (TextView) dialog.findViewById(R.id.txtTime);
        txtTimeketthuc = (TextView) dialog.findViewById(R.id.txtTimeKetThuc);

        edtMota = (EditText) dialog.findViewById(R.id.edtMoTa);
        edtName = (EditText) dialog.findViewById(R.id.edtName);
        edtName.setEnabled(false);
        edtName.setText(getSharedPreferences(Constanttt.SHARE_REF_LOGIN, MODE_PRIVATE).getString(Constanttt.LOGIN_NAME, null));
        autoCompleteTextViewMatch = (AutoCompleteTextView) dialog.findViewById(R.id.auto_edt_MatchName);


        adapteryard = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, listNameYard);
        autoCompleteTextViewMatch.setAdapter(adapteryard);


        rd55 = (RadioButton) dialog.findViewById(R.id.radioBtn55);
        rd77 = (RadioButton) dialog.findViewById(R.id.radioBtn77);
        rd1111 = (RadioButton) dialog.findViewById(R.id.radioBtn1111);

        imgmatch = (ImageView) dialog.findViewById(R.id.imgMatch);

        btnOkmatch.setOnClickListener(this);
        btnGioDa.setOnClickListener(this);
        btnNgayDa.setOnClickListener(this);
        btnCancelMatch.setOnClickListener(this);
        imgmatch.setOnClickListener(this);
        btnGioKetThuc.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCancelMatch:
                dialog.dismiss();
                break;
            case R.id.btnOkMatch:
                XuLyCreate_Match();
                break;
            case R.id.btnGioDa:
                btnGioKetThuc.setEnabled(true);//nut gio ket thuc mac dinh laf false khi click gio bat dau thi true cho nut gio ket thuc
                showTimePickerDialog(true);
                break;
            case R.id.btnNgayDa:
                showDatePickerDialog();
                break;
            case R.id.btnGioKetThuc:
                showTimePickerDialog(false);
                break;
            case R.id.imgMatch:
                break;
        }
    }


    private void showDatePickerDialog() {
        int y = today.get(Calendar.YEAR);
        int m = today.get(Calendar.MONTH);
        int d = today.get(Calendar.DATE);
        DatePickerDialog dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                txtDate.setText(i2 + "/" + (i1 + 1) + "/" + i);
            }
        }, y, m, d);
        dateDialog.show();
    }


    private void showTimePickerDialog(final boolean b) {
        TimePickerDialog timeDialog = new TimePickerDialog(
                this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                if (b) {
                    txtTime.setText(i + ":" + i1);

                } else {
                    if (i < Integer.parseInt(txtTime.getText().toString().substring(0, 2))) {
                        Toast.makeText(Main_Activity.this, "Gio ket thuc khong the nho hon gio bat dau!", Toast.LENGTH_SHORT).show();
                    } else {
                        txtTimeketthuc.setText(i + ":" + i1);
                    }
                }
            }
        }, today.get(Calendar.HOUR_OF_DAY),
                today.get(Calendar.MINUTE), true);
        timeDialog.show();
    }


    private void XuLyCreate_Match() {

        Match tranbanh = new Match();
        tranbanh.setIdMatch(getDateTimeSystem());//id trận
        tranbanh.setAddressMatch(autoCompleteTextViewMatch.getText().toString());//địa chỉ trạn
        tranbanh.setIdPoster(getSharedPreferences(Constanttt.SHARE_REF_LOGIN, MODE_PRIVATE).getString(Constanttt.LOGIN_ID, null));
        tranbanh.setNameOfPoster(edtName.getText().toString());
        if (rd55.isChecked()) {
            tranbanh.setTypeOfMatch("5vs5");
        } else if (rd77.isChecked()) {
            tranbanh.setTypeOfMatch("7vs7");
        } else {
            tranbanh.setTypeOfMatch("11vs11");
        }



        tranbanh.setThoigian(String.valueOf(ChuyenDateTime2Long(txtDate.getText().toString(),txtTime.getText().toString())));
        //thời gian của trận
        Log.e(Constanttt.TAG_APP,"thoi gian match =" + String.valueOf(ChuyenDateTime2Long(txtDate.getText().toString(),txtTime.getText().toString())));
        ArrayList<String> listIdMem = new ArrayList<>();
        listIdMem.add("111111111111");
        listIdMem.add("222222222222");
        listIdMem.add("333333333333");
        listIdMem.add("444444444444");
        tranbanh.setListMember(listIdMem);//lít thanh viên

        tranbanh.setDescription(edtMota.getText().toString());// mo ta

        for (int i = 0; i < listYard.size(); i++) {
            if (TextUtils.equals(autoCompleteTextViewMatch.getText().toString(), listYard.get(i).getTenSan() + "-" + listYard.get(i).getDiaChiSan())) {
                //neu text trong auto complete == text data firebase thi set x,y,url img theo Yard do
                tranbanh.setxMatch(listYard.get(i).getX());
                tranbanh.setyMatch(listYard.get(i).getY());
                tranbanh.setUrlOfMatch(listYard.get(i).getListHinhAnh().get(0));
            }
        }
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Đang tạo trận banh....");
        progressDialog.show();
        database.child(Constanttt.MATCHs).child(getDateTimeSystem()).setValue(tranbanh).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                dialog.dismiss();
                progressDialog.dismiss();
                Toast.makeText(Main_Activity.this, "Create Match succesful!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private long ChuyenDateTime2Long(String date,String time)
    {
       long r=0;
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm");
        try {
            Date dt = df.parse(date + " " + time);
            Calendar ca = Calendar.getInstance();
            ca.setTime(dt);
            r=ca.getTimeInMillis();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return r;
    }
    //hàm lấy ngày giờ hẽ thống để làm id bài đăng
    private String getDateTimeSystem() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate + "-" + System.currentTimeMillis();
    }
}
