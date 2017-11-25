package picktokick.devfest.picktokick.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import picktokick.devfest.picktokick.R;
import picktokick.devfest.picktokick.objects.MessageObj;

/**
 * Created by phamh on 11/24/2017.
 */

public class HeaderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private Context mContext;
    private ArrayList<MessageObj> listMessage;
//
    public HeaderAdapter(Context mContext, ArrayList<MessageObj> listMessage) {
        this.mContext = mContext;
        this.listMessage = listMessage;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        if (viewType == 1) {
            //inflate your layout and pass it to view holder
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View view = inflater.inflate(R.layout.item_received_messages, parent, false);
            return new Receieve(view);
        } else{
            LayoutInflater inflater = LayoutInflater.from(mContext.getApplicationContext());
            View view = inflater.inflate(R.layout.item_sent_messages,parent,false);
            return new Sender(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case 0:
                Sender sender = (Sender) holder;
                MessageObj message = listMessage.get(position);
                sender.txtBodyMessage.setText(message.getBodyMessage());
                //sender.txtDate.setText(message.getDateSent());
                break;
            case 1:
                Receieve receieve = (Receieve) holder;
                MessageObj message2 = listMessage.get(position);
                receieve.txtMassageName.setText(message2.getUserName());
                receieve.txtBodyMessage.setText(message2.getBodyMessage());
                //receieve.txtDate.setText(message2.getDateSent());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return listMessage.size();
    }
//
    /**
     * 1 la nhan
     * 0 la gui
     * */
    @Override
    public int getItemViewType(int position) {
        if (listMessage.get(position).isCheckSend()){
            Log.e("TAG"," Mot 0");
            return 0;
        }
        Log.e("TAG","Ko 1");
        return 1;
    }


//    private boolean isPositionHeader(int position) {
//        return position == 0;
//    }
//
//    private String getItem(int position) {
//        return data[position - 1];
//    }

    class Receieve extends RecyclerView.ViewHolder {
        TextView txtBodyMessage;
        TextView txtDate;
        TextView txtMassageName;
        ImageView imvMassgeProfile;

        public Receieve(View itemView) {
            super(itemView);
            txtBodyMessage = itemView.findViewById(R.id.text_receive_message_body);
            txtDate = itemView.findViewById(R.id.text_receive_message_time);
            txtMassageName = itemView.findViewById(R.id.text_receive_message_name);
            imvMassgeProfile = itemView.findViewById(R.id.image_receive_message_profile);
        }
    }

    class Sender extends RecyclerView.ViewHolder {
        TextView txtBodyMessage;
        TextView txtDate;
        public Sender(View itemView) {
            super(itemView);
            txtBodyMessage = itemView.findViewById(R.id.text_sent_message_body);
            txtDate = itemView.findViewById(R.id.text_sent_message_time);
        }
    }

}
