package com.gjl.day07_shape;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Shape形状
 * 替代图片
 * 纯色，渐变，镭射、
 * 案例：
 * ViewPager引导页
 */
public class MainActivity extends Activity {

    private ArrayList<ImageView> imageViews;
    private ArrayList<ImageView> points;
    private int[] icons = new int[]{
            R.drawable.a, R.drawable.b, R.drawable.d, R.drawable.h
    };
    private ViewPager viewPager;
    private LinearLayout ll_points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //界面  alt+enter
        initViews();

        //模拟数据
        initDatas();
        //设置适配器
        viewPager.setAdapter(new MyAdapter());
    }

    private void initDatas() {
        imageViews = new ArrayList<>();
        points = new ArrayList<>();
        //放入图片
        for (int i = 0; i < 4; i++) {
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setImageResource(R.mipmap.ic_launcher);
            imageViews.add(imageView);
            //放入小圆点
            ImageView point = new ImageView(MainActivity.this);
            point.setImageResource(R.drawable.point_selector);
            //放入线性布局
            //设置小圆点的边距
            point.setLeft(10);
//            point.setLayoutParams();
            ll_points.addView(point);
            points.add(point);
        }
        //默认第一个小圆点选中
        points.get(0).setSelected(true);
        //设置监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //将所有的小圆点初始化
                for (int i=0;i<points.size();i++){
                    points.get(i).setSelected(false);
                }
                //将对应的小圆点选中
                points.get(position).setSelected(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initViews() {
        //提成全局变量 ctrl + alt +f
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ll_points = (LinearLayout) findViewById(R.id.ll_points);

    }

    class MyAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //altet+enter  自动补全
            ImageView imageView = imageViews.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            ImageView imageView = imageViews.get(position);
            container.removeView(imageView);
        }

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
