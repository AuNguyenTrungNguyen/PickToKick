package picktokick.devfest.picktokick.activitys;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import picktokick.devfest.picktokick.R;
import picktokick.devfest.picktokick.adapter.AdapterListFriend;
import picktokick.devfest.picktokick.objects.Constanttt;
import picktokick.devfest.picktokick.objects.Friend;

public class ListFriendActivity extends AppCompatActivity {

    private DatabaseReference data;
    private RecyclerView listAddFriend;
    private AdapterListFriend adapterAddFriend;
    private List<Friend> friendList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_friend);

        data = FirebaseDatabase.getInstance().getReference();
        listAddFriend = findViewById(R.id.lvListFriend);

        friendList = new ArrayList<>();
        adapterAddFriend = new AdapterListFriend(friendList, this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        listAddFriend.setLayoutManager(manager);
        listAddFriend.setAdapter(adapterAddFriend);
        loadData();

    }

    private void loadData() {
        String myID = getSharedPreferences(Constanttt.SHARE_REF_LOGIN, Context.MODE_PRIVATE).getString(Constanttt.LOGIN_ID, null);

        data.child(Constanttt.USERS).child(myID).child(Constanttt.USERS_listFriends).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Friend friend = dataSnapshot.getValue(Friend.class);
                friendList.add(friend);
                Log.e("logg", friend.getNameFr());
                adapterAddFriend.notifyDataSetChanged();
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
}
