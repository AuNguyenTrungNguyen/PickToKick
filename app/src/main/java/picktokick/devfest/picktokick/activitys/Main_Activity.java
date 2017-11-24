package picktokick.devfest.picktokick.activitys;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import picktokick.devfest.picktokick.R;
import picktokick.devfest.picktokick.fragment.Fragment_Home;
import picktokick.devfest.picktokick.fragment.Fragment_ThoiTiet;
import picktokick.devfest.picktokick.fragment.Fragment_ThongTin;
import picktokick.devfest.picktokick.objects.Constanttt;

public class Main_Activity extends AppCompatActivity {
    FragmentTransaction ft;
    FragmentManager fm;
    private TextView mTextMessage;

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

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportActionBar().setTitle("Trang chủ");
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.container_frame, new Fragment_Home());
        ft.commit();
        SharedPreferences ref = getSharedPreferences(Constanttt.SHARE_REF_LOGIN, MODE_PRIVATE);
        Log.e(Constanttt.TAG_APP
                , ref.getString(Constanttt.LOGIN_ID,null)
                        + "\n" + ref.getString(Constanttt.LOGIN_NAME,null)
                        + "\n" + ref.getString(Constanttt.LOGIN_LINK_IMG,null)
                        + "\n" + ref.getString(Constanttt.LOGIN_LATITUDE,null)
                        + "\n" + ref.getString(Constanttt.LOGIN_LONGITUDE,null)
        );
    }

}
