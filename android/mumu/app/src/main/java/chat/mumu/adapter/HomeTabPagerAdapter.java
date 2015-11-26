package chat.mumu.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import chat.mumu.fragment.MineFragment;
import chat.mumu.fragment.NearbyFragment;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.fragment.MessageListFragment;
import io.rong.imlib.model.Conversation;

/**
 * Created by Jan on 10/20/2015.
 */
public class HomeTabPagerAdapter extends FragmentStatePagerAdapter {
    private static final int	TAB_COUNT	= 3;
    private Context mContext;

    public HomeTabPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new NearbyFragment();
                break;
            case 1:
                fragment = new ConversationListFragment();
                Context c;
                Uri uri = Uri.parse("rong://" + mContext.getApplicationInfo().packageName).buildUpon()
                        .appendPath("conversationlist")
                        .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话非聚合显示
                        .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//设置群组会话聚合显示
                        .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")//设置讨论组会话非聚合显示
                        .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//设置系统会话非聚合显示
                        .build();

                ((ConversationListFragment)fragment).setUri(uri);
                break;
            case 2:
                fragment = new MineFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

}
