package com.application.budgetplanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.application.budgetplanner.adapter.IncomeAdapter;

public class MainActivity extends AppCompatActivity {

    ImageButton inDropDown, exDropDown, svDropDown;
    ScrollView inScrollContainer;
    private LinearLayout  incomeContainer,inItemContainer,expenseContainer, exItemContainer,savingContainer, svItemContainer;
    private TextView txt;
    private static boolean inClick, exClick, svClick;
    View inItemView, exItemView, svItemView;
    LayoutInflater layoutInflater;
    ListView inList, exList, svList;
    IncomeAdapter incomeAdapter, expenseAdapter, savingAdapter;
    LinearLayout.LayoutParams maxParam, mediumParam, minParam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        incomeContainer = findViewById(R.id.income_container);
        inItemContainer = findViewById(R.id.income_item_container);

        expenseContainer = findViewById(R.id.expense_container);
        exItemContainer = findViewById(R.id.expense_item_container);

        savingContainer = findViewById(R.id.saving_container);
        svItemContainer = findViewById(R.id.saving_item_container);

        inDropDown = findViewById(R.id.income_dropDown);
        exDropDown = findViewById(R.id.expense_dropDown);
        svDropDown = findViewById(R.id.saving_dropDown);

        incomeAdapter = new IncomeAdapter(MainActivity.this, R.layout.single_item_income);
        expenseAdapter = new IncomeAdapter(MainActivity.this, R.layout.single_item_expense);
        savingAdapter = new IncomeAdapter(MainActivity.this, R.layout.single_item_saving);

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
