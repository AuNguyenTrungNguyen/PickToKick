package picktokick.devfest.picktokick.activitys;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import picktokick.devfest.picktokick.R;
import picktokick.devfest.picktokick.adapter.HeaderAdapter;
import picktokick.devfest.picktokick.objects.Constanttt;
import picktokick.devfest.picktokick.objects.Member;
import picktokick.devfest.picktokick.objects.MessageObj;

public class ChatActivity extends AppCompatActivity {
    private FloatingActionButton btnSend;
    private EditText edtContentMessage;
    private TextView txtChatConversation;
    private String roomName;

    private DatabaseReference root;
    private String temp_key;
    private RecyclerView recyclerView;
    private HeaderAdapter mAdapter;

    private ArrayList<MessageObj> messageList;

    private LinearLayoutManager mLayoutManager;

    private List<Member> memberList;
    private String time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getDataFromAdapter();

        messageList = new ArrayList<>();
        //listName = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.rcListMessage);

        mAdapter = new HeaderAdapter(this,messageList);
//        mAdapter = new HeaderAdapter(this,listName);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        mLayoutManager.setStackFromEnd(true);
//        mLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(mAdapter);


        edtContentMessage = findViewById(R.id.msg_input);
        btnSend = btnSend = findViewById(R.id.btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(memberList.get(0).getIdMember());
                edtContentMessage.setText("");
                edtContentMessage.requestFocus();
            }
        });


    }


    private void getDataFromAdapter() {
        memberList = (List<Member>) getIntent().getSerializableExtra("listMember");
        time = getIntent().getStringExtra("time");
        Log.e("TAG", String.valueOf(memberList.size()));
        Log.e("TAG", time);
        createOrReplaceDataChat(memberList.get(0).getIdMember());
        Log.e("TAG","id Truong nhoms" + memberList.get(0).getIdMember());
        //set title for layout
        setTitle(memberList.get(0).getNameOfMember());
        showMessageData(memberList.get(0).getIdMember());
    }


    private void createOrReplaceDataChat(String id){
        String idGroup = id+time;
        DatabaseReference root = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("GROUPCHAT").child(idGroup);
//
//        MessageObj messageObj = new MessageObj(memberList.get(0).getNameOfMember()
//                ,getHourSystem()
//                ,memberList.get(0).getUrlMember(),"Xin chào!",memberList.get(0).getIdMember(),false);
//        root.push().setValue(messageObj);
    }

    private void showMessageData(String id){
        String idGroup = id+time;
        DatabaseReference root = FirebaseDatabase
                .getInstance().getReference().child("GROUPCHAT").child(idGroup);

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                MessageObj messageObj = dataSnapshot.getValue(MessageObj.class);
                messageList.add(messageObj);
                Log.e("TAG",messageObj.getBodyMessage());
                mAdapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(mAdapter.getItemCount());
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
    }

    private void sendMessage(String id){
        String idGroup = id+time;
        DatabaseReference root = FirebaseDatabase
                .getInstance().getReference().child("GROUPCHAT").child(idGroup);
        SharedPreferences sharedPreferences = getSharedPreferences(Constanttt.SHARE_REF_LOGIN,MODE_PRIVATE);
        MessageObj messageObj = new MessageObj(sharedPreferences.getString(Constanttt.LOGIN_NAME,"")
                ,getHourSystem()
                ,sharedPreferences.getString(Constanttt.LOGIN_LINK_IMG,"")
                ,edtContentMessage.getText().toString()
                ,sharedPreferences.getString(Constanttt.LOGIN_ID,""),true);
        root.push().setValue(messageObj);
    }

    private void getDataChatFromGroupName(String id) {
        DatabaseReference root = FirebaseDatabase.getInstance().getReference().child(id);

        MessageObj message = new MessageObj("An",null,null,"Xin Chao",null,false);
        MessageObj message2 = new MessageObj("An",null,null,"Xin Chao",null,true);
        messageList.add(message);
        messageList.add(message2);
        mAdapter.notifyDataSetChanged();

    }
    private String getDateTimeSystem() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate + "-" + System.currentTimeMillis();
    }

    private String getHourSystem(){
        SimpleDateFormat sdfDate = new SimpleDateFormat("HH-mm");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}
