package com.example.pts.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import com.example.pts.R;


public class TutorAdapter extends ArrayAdapter<Tutor> {

    public TutorAdapter(Context context, ArrayList<Tutor> tutors) {
        super(context, 0, tutors);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_tutors, parent, false);
        }

        Tutor tutor = getItem(position);

        TextView textViewName = convertView.findViewById(R.id.textViewName);
        TextView textViewCategory = convertView.findViewById(R.id.textViewCategory);
        TextView textViewLocation = convertView.findViewById(R.id.textViewLocation);
        TextView textViewTime = convertView.findViewById(R.id.textViewTime);
        TextView textViewPrice = convertView.findViewById(R.id.textViewPrice);

        if (tutor != null) {
            textViewName.setText(tutor.getName());
            textViewCategory.setText(tutor.getCategory());
            textViewLocation.setText(tutor.getLocation() + "\t" + tutor.getDistance() + "mi");
            textViewTime.setText(tutor.getTime());
            textViewPrice.setText(tutor.getPrice() + " $/hr");
        }

        return convertView;
    }
}
