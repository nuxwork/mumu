package chat.mumu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Jan on 10/20/2015.
 */
public class HomeTabAdapter extends BaseAdapter {
    private LayoutInflater	mInflater;
    private int[]			mSrcData;
    private int[]			mBgData;
    private String[]		mTxtData;
    private int				mImageId;
    private int				mTextId;
    private int				mLayoutId;

    public HomeTabAdapter(Context context, int layoutId, int textId, int imageId, String[] txtData,
                      int[] srcData, int[] bgData) {
        mLayoutId = layoutId;
        mImageId = imageId;
        mTextId = textId;
        mTxtData = txtData;
        mSrcData = srcData;
        mBgData = bgData;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (mSrcData != null) {
            return mSrcData.length;
        }
        return 0;
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
        View v = convertView;

        if (v == null) {
            v = mInflater.inflate(mLayoutId, null);
            ViewHolder holder = new ViewHolder();
            holder.img = (ImageView) v.findViewById(mImageId);
            holder.txt = (TextView) v.findViewById(mTextId);
            v.setTag(holder);
        }

        bindView(v, position);

        return v;
    }

    private void bindView(View v, int position) {
        ViewHolder holder = (ViewHolder) v.getTag();
        if (mSrcData != null)
            holder.img.setImageResource(mSrcData[position]);

        if (mBgData != null)
            holder.img.setBackgroundResource(mBgData[position]);

        holder.txt.setText(mTxtData[position]);
    }

    private class ViewHolder {
        public ImageView	img;
        public TextView		txt;
    }

}


