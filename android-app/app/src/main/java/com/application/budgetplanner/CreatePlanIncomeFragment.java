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

public class CreatePlanIncomeFragment extends Fragment implements View.OnClickListener {
    LinearLayout parentContainer;
    LayoutInflater inflater;
    ScrollView scrollView;
    ArrayList<PlanItem> incomeItems;
    PlanItem planItem;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.create_plan_fragment, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        CreatePlanActivity.stepOne.setBackground(getActivity().getDrawable(R.drawable.stepper_background));
        CreatePlanActivity.stepTwo.setBackground(getActivity().getDrawable(R.drawable.stepper_background_gray));
        CreatePlanActivity.stepThree.setBackground(getActivity().getDrawable(R.drawable.stepper_background_gray));

        inflater=(LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        parentContainer = getActivity().findViewById(R.id.items_parent_container);
        if(CreatePlanActivity.incomeItems != null){
            for(int i = 0; i< CreatePlanActivity.incomeItems.size(); i++){
                PlanItem item = CreatePlanActivity.incomeItems.get(i);
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
        Button nextBtn = getActivity().findViewById(R.id.next_btn);
        Button backBtn = getActivity().findViewById(R.id.back_btn);
        backBtn.setVisibility(View.INVISIBLE);
        scrollView = getActivity().findViewById(R.id.scroll_view);

        addItemBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);


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
            Log.e("Count child" , " " + parentContainer.getChildCount());
            parentContainer.addView(rowView, parentContainer.getChildCount());
            scrollView.fullScroll(View.FOCUS_DOWN);

        }
        else if(id == R.id.next_btn){
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
        incomeItems = new ArrayList<>();
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
                incomeItems.add(planItem);
                Log.e("Next", " " + name + " " + amount + " " + planItem);
            }
        }
        CreatePlanActivity.incomeItems = incomeItems;
        return true;
    }
}
