package com.application.budgetplanner;

import android.app.Application;

import com.application.budgetplanner.dataModel.PlanItem;

import java.util.ArrayList;

public class Environment extends Application {
    private ArrayList<PlanItem> incomePlan;

    public ArrayList<PlanItem> getIncomePlan() {
        return incomePlan;
    }

    public void setIncomePlan(ArrayList<PlanItem> incomePlan) {
        this.incomePlan = incomePlan;
    }
}
