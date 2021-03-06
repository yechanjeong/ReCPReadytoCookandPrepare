package com.davidjeong.recpreadytocookandprepare;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public abstract class CheckBoxesFragment extends Fragment {
    private static final String KEY_CHECKED_BOXES = "key_checked_boxes";
    private CheckBox[] mCheckBoxes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checkboxes, container, false);
        int index = getArguments().getInt(ViewPagerFragment.KEY_RECIPE_INDEX);

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.checkBoxesLayout);

        String[] items = getItems(index);
        

        mCheckBoxes = new CheckBox[items.length];
        boolean[] checkedBoxes = new boolean[mCheckBoxes.length];
        if (savedInstanceState != null && savedInstanceState.getBooleanArray(KEY_CHECKED_BOXES) != null) {
            checkedBoxes = savedInstanceState.getBooleanArray(KEY_CHECKED_BOXES);
        }


        setUpCheckBoxes(linearLayout, items, checkedBoxes);

        return view;
    }

    public abstract String[] getItems(int index);

    private void setUpCheckBoxes(ViewGroup container, String[] items, boolean[] checkedBoxes) {
        int i = 0;

        for (String item : items) {
            mCheckBoxes[i] = new CheckBox(getActivity());
            mCheckBoxes[i].setPadding(8, 16, 8, 16);
            mCheckBoxes[i].setTextSize(20f);
            mCheckBoxes[i].setText(item);
            container.addView(mCheckBoxes[i]);

            if (checkedBoxes[i]) {
                mCheckBoxes[i].toggle();
            }

            i++;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        boolean[] stateOfCheckBoxes = new boolean[mCheckBoxes.length];

        int i = 0;

        for (CheckBox checkBox : mCheckBoxes) {
            stateOfCheckBoxes[i] = checkBox.isChecked();
            i++;
        }

        outState.putBooleanArray(KEY_CHECKED_BOXES, stateOfCheckBoxes);

        super.onSaveInstanceState(outState);
    }
}
