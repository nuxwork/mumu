package chat.mumu.lego.anim;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by Jan on 11/26/2015.
 */
public class BreatheAnimation extends Animation {
    private float mFromAlpha;
    private float mToAlpha;

    /**
     * Constructor used when an AlphaAnimation is loaded from a resource.
     *
     * @param context Application context to use
     * @param attrs Attribute set from which to read values
     */
    public BreatheAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor to use when building an AlphaAnimation from code
     *
     * @param fromAlpha Starting alpha value for the animation, where 1.0 means
     *        fully opaque and 0.0 means fully transparent.
     * @param toAlpha Ending alpha value for the animation.
     */
    public BreatheAnimation(float fromAlpha, float toAlpha) {
        mFromAlpha = fromAlpha;
        mToAlpha = toAlpha;
    }

    /**
     * Changes the alpha property of the supplied {@link Transformation}
     */
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final float from = mFromAlpha;
        final float to = mToAlpha;
        final float alpha = from + ((mToAlpha - from) * interpolatedTime);

        t.setAlpha(alpha);
    }

    @Override
    public boolean willChangeTransformationMatrix() {
        return false;
    }

    @Override
    public boolean willChangeBounds() {
        return false;
    }

    public boolean hasAlpha() {
        return true;
    }
}