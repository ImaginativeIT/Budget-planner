package com.application.budgetplanner;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreatePlanActivity extends AppCompatActivity {
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);

        Button nextButton = findViewById(R.id.next_btn);
        Button backButton = findViewById(R.id.back_btn);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        CreatePlanIncomeFragment createPlanIncomeFragment = new CreatePlanIncomeFragment();
        fragmentTransaction.addToBackStack("income");
        fragmentTransaction.add(R.id.fragment_container, createPlanIncomeFragment).commit();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction = fragmentManager.beginTransaction();
                CreatePlanExpenseFragment createPlanExpenseFragment = new CreatePlanExpenseFragment();
                fragmentTransaction.addToBackStack("expense");
                fragmentTransaction.replace(R.id.fragment_container, createPlanExpenseFragment).commit();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
