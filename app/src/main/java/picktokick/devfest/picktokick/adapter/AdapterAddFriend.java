package picktokick.devfest.picktokick.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import picktokick.devfest.picktokick.R;
import picktokick.devfest.picktokick.objects.Constanttt;
import picktokick.devfest.picktokick.objects.Friend;

/**
 * Created by admin on 11/25/2017.
 */

public class AdapterAddFriend extends RecyclerView.Adapter<AdapterAddFriend.RecyclerViewHolder> {

    private List<Friend> listData = new ArrayList<>();
    private Context context;

    public AdapterAddFriend(List<Friend> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public AdapterAddFriend.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_friend, parent, false);
        return new RecyclerViewHolder(item);
    }

    @Override
    public void onBindViewHolder(AdapterAddFriend.RecyclerViewHolder holder, final int position) {
        holder.txtName.setText(listData.get(position).getNameFr());

        Glide.with(context).load(listData.get(position).getLink()).into(holder.imgAvatar);

        holder.imgAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acceptAddFriend(listData.get(position), position);
            }
        });
    }

    private void acceptAddFriend(Friend friend, final int position) {
        Toast.makeText(context, "show", Toast.LENGTH_SHORT).show();
        String myID = context.getSharedPreferences(Constanttt.SHARE_REF_LOGIN, Context.MODE_PRIVATE).getString(Constanttt.LOGIN_ID, null);
        String link = context
                .getSharedPreferences(Constanttt.SHARE_REF_LOGIN, Context.MODE_PRIVATE).getString(Constanttt.LOGIN_LINK_IMG, null);
        String name = context
                .getSharedPreferences(Constanttt.SHARE_REF_LOGIN, Context.MODE_PRIVATE).getString(Constanttt.LOGIN_NAME, null);


        String friendID = friend.getID();
        Log.e("logg", myID);
        Log.e("logg", friendID);
        DatabaseReference data = FirebaseDatabase.getInstance().getReference();
        data.child(Constanttt.USERS).child(myID).child(Constanttt.USERS_listWait).child(friendID).removeValue();
        data.child(Constanttt.USERS).child(myID).child(Constanttt.USERS_listFriends).child(friendID).setValue(friend);
        data.child(Constanttt.USERS).child(friendID).child(Constanttt.USERS_listFriends).child(myID)
                .setValue(new Friend(myID, link, name)).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                listData.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        ImageView imgAvatar;
        ImageView imgAccept;

        RecyclerViewHolder(final View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            imgAccept = itemView.findViewById(R.id.imgAccept);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);


        }


    }
}