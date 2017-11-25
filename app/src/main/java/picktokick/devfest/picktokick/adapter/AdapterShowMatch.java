package picktokick.devfest.picktokick.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import picktokick.devfest.picktokick.R;
import picktokick.devfest.picktokick.activitys.ChatActivity;
import picktokick.devfest.picktokick.objects.Constanttt;
import picktokick.devfest.picktokick.objects.Match;
import picktokick.devfest.picktokick.objects.Member;

/**
 * Created by Au Nguyen on 11/24/2017.
 */

public class AdapterShowMatch extends RecyclerView.Adapter<AdapterShowMatch.RecyclerViewHolder> {

    private List<Match> listData = new ArrayList<>();
    private Context context;

    public AdapterShowMatch(List<Match> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }


    public void addItem(int position, Match data) {
        listData.add(position, data);
        notifyItemInserted(position);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.item_match, parent, false);
        return new RecyclerViewHolder(row);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {

        final Match match = listData.get(position);

        if (match != null) {
            //lấy thông tin
            String nameOfHost = listData.get(position).getNameOfPoster();
            String timeString = listData.get(position).getThoigian();
            long value = Long.parseLong(timeString);
            Date date = new Date(value);
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String time = df.format(date);
            String address = listData.get(position).getAddressMatch();
            String type = listData.get(position).getTypeOfMatch();
            String info = "";
            List<Member> listMember = listData.get(position).getListMember();
            for (int i = 1; i < listMember.size(); i++) {
                info += listMember.get(i).getNameOfMember();
                if (i < listMember.size() - 1) {
                    info += ", ";
                } else {
                    info += ".";
                }
            }
            String description = listData.get(position).getDescription();
            String url = listData.get(position).getUrlOfMatch();

            holder.txtNameOfHost.setText(nameOfHost);
            holder.txtTime.setText(time);
            holder.txtAddress.setText(address);
            holder.txtType.setText("Loại sân: " + type);
            holder.txtInfoMember.setText("Thành viên: " + info);
            holder.txtDescription.setText("Mô tả: " + description);

            Glide.with(context).load(url).into(holder.imgShowMatch);



            Glide.with(context).load(match.getUrlPoster()).into(holder.imgADDsmall);

            holder.btnAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    accept(match);
                }
            });


            holder.btnChat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chat(match);
                }
            });
        }
    }

    private void chat(Match match) {
        List<Member> memberList = match.getListMember();
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra("listMember", (Serializable) memberList);
        intent.putExtra("time",match.getThoigian());
        context.startActivity(intent);
    }


    private void accept(Match match) {

        SharedPreferences preferences = context.getSharedPreferences(Constanttt.SHARE_REF_LOGIN, Context.MODE_PRIVATE);
        String idMember = preferences.getString(Constanttt.LOGIN_ID, "");

        List<Member> list = match.getListMember();

        boolean isExist = false;

        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getIdMember().equals(idMember)){
                isExist = true;
                break;
            }
        }

        if (isExist) {
            Toast.makeText(context, "Bạn đã có trong nhóm này rồi", Toast.LENGTH_SHORT).show();
        }else{
            String nameOfMember = preferences.getString(Constanttt.LOGIN_NAME, "");
            String urlMember = preferences.getString(Constanttt.LOGIN_LINK_IMG, "");

            DatabaseReference databaseReference;
            databaseReference = FirebaseDatabase.getInstance().getReference().child(Constanttt.MATCHs);
            Member member = new Member();
            member.setIdMember(idMember);
            member.setNameOfMember(nameOfMember);
            member.setUrlMember(urlMember);

            int count = match.getListMember().size();
            databaseReference.child(match.getIdMatch()).child("listMember").child(count+"").setValue(member);
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameOfHost, txtTime, txtAddress, txtType, txtInfoMember, txtDescription;
        ImageView imgShowMatch;
        Button btnAccept, btnChat;
        CircleImageView imgADDsmall;
        public RecyclerViewHolder(final View itemView) {

            super(itemView);

            txtNameOfHost = itemView.findViewById(R.id.txtNameOfHost);
            txtTime = itemView.findViewById(R.id.txtTime);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            txtType = itemView.findViewById(R.id.txtType);
            txtInfoMember = itemView.findViewById(R.id.txtInfoMember);
            txtDescription = itemView.findViewById(R.id.txtDescription);

            imgShowMatch = itemView.findViewById(R.id.imgShowMatch);
            imgADDsmall=itemView.findViewById(R.id.imgAnhDaiDienSmall);
            btnAccept = itemView.findViewById(R.id.btnAccept);
            btnChat = itemView.findViewById(R.id.btnChat);
        }
    }
}