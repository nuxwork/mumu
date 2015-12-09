package chat.mumu.lego.activity;

import java.util.Stack;

import android.app.Activity;

public class ActivityStack {
	private static final String TAG = "Lego.ActivityStack";

	private Stack<Activity> mStack;

	private static ActivityStack mInstance;

	private static ActivityStack instance() {
		if (mInstance == null)
			mInstance = new ActivityStack();

		return mInstance;
	}

	private ActivityStack() {
		mStack = new Stack<Activity>();
	}

	public static void push(Activity activity) {
		instance().mStack.add(activity);
//		Log.i(TAG, "push ###  " + activity + "  \n" + mStack.toString());
	}

	public static Activity pop() {
		Activity act = instance().mStack.pop();
//		Log.e(TAG, "pop ###  " + act + " \n" + mStack.toString());
		return act;
	}

	public static Activity peek() {
		Activity act = instance().mStack.peek();
//		Log.v(TAG, "peek ###  " + act + " \n" + mStack.toString());
		return act;
	}

	public static boolean inTop(Activity activity) {
		if (instance().mStack.isEmpty())
			return false;

		Activity act = instance().mStack.peek();
		boolean in = act == activity;
//		Log.w(TAG, "inTop ###  " + activity + "  == " + in + "  \n" + mStack.toString());
		return in;
	}

	public static boolean remove(Activity activity) {
		boolean b = instance().mStack.remove(activity);
//		Log.e(TAG, "remove ###  " + activity + "  == " + b + "  \n" + mStack.toString());
		return b;
	}

	public static void backTo(Class<? extends Activity> cls) {
		int s = instance().mStack.size();
		for (int i = 0; i != s; i++) {
			Activity act = instance().mStack.pop();
			if(act.getClass() == cls)
				break;
			
			act.finish();
//			Log.e(TAG, "pop && finish ###  " + act + " \n" + mStack.toString());
		}
	}

	public static void exit() {
		int s = instance().mStack.size();
		for (int i = 0; i != s; i++) {
			Activity act = instance().mStack.pop();
			act.finish();
//			Log.e(TAG, "pop && finish ###  " + act + " \n" + mStack.toString());
		}
	}
}
