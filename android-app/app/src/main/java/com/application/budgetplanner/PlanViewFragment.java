package com.application.budgetplanner;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.application.budgetplanner.adapter.IncomeAdapter;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class PlanViewFragment extends Fragment {

    ImageButton inDropDown, exDropDown, svDropDown;
    private LinearLayout incomeContainer,inItemContainer,expenseContainer, exItemContainer,savingContainer, svItemContainer;
    private static boolean inClick, exClick, svClick;
    View inItemView, exItemView, svItemView;
    LayoutInflater layoutInflater;
    ListView inList, exList, svList;
    IncomeAdapter incomeAdapter, expenseAdapter, savingAdapter;
    LinearLayout.LayoutParams maxParam, mediumParam, minParam;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.plan_view_fragment, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        layoutInflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);

        incomeContainer = getActivity().findViewById(R.id.income_container);
        inItemContainer = getActivity().findViewById(R.id.income_item_container);

        expenseContainer = getActivity().findViewById(R.id.expense_container);
        exItemContainer = getActivity().findViewById(R.id.expense_item_container);

        savingContainer = getActivity().findViewById(R.id.saving_container);
        svItemContainer = getActivity().findViewById(R.id.saving_item_container);

        inDropDown = getActivity().findViewById(R.id.income_dropDown);
        exDropDown = getActivity().findViewById(R.id.expense_dropDown);
        svDropDown = getActivity().findViewById(R.id.saving_dropDown);

        incomeAdapter = new IncomeAdapter(getActivity(), R.layout.single_item_income);
        expenseAdapter = new IncomeAdapter(getActivity(), R.layout.single_item_expense);
        savingAdapter = new IncomeAdapter(getActivity(), R.layout.single_item_saving);

        inItemView = layoutInflater.inflate(R.layout.list_view_container, null);
        inList = inItemView.findViewById(R.id.listView);
        inList.setAdapter(incomeAdapter);
        inItemContainer.addView(inItemView);

        exItemView = layoutInflater.inflate(R.layout.list_view_container, null);
        exList = exItemView.findViewById(R.id.listView);
        exList.setAdapter(expenseAdapter);
        exItemContainer.addView(exItemView);

        svItemView = layoutInflater.inflate(R.layout.list_view_container, null);
        svList = svItemView.findViewById(R.id.listView);
        svList.setAdapter(savingAdapter);
        svItemContainer.addView(svItemView);


        maxParam = getLayoutParams(13);
        mediumParam = getLayoutParams(5);
        minParam = getLayoutParams(1);

        inClick = false;
        svClick = false;
        exClick = false;

        inDropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("inClick", " " + inClick);
                viewContainerHandler(1);
                if (!inClick) {
                    incomeContainer.setLayoutParams(maxParam);
                    expenseContainer.setLayoutParams(minParam);
                    savingContainer.setLayoutParams(minParam);

                    inClick = true;
                }
                else {
                    incomeContainer.setLayoutParams(mediumParam);
                    expenseContainer.setLayoutParams(mediumParam);
                    savingContainer.setLayoutParams(mediumParam);
                    exItemContainer.addView(exItemView);
                    svItemContainer.addView(svItemView);

                    inClick  = false;

                }
            }
        });


        exDropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewContainerHandler(2);
                if (!exClick) {
                    incomeContainer.setLayoutParams(minParam);
                    expenseContainer.setLayoutParams(maxParam);
                    savingContainer.setLayoutParams(minParam);

                    exClick = true;
                }
                else {

                    incomeContainer.setLayoutParams(mediumParam);
                    expenseContainer.setLayoutParams(mediumParam);
                    savingContainer.setLayoutParams(mediumParam);
                    inItemContainer.addView(inItemView);
                    svItemContainer.addView(svItemView);
                    exClick = false;
                }
            }
        });

        svDropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewContainerHandler(3);
                if (!svClick) {
                    incomeContainer.setLayoutParams(minParam);
                    expenseContainer.setLayoutParams(minParam);
                    savingContainer.setLayoutParams(maxParam);

                    svClick = true;
                }
                else {
                    incomeContainer.setLayoutParams(mediumParam);
                    expenseContainer.setLayoutParams(mediumParam);
                    savingContainer.setLayoutParams(mediumParam);
                    inItemContainer.addView(inItemView);
                    exItemContainer.addView(exItemView);
                    svClick = false;
                }
            }
        });
    }

    private void viewContainerHandler(int containerCode){
        inItemContainer.removeView(inItemView);
        exItemContainer.removeView(exItemView);
        svItemContainer.removeView(svItemView);
        switch (containerCode){

            case 1:
                exClick = false;
                svClick = false;
                inItemContainer.addView(inItemView);
                break;
            case 2:
                inClick = false;
                svClick = false;
                exItemContainer.addView(exItemView);
                break;
            case 3:
                inClick = false;
                exClick = false;
                svItemContainer.addView(svItemView);
                break;
        }

    }

    private LinearLayout.LayoutParams getLayoutParams(float layout_weight){

        return new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                layout_weight
        );
    }
}
