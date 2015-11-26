package chat.mumu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import chat.mumu.R;
import chat.mumu.activity.UserInfoActivity;
import chat.mumu.adapter.NearbyListAdapter;
import io.rong.imkit.RongIM;

/**
 * Created by Jan on 10/20/2015.
 */
public class NearbyFragment extends Fragment{
    Button mBtnConversation;
    Button mBtnConversationList;
    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_nearby, null);

        mListView = (ListView)v.findViewById(R.id.list);
        mListView.setAdapter(new NearbyListAdapter(getContext()));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(getContext(), UserInfoActivity.class));
            }
        });

        return v;
    }
}
