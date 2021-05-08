package com.android.calendar2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.w3c.dom.Text;

import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;

import static android.content.Context.WINDOW_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MonthCalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MonthCalendarFragment extends Fragment {
    Calendar mCal; // 캘린더 선언
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    int year;
    int month;
    // TODO: Rename and change types of parameters
    private int mParam1;
    private int mParam2;
    public MonthCalendarFragment() {
        // Required empty public constructor
    }

    public static MonthCalendarFragment newInstance(int year, int month) {
        MonthCalendarFragment fragment = new MonthCalendarFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, year);
        args.putInt(ARG_PARAM2, month);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }
        int orientation = getResources().getConfiguration().orientation;
        View rootView = getLayoutInflater().inflate(R.layout.fragment_month_calendar, null, false);
        View item = getLayoutInflater().inflate(R.layout.item_month, null, false);
        GridView gridview = (GridView)rootView.findViewById(R.id.gridview);
        TextView tv = (TextView)item.findViewById(R.id.item_gridview);
        tv.getLayoutParams().height = 800;
        ViewGroup.LayoutParams pr = new ViewGroup.LayoutParams(123, 800);
        tv.setLayoutParams(pr);
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            //System.out.println("세로입니당"+tv.getLayoutParams().height);

        }
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            //System.out.println("가로입니당"+tv.getHeight());
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_month_calendar, container, false);
        ArrayList<String> dayList = new ArrayList<String>();
        mCal = Calendar.getInstance();
        // year 변수에 저장된 값을 Year에, month변수에 저장된 값을 Month에, 1을 Day에 넣는다.
        mCal.set(Integer.parseInt(String.valueOf(mParam1)), Integer.parseInt(String.valueOf(mParam2)) - 1, 1);
        int dayNum = mCal.get(Calendar.DAY_OF_WEEK);
        for (int i = 1; i < dayNum; i++) {
            dayList.add("");
        }
        int dayMax = mCal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //해당 달의 최대 일 수를 구하기 위해 .getActualMaximum(Calendar.DAY_OF_MONTH) 함수를 사용한다.
        for (int i = 1; i < dayMax+1; i++){ // 최대 일 수만큼 dayList에 요소를 추가한다.
            dayList.add(String.valueOf(i));
        }
        for (int i = dayList.size(); i < 42; i++){
            dayList.add("");
        }
        // id를 바탕으로 화면 레이아웃에 정의된 GridView 객체 로딩
        GridView gridview = rootView.findViewById(R.id.gridview);
        View v = inflater.inflate(R.layout.item_month, container, false);
        TextView tv = (TextView)v.findViewById(R.id.item_gridview);
        ArrayAdapter<String> adapt
                = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                dayList){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Cast the grid view current item as a text view
                TextView tv_cell = (TextView) super.getView(position,convertView,parent);
                //TextView tv = (TextView)parent.findViewById(R.id.item_gridview);
                // Set the grid view item/cell/row height
                tv_cell.setHeight(300); // In pixels
                tv_cell.setBackgroundColor(Color.WHITE);
                tv_cell.setGravity(Gravity.CENTER_HORIZONTAL);
                // Another way to change grid view row height
                tv_cell.getLayoutParams().height = gridview.getHeight()/6;

                // Return the modified item
                return tv_cell;
            }
        };
        // 기존에 simple_list_item_1 리소스를 사용하였으나 텍스트 정렬을 위해
        // item_month 레이아웃을 만들어 그 내부에 만든 item_gridview를 사용하였다.
        // 어댑터를 GridView 객체에 연결

        gridview.setAdapter(adapt);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                System.out.println("세로입니당"+gridview.getHeight()/6);
                if(position >= dayNum-1 && position < dayMax+dayNum-1) { //그리드뷰의 n번째 요소의 내용이 1일 이상일 때 메시지를 출력한다.
                    Toast.makeText(getActivity(),
                            mParam1 + "." + mParam2 + "." + (position -(dayNum-2)),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rootView;
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LayoutInflater inflater2 = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater2.inflate(R.layout.fragment_month_calendar, null);
        View item = getLayoutInflater().inflate(R.layout.item_month, null, false);
        GridView gridview = (GridView)view.findViewById(R.id.gridview);
        System.out.println(view.getHeight());
        TextView tv = (TextView)item.findViewById(R.id.item_gridview);
        switch (newConfig.orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                System.out.println(gridview.getWidth()+"가로입니다"+gridview.getHeight());
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                System.out.println(gridview.getWidth()+"세로입니다"+gridview.getHeight());
                break;
        }
    }
}