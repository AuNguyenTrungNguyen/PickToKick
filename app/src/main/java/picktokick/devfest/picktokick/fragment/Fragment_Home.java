package picktokick.devfest.picktokick.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import picktokick.devfest.picktokick.R;
import picktokick.devfest.picktokick.adapter.AdapterShowMatch;
import picktokick.devfest.picktokick.objects.Constanttt;
import picktokick.devfest.picktokick.objects.Match;

/**
 * Created by quocb14005xx on 11/24/2017.
 */

public class Fragment_Home extends Fragment {

    private RecyclerView rcvShowMatch;
    private AdapterShowMatch adapterShowMatch;
    private ArrayList<Match> listMatch;
    private LinearLayoutManager layoutManager;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        rcvShowMatch = view.findViewById(R.id.rcvShowMatch);

        listMatch = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        adapterShowMatch = new AdapterShowMatch(listMatch, getContext());

        rcvShowMatch.setLayoutManager(layoutManager);
        rcvShowMatch.setAdapter(adapterShowMatch);

        loadData();

        return view;
    }

    private void loadData(){
        databaseReference = FirebaseDatabase.getInstance().getReference().child(Constanttt.MATCHs+"Test");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Match match = (Match) dataSnapshot.getValue(Match.class);
                adapterShowMatch.addItem(listMatch.size(), match);
                Collections.sort(listMatch, new Comparator<Match>() {
                    @Override
                    public int compare(Match c1, Match c2) {
                        return Double.compare(Double.parseDouble(c1.getThoigian()), Double.parseDouble(c2.getThoigian()));
                    }
                });
                adapterShowMatch.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                listMatch.clear();
                Match match = (Match) dataSnapshot.getValue(Match.class);
                adapterShowMatch.addItem(listMatch.size(), match);
                Collections.sort(listMatch, new Comparator<Match>() {
                    @Override
                    public int compare(Match c1, Match c2) {
                        return Double.compare(Double.parseDouble(c1.getThoigian()), Double.parseDouble(c2.getThoigian()));
                    }
                });
                adapterShowMatch.notifyDataSetChanged();
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
