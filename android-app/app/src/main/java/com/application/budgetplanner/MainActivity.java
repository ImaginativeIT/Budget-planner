package com.application.budgetplanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.application.budgetplanner.adapter.IncomeAdapter;

public class MainActivity extends AppCompatActivity {
    private Button inDropDown, exDropDown, svDropDown;
    private LinearLayout  incomeContainer,inItemContainer,expenseContainer, exItemContainer,savingContainer, svItemContainer;
    private TextView txt;
    boolean inddClick;
    View inItemView, exItemView, svItemView;
    LayoutInflater layoutInflater;
    ListView inList;
    IncomeAdapter incomeAdapter;

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

        incomeAdapter = new IncomeAdapter(MainActivity.this);

        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f

        );

        exItemView = layoutInflater.inflate(R.layout.list_view_container, null);
        svItemView = layoutInflater.inflate(R.layout.list_view_container, null);
        inddClick = false;
        inDropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inItemView == null) {
                    inItemView = layoutInflater.inflate(R.layout.list_view_container, null);
                    inList = inItemView.findViewById(R.id.listView);
                    inList.setAdapter(incomeAdapter);
                    inItemContainer.addView(inItemView);
                }
                else {
                    incomeContainer.setLayoutParams(params);
                    inItemContainer.removeView(inItemView);
                    inItemView = null;
                }
            }
        });

        exDropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inItemView == null) {
                    inItemView = layoutInflater.inflate(R.layout.list_view_container, null);
                    inList = inItemView.findViewById(R.id.listView);
                    inList.setAdapter(incomeAdapter);
                    exItemContainer.addView(inItemView);
                }
                else {
                    expenseContainer.setLayoutParams(params);
                    exItemContainer.removeView(inItemView);
                    inItemView = null;
                }
            }
        });

        svDropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inItemView == null) {
                    inItemView = layoutInflater.inflate(R.layout.list_view_container, null);
                    inList = inItemView.findViewById(R.id.listView);
                    inList.setAdapter(incomeAdapter);
                    svItemContainer.addView(inItemView);
                }
                else {
                    savingContainer.setLayoutParams(params);
                    svItemContainer.removeView(inItemView);
                    inItemView = null;
                }
            }
        });

//        btn = findViewById(R.id.btn);
//        removeBtn = findViewById(R.id.remove_btn);
//        addBtn = findViewById(R.id.addbtn);
//        listContainer = findViewById(R.id.list_container);
//        final View btnview = btn;
//
//
//
//        final View listcontainer = layoutInflater.inflate(R.layout.list_view_container,null);
//
//        removeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                ((ViewGroup) btnview.getParent()).removeView(listcontainer);
//            }
//        });
//
//        addBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
////                View btnview = btn;
////                if ( btnview.getParent() != null){
////                    ((ViewGroup) btnview.getParent()).removeView(btnview);
////                }
//                ((ViewGroup) addBtn.getParent()).addView(listcontainer);
////                listContainer.addView(btnview);
//            }
//        });




    }
}
