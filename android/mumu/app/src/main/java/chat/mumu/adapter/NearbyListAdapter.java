package chat.mumu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import chat.mumu.R;

/**
 * Created by Jan on 11/24/2015.
 */
public class NearbyListAdapter extends BaseAdapter{
    private Context mContext;

    public NearbyListAdapter(Context context){
        mContext = context;
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return View.inflate(mContext, R.layout.frag_nearby_list_item, null);
    }
}
