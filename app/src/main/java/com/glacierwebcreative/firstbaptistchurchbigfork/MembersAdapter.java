package com.glacierwebcreative.firstbaptistchurchbigfork;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MembersAdapter extends ArrayAdapter<Member> {


    // Constructor
    public MembersAdapter (Activity context, ArrayList<Member> membersList) {
        super(context, 0, membersList);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView;

        listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.one_member_view, parent,false);
        }

        // Get the races object located at this position in the list
        Member currentMember = getItem(position);

        TextView firstNameHusbandView = listItemView.findViewById(R.id.husband_first_name);
        firstNameHusbandView.setText(currentMember.getmFirstNameHusband());

        TextView firstNameWifeView = listItemView.findViewById(R.id.wife_first_name);
        firstNameWifeView.setText(currentMember.getmFirstNameWife());

        TextView lastNameView = listItemView.findViewById(R.id.last_name);
        lastNameView.setText(currentMember.getmLastName());

        return listItemView;
    }
}
