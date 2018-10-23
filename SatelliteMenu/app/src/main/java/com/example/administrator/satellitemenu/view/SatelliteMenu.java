package com.example.administrator.satellitemenu.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.example.administrator.satellitemenu.R;

public class SatelliteMenu extends ViewGroup {
    private int pm_width;
    private int pm_height;

    private int mRadius;
    private Status mCurrentStatus = Status.CLOSE;
    private View mButton;
    private OnSatelliteMenuItemClickListener mMenuItemClickListener;

    public enum Status
    {
        OPEN, ClOSE
    }

    public interface OnSatelliteMenuItemClickListener
    {
        void onClick(View view, int pos);
    }

    public SatelliteMenu(Context context) {
        this(context, null);
    }

    public SatelliteMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SatelliteMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        pm_width =wm.getDefaultDisplay().getWidth();


        pm_height =wm.getDefaultDisplay().getHeight();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.ArcMenu, deStyleAttr, 0);
        mRadius =(int)a.getDimension(R.styleable.ArcMenu_radius,TypedValue

                .applyDimension(TypedValue.COMPLEX_UNIT_DIP,100,

                        getResources().getDisplayMetrics()));

        Log.i("tag",mRadius+"");

        a.recycle();
    }
     */
    @Override

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int count = getChildCount();        //获取所有的按钮
        for (int i = 0; i < count; i++)    //便利所有的按钮
        {
            // 测量所有按钮，宽度与高度
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     *设置按钮的位置
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b){
        if(changed){
            buttonLayout();
            childLayout();
        }
    }
    private void childLayout(){
        int count = getChildCount();
        for(int i = 0; i<count - 1;i++){
            View child = getChildAt(i + 1);
            child.setVisibility(View.GONE);
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            int c_x = pm_width / 2-childWidth/2;

            int childLeft  = (int) (mRadius * Math.sin(Math.PI / (count - 2)
                    *i));
            int childTop  = (int) (mRadius * Math.sin(Math.PI / (count - 2)
                    *i));
            childTop = getMeasuredHeight() - childHeight - childTop;
            if (i == 0) {
                childLeft = c_x - mRadius;
                childTop = getMeasuredHeight() - childHeight;
            }else if(i == 1){
                childLeft = c_x  - childLeft;
            } else if (i == 2) {
                childLeft=c_x;
                childTop=getMeasuredHeight()-childHeight-mRadius;
            }else if(i==3){
                childLeft = c_x + childLeft;

            } else if (i == 4) {
                childLeft = c_x + mRadius;
                childTop = getMeasuredHeight() - childHeight;
            }
            //设置子按钮的位置
            child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
        }
    }

    public void shrinkMenu(int time){
        int count = getChildCount();
        for(int i = 0; i<count-1;i++)
        {
            final View childView = getChildAt(i+1);
            childView.setVisibility(View.VISIBLE);

            int clx = (int) (mRadius * Math.sin(Math.PI / (count - 2) * i));
            int cty = (int) (mRadius * Math.cos(Math.PI  / (count - 2) * i));
            if (i == 0) {       //第一个子按钮
                clx = mRadius;
                cty = 0;
            } else if (i == 1) {    //第二个子按钮

            } else if (i == 2) {    //第三个子按钮
                clx = 0;
                cty = mRadius;
            } else if (i == 3) {    //第四个子按钮
                clx=(int) (mRadius * Math.cos(Math.PI  / (count - 2) * i));
                cty=-cty;
        else if(i == 4){
                    clx=-mRadius;
                    cty = 0;

                }
                AnimationSet animset = new AnimationSet(true);
                Animation tranAnim = null;
                if(mCurrentStatus == Status.CLOSE)
                {
                    tranAnim = new TranslateAnimation(clx,0, cty,0);
                    childView.setClickable(true);
                    childView.setFocusable(true);
                }else
                {
                    tranAnim = new TranslateAnimation(0, clx,0,cty);
                    childView.setClickable(false);
                    childView.setFocusable(false);
                }
                tranAnim.setDuration(time);
                tranAnim.setFillAfter(true);
                tranAnim.setStartOffset((i *100) / count);
                tranAnim.setAnimationListener(new Animation.AnimationLisener)()
                {
                    @Override
                    public void onAnimationStart(Animation animation)
                    {

                    }
                    @Override
                    public void onAnimationRepeat(Animation animation)
                    {

                    }
                    @Override
                    public void onAnimationEnd(Animation animation
	){
                    if(mCurrentStatus == Status.ClOSE)
                    {
                        childView.setVisibility(View.Gone);
                    }
                }
                };
                RotateAnimation rotateAnim = new RotateAnimation(0, 720,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnim.setDuration(time);       //设置旋转的时间
                rotateAnim.setFillAfter(true);      //停止最后一帧的位置
                animset.addAnimation(rotateAnim);   //添加旋转动画
                animset.addAnimation(tranAnim);     //添加平移动画
                childView.startAnimation(animset);  //启动动画
                final int pos = i + 1;
                //子按钮单击事件
                childView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mMenuItemClickListener != null)
                            mMenuItemClickListener.onClick(childView, pos);
                        childButtonClickAnim(pos - 1);      //调用字按钮单击动画方法
                        menuStatus();                       //调用菜单状态方法
                    }
                });
            }
            menuStatus();
        }
        private void childButtonClickAnim(int pos) {
            for (int i = 0; i < getChildCount() - 1; i++)
            {
                View childView = getChildAt(i + 1);
                if (i == pos)       //当前单击的子按钮
                {   //启动子按钮变大并消失动画
                    childView.startAnimation(scaleBigAnim(200));
                } else
                {
                    childView.startAnimation(scaleSmallAnim(200));
                }
                childView.setClickable(false);
                childView.setFocusable(false);

            }

        }

        /**
         *没有被单击的子按钮，变小并消失的动画方法
         */
        private Animation scaleSmallAnim(int time)
        {   //设置动画集合
            AnimationSet animationSet = new AnimationSet(true);
            //缩放动画
            ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                    0.5f);
            //透明度动画
            AlphaAnimation alphaAnim = new AlphaAnimation(1f, 0.0f);
            animationSet.addAnimation(scaleAnim);   //添加缩放动画
            animationSet.addAnimation(alphaAnim);   //添加透明度动画
            animationSet.setDuration(time);         //设置动画时间
            animationSet.setFillAfter(true);        //停止最后一帧的位置
            return animationSet;

        }

        /**
         * 单击子按钮，当前子按钮变大并消失动画方法
         */
        private Animation scaleBigAnim(int time)
        {   //设置动画集合
            AnimationSet animationSet = new AnimationSet(true);
            //缩放动画
            ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 4.0f, 1.0f, 4.0f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                    0.5f);
            AlphaAnimation alphaAnim = new AlphaAnimation(1f,0.0f);
            animationSet.addAnimation(scaleAnim);
            animationSet.addAnimation(alphaAnim);
            animationSet.setDuration(time);
            animationSet.setFillAfter(true);
            return animationSet;
        }
        /**
         * 切换菜单状态
         */
        private void menuStatus()
        {
            mCurrentStatus = (mCurrentStatus == Status.CLOSE ? Status.OPEN
                    : Status.CLOSE);
        }
        private void rotatemButton(View v,float start,float end, int time){
            RotateAnimation anim = new RotateAnimation(start, end,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                    0.5f);
            anim.setDuration(time);
            anim.setFillAfter(true);
            v.startAnimation(anim);
        }
        private void buttonLayout(){
            mButton = getChildAt(0);
            mButton.setOnClickListener(new OnClickLisener()){
                @Override
                public void onClick(View v){
                    rotatemButton(v, 0f, 360f,500)


                    shrinkMenu(500);
                }

            }
        };
        int l = 0;
        int t = 0;
        int width = mButton.getMeasuredWidth();

        int height = mButton.getMeasuredHeight();
        l= pm_width/2-width/2;
        t = getMeasuredHeight()-height;
        mButton.layout(l,t,l+width,t+width);
    }
}
