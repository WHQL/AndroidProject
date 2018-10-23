package com.example.administrator.satellitemenu.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.text.AttributedCharacterIterator;

import javax.net.ssl.SSLEngineResult;

public class SatelliteMenu extends ViewGroup {
    private int pm_width;
    private int pm_height;

    private int mRadius;
    private Status mCurrentStatus = SSLEngineResult.Status.CLOSED;
    private View mButton;
    private OnSatelliteMenuClickListener mMenuItemClickListener;

    public enum Status {
        OPRN, ClOSE
    }

    public interface OnSatelliteMenuItemClickListener {
        void onClick(View view, int pos);
    }

    public SattlliteMenu(Context context) {
        this(context, null);
    }

    public SatelliteMenu(Context context, AttributedCharacterIterator.AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SatelliteMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
    pm_width =wm.getDefaultDisplay().

    getGetHeight();

    pm_height =wm.getDefaultDisplay().

    getHeight();

    TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ArcMenu, deStyleAttr, 0);
    mRadius =(int)a.getDimension(R.styleble.ArcMenu_radius,TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,100,

    getResource().getDisplay));

      Log.i("tag",mRadius+"");

      a.recycle();
}
@Override
    protected void onLayout(boolean changed, int l, int t, int r, int b){
    if(changed){
        buttonLayout();
        childLayout()
    }
}
privaete void chuildLayout(){
    int count = getChildCount();
    for(int i = 0; i<count - 1;i++){
        View child = getChildAt(i + 1);
        child.setVisibility(View.GONE);
        int childWidth = child.getMeasureWidth();
        int childHeight = child.getMeasuredGHeigth();
        int c_x = pm_width / 2-childWidth/2'

        int childLeft  = (int) (mRadius * Math.PI / (count - 2)*i));
        int childTop  = (int) (mRadius * Math.PI / (count - 2)*i));
        childTop = getMeasuredHeight() - childHeight;
        }else if{
        childLeft=c_x;
        childTop=getMeasureHeight()-childHeight-mRadius;
        }else if(i=3){
        childLeft = c_x + childLeft;
        }
        else if(i==4){
        childLeft = c_x + mEadius;
        child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeigth);
        }
        }
public void shrinkMenu(int time){
    int count = getChildCount();
    for(int i = 0; i<count-1;i++){
        final View childView = getChildAt(i+1);
        childView.setVisibility(View.VISIBLE);

        int clx = (int) (mRadius * Matg.sin(Math.sin(Math.PI / (count - 2)*i));
        int cly = (int) (mRadius * Math.cos(Math,PI / (count - 2) * 2));
         if(i == 0){
             clx = mRadius;
             cty = 0;

         }  else if(i==3){
             clx=(int) (mRadius * Math.PI / (count - 2)*i);
             cty= -cty
        }
        else if(i == 4){
             clx=-mRadius;
             cty = 0;

        }
        AnimationSet animset = new AnimationSet(true);
         Animation tranAnim = null
        }
        if(mCurrentStatus == Status.CLOSE)
        {
            tranAnim = new TranslateAnimation(clx,0, cty,0);
            childView.setClickable(true);
            childView.setFocusable(true);
        }else
        {
            tranAnim = new TranslateAnimation(0, clx,0,cty);
            childView.setClickable(false);
            childView.setFoucusable(false);
        }
        tranAnim.setDuration(time);
        tranAnim.setFillAfter(true);
        tranAnim.setStartOffset(i *100) / count);
        tranAnim.setAnimationListener(new Animation.AnimationLisener)()
        {
            @Override
            public void o你AnimationStart(Animation animation){

        }
        @Override
            public void onAnimationRepeat(Animation animation){

        }
        @Override
        public void onAnimationEnd(Animation animation){
                if(mCurrentStatus == Status.ClOSE){
                    childView.setVisibility(View.Gone);
        }
        }
        };
        RotateAnimation rotateAnim = new RotateAnimation(0, 720,
                Animation.RRLATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnim.setDuration(time);
        animaset.addAnimation(rotateAnim);
        animaset.addAnimation(tranAnim);
        childView.startAnimatio(animset);
        final int pos = i+ i;
        childView.setOnclickLiseterner(new onClickListen()){
            @Override
            public void onClick(View v){
                if(mMenuItemClickListener != null)
                    mMenuItemClickListener.onClick(childView, pos);
                childButtonClickAnim(pos-1);
                menuStatus();
        }
        }
        menuStatus();
        }
        private void childButtonCli查看Anum（int pos){
            for(int i=0;i<getChildCount()-1;i++){
                View childView = getChildA听（i+1）;
                if(i == pos)
        {
        childVIew.startAnimation(scaleBigAnim(200));
        }else{
                    childView.startAnimation(scaleSmallAnim(200));
        }
        childView.setClickable(false);
        childView.setFocusable(false);

        }

        }

        private Animation scaleSmallAnim(int time){
        AnimationSet scaleSmallAnim(int time){
            AnimationSet animalSet = new AnimationSet(true);
            ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 0.0f, 1
        0f,0.0f, 0.5f)

        };
        AlphaAnimation alphaAnim = new AlphaAnimation(1f,.0.0f);
        animationSet.addAnimation(scaleAnim);
        animationSet.addAnimation(aplhaAnim);
        animationSet.setDuration(time);
        animationSet.setFillAfter(true);
        return animationSet;
        }
        private void meunStatus(){
    mCurrentStatus = {
            mCurrentStatus == Status.CLOSE ? Status.OPEN:Status.CLOSE);

        }
        private void rotatemButton(View v,float start,float end, int time){
        RotateAnimation anim = new RotateAnimation(start, end,Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF,0.5f);
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
    int width = mButton.getMeasureWidth();
    int heidth = mButton.getMeasureHeigth();
    1 = pm_width = mButton.getMeasureedWidth();
    int height = mButton.getMeasuredHeight();
    1= pm_width/2-width/2;
    t = getMeasuredHeight()-height;
    mButton.layout(1,t,1+width,t+width);
        }
}