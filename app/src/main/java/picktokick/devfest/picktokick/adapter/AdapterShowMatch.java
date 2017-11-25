package picktokick.devfest.picktokick.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import picktokick.devfest.picktokick.R;
import picktokick.devfest.picktokick.activitys.ChatActivity;
import picktokick.devfest.picktokick.objects.Match;

/**
 * Created by Au Nguyen on 11/24/2017.
 */

public class AdapterShowMatch extends RecyclerView.Adapter<AdapterShowMatch.RecyclerViewHolder>  {

    private List<Match> listData = new ArrayList<>();
    private Context context;
    private String id;
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

        Match match = listData.get(position);

        if(match != null){
            //lấy thông tin
            String nameOfHost = listData.get(position).getNameOfPoster();
            String time = listData.get(position).getThoigian();
            String address = listData.get(position).getAddressMatch();
            String type = listData.get(position).getTypeOfMatch();
            String info = "";
            List<String> listMember = listData.get(position).getListMember();
            for(int i = 0; i < listMember.size(); i++){
                info += listMember.get(i);
                if(i < listMember.size() - 1){
                    info += ", ";
                }else{
                    info += ".";
                }
            }
            String description = listData.get(position).getDescription();
            String url = listData.get(position).getUrlOfMatch();



            holder.txtNameOfHost.setText(nameOfHost);
            holder.txtTime.setText(time);
            holder.txtAddress.setText(address);
            holder.txtType.setText(type);
            holder.txtInfoMember.setText(info);
            holder.txtDescription.setText(description);

            Glide.with(context).load(url).into(holder.imgShowMatch);
           // if(getIDID(id).equals())
            holder.btnAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    accept();
                }
            });

            holder.btnShowInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showInfo();
                }
            });

            holder.btnChat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chat();
                }
            });
        }
    }

    private void chat() {
//        //Ten nhom
//        //id nguoi dung
//        //url hinh nguoi dung
//
//        Intent intent = new Intent(context, ChatActivity.class);
//        intent.putExtra("")
    }

    private void showInfo() {

    }

    private void accept() {

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public String getIDID(String IDID) {
        return IDID;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameOfHost, txtTime, txtAddress, txtType, txtInfoMember, txtDescription;
        ImageView imgShowMatch;
        Button btnAccept, btnShowInfo, btnChat;
        public RecyclerViewHolder(final View itemView) {

            super(itemView);

            txtNameOfHost = itemView.findViewById(R.id.txtNameOfHost);
            txtTime = itemView.findViewById(R.id.txtTime);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            txtType = itemView.findViewById(R.id.txtType);
            txtInfoMember = itemView.findViewById(R.id.txtInfoMember);
            txtDescription = itemView.findViewById(R.id.txtDescription);

            imgShowMatch = itemView.findViewById(R.id.imgShowMatch);

            btnAccept = itemView.findViewById(R.id.btnAccept);
            btnShowInfo = itemView.findViewById(R.id.btnShowInfo);
            btnChat = itemView.findViewById(R.id.btnChat);
        }
    }
}