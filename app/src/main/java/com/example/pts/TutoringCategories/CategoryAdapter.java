package com.example.pts.TutoringCategories;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pts.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CategoryAdapter extends ArrayAdapter<Category> {

    public CategoryAdapter(Context context, ArrayList<Category> categories) {
        super(context, 0, categories);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Category category = getItem(position);

        TextView nameTextView = convertView.findViewById(R.id.nameTextView);
        TextView descriptionTextView = convertView.findViewById(R.id.descriptionTextView);

        if (category != null) {
            nameTextView.setText(category.getName());
            descriptionTextView.setText(category.getDescription());
        }

        return convertView;
    }
}
