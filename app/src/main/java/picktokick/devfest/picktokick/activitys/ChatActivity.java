package picktokick.devfest.picktokick.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import picktokick.devfest.picktokick.R;
import picktokick.devfest.picktokick.objects.Member;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getDataFromAdapter();
    }

    private void getDataFromAdapter() {
        List<Member> memberList = (List<Member>) getIntent().getSerializableExtra("listMember");
        Log.e("TAG", String.valueOf(memberList.size()));
    }
}
