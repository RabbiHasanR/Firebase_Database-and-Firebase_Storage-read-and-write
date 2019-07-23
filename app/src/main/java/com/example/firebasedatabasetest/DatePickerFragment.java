package com.example.firebasedatabasetest;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener{
    public interface DatePickerFragmentListener {
        public void onDateSet(Date date);
    }
    private Calendar calendar;
    private Date date;
    private DatePickerFragmentListener datePickerListener;

    public DatePickerFragment(){
        calendar = Calendar.getInstance();
    }
    public static DatePickerFragment newInstance(DatePickerFragmentListener listener) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setDatePickerListener(listener);
        return fragment;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,day);
        date=calendar.getTime();
        Log.d("Date1:", String.valueOf(date));
        // Here we call the listener and pass the date back to it.
        notifyDatePickerListener(date);

    }
    public DatePickerFragmentListener getDatePickerListener() {
        return this.datePickerListener;
    }

    public void setDatePickerListener(DatePickerFragmentListener listener) {
        this.datePickerListener = listener;
    }

    protected void notifyDatePickerListener(Date date) {
        if(this.datePickerListener != null) {
            this.datePickerListener.onDateSet(date);
        }
    }
}
