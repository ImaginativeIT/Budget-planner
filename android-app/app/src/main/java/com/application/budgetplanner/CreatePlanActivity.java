package com.application.budgetplanner;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.application.budgetplanner.dataModel.PlanItem;

import java.util.ArrayList;

public class CreatePlanActivity extends AppCompatActivity {
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    static ArrayList<PlanItem> incomeItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);

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
