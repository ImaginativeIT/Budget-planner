package com.application.budgetplanner;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.application.budgetplanner.R;

public class CreatePlanIncomeFragment extends Fragment implements View.OnClickListener {
    LinearLayout incomeParentContainer;
    LayoutInflater inflater;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_plan_income, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        incomeParentContainer = getActivity().findViewById(R.id.income_parent_container);
        Button addItemBtn = getActivity().findViewById(R.id.add_item_btn);

        addItemBtn.setOnClickListener(this);
        inflater=(LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        addItemBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                LayoutInflater inflater=(LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                final View rowView=inflater.inflate(R.layout.single_item_create_plan, null);
//                ImageButton btn = rowView.findViewById(R.id.delete_item_btn);
//                btn.setOnClickListener(this);
//                // Add the new row before the add field button.
//                incomeParentContainer.addView(rowView, incomeParentContainer.getChildCount() - 1);
//            }
//        });


    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.delete_item_btn) {
            incomeParentContainer.removeView((View) view.getParent());
        }
        else if(id == R.id.add_item_btn){
            final View rowView=inflater.inflate(R.layout.single_item_create_plan, null);
            ImageButton btn = rowView.findViewById(R.id.delete_item_btn);
            btn.setOnClickListener(this);
            incomeParentContainer.addView(rowView, incomeParentContainer.getChildCount() - 1);
        }
    }
}
