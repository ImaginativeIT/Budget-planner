package com.application.budgetplanner;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.application.budgetplanner.dataModel.PlanItem;

import java.util.ArrayList;

public class CreatePlanSavingFragment extends Fragment implements View.OnClickListener {
    LinearLayout parentContainer;
    LayoutInflater inflater;
    ScrollView scrollView;
    ArrayList<PlanItem> savingItems;
    PlanItem planItem;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.create_plan_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CreatePlanActivity.stepOne.setBackground(getActivity().getDrawable(R.drawable.stepper_background_gray));
        CreatePlanActivity.stepTwo.setBackground(getActivity().getDrawable(R.drawable.stepper_background_gray));
        CreatePlanActivity.stepThree.setBackground(getActivity().getDrawable(R.drawable.stepper_background));


        inflater=(LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        parentContainer = getActivity().findViewById(R.id.items_parent_container);
        if(CreatePlanActivity.savingItems != null){
            for(int i = 0; i< CreatePlanActivity.savingItems.size(); i++){
                PlanItem item = CreatePlanActivity.savingItems.get(i);
                String name = item.getItemName();
                int amount = item.getItemAmount();

                Log.e("Next after", " "+i+ " " + name + " " + amount + " " + item);

                final View rowView=inflater.inflate(R.layout.single_item_create_plan, null);
                ImageButton btn = rowView.findViewById(R.id.delete_item_btn);
                btn.setOnClickListener(this);
                EditText itemNameText = rowView.findViewById(R.id.item_name);
                EditText itemAmountText = rowView.findViewById(R.id.item_amount);
                itemNameText.setText(name);
                itemAmountText.setText(String.valueOf(amount));
                parentContainer.addView(rowView, parentContainer.getChildCount());
            }
        }

        Button addItemBtn = getActivity().findViewById(R.id.add_item_btn);
        Button saveBtn = getActivity().findViewById(R.id.next_btn);
        saveBtn.setText("Save");
        Button backBtn = getActivity().findViewById(R.id.back_btn);
        scrollView = getActivity().findViewById(R.id.scroll_view);

        addItemBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.delete_item_btn) {
            parentContainer.removeView((View) view.getParent());
        }
        else if(id == R.id.add_item_btn){
            final View rowView=inflater.inflate(R.layout.single_item_create_plan, null);
            ImageButton btn = rowView.findViewById(R.id.delete_item_btn);
            btn.setOnClickListener(this);
            parentContainer.addView(rowView, parentContainer.getChildCount());
            scrollView.fullScroll(View.FOCUS_DOWN);

        }
        else if(id == R.id.next_btn){
            if(saveCurrentData()) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                CreatePlanSavingFragment createPlanSavingFragment = new CreatePlanSavingFragment();
                fragmentTransaction.replace(R.id.fragment_container, createPlanSavingFragment).commit();
            }
            else{
                Log.e("failed", "saving data");
            }
        }
        else if(id == R.id.back_btn){
            if(saveCurrentData()) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                CreatePlanExpenseFragment createPlanExpenseFragment = new CreatePlanExpenseFragment();
                fragmentTransaction.replace(R.id.fragment_container, createPlanExpenseFragment).commit();
            }
            else{
                Log.e("failed", "saving data");
            }
        }

    }

    public boolean saveCurrentData(){
        savingItems = new ArrayList<>();
        for(int i = 0; i<parentContainer.getChildCount(); i++) {
            planItem = new PlanItem();
            View childView = parentContainer.getChildAt(i);
            EditText itemNameText = childView.findViewById(R.id.item_name);
            EditText itemAmountText = childView.findViewById(R.id.item_amount);
            String name = itemNameText.getText().toString();
            String amount = itemAmountText.getText().toString();

            if (TextUtils.isEmpty(name)) {
                itemNameText.setError("Required");
                return false;
            } else if (TextUtils.isEmpty(amount)) {
                itemAmountText.setError("Required");
                return false;
            } else {
                planItem.setItemName(name);
                planItem.setItemAmount(Integer.parseInt(amount));
                savingItems.add(planItem);
                Log.e("Next", " " + name + " " + amount + " " + planItem);
            }
        }
        CreatePlanActivity.savingItems = savingItems;
        return true;
    }
}
