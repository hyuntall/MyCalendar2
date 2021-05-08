package com.android.calendar2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.Calendar;

public class WeekCalendarAdapter extends FragmentStateAdapter {
    private static int NUM_ITEMS=100;
    public WeekCalendarAdapter(@NonNull WeekViewFragment fragmentActivity) {
        super(fragmentActivity);
    }

    int year = Calendar.getInstance().get(Calendar.YEAR);
    int month = Calendar.getInstance().get(Calendar.MONTH);
    int day;

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // year 변수에 저장된 값을 Year에, month변수에 저장된 값을 Month에, 1을 Day에 넣는다.
        int dm = position*7/42;
        int realMonth = (month+dm)%12+1;
        int ym = (month+dm)/12;
        day = position;
        return WeekCalendarFragment.newInstance((year+ym), realMonth, day);
    }

    @Override
    public int getItemCount() {
        return NUM_ITEMS;
    }

}
