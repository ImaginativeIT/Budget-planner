package com.application.budgetplanner;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.application.budgetplanner.dataModel.PlanItem;

import java.util.ArrayList;

public class CreatePlanActivity extends AppCompatActivity {
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    static TextView stepOne, stepTwo, stepThree;
    static ArrayList<PlanItem> incomeItems;
    static ArrayList<PlanItem> expenseItems;
    static ArrayList<PlanItem> savingItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);

        stepOne = findViewById(R.id.step_one_text);
        stepTwo = findViewById(R.id.step_two_text);
        stepThree = findViewById(R.id.step_three_text);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        CreatePlanIncomeFragment createPlanIncomeFragment = new CreatePlanIncomeFragment();
        fragmentTransaction.add(R.id.fragment_container, createPlanIncomeFragment).commit();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
