package com.marvinlabs.widget.floatinglabel.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.marvinlabs.widget.floatinglabel.itempicker.FloatingLabelItemPicker;
import com.marvinlabs.widget.floatinglabel.itempicker.ItemPickerListener;
import com.marvinlabs.widget.floatinglabel.itempicker.StringPickerDialogFragment;
import com.marvinlabs.widget.slideshow.demo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


public class ItemWidgetsFragment extends Fragment implements ItemPickerListener<String>, FloatingLabelItemPicker.OnItemPickerEventListener<String> {

    FloatingLabelItemPicker<String> picker1;
    FloatingLabelItemPicker<String> picker2;
    FloatingLabelItemPicker<String> picker3;
    FloatingLabelItemPicker<String> picker4;
    FloatingLabelItemPicker<String> picker5;

    Handler handler = new Handler();

    public static ItemWidgetsFragment newInstance() {
        return new ItemWidgetsFragment();
    }

    @Override
    @SuppressWarnings("unchecked")
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_item_widgets, null, false);


        // Spinners
        picker1 = (FloatingLabelItemPicker<String>) root.findViewById(R.id.picker1);
        picker1.setItemPickerListener(this);
        picker1.setAvailableItems(new ArrayList<String>(Arrays.asList("Item 1.1", "Item 1.2", "Item 1.3", "Item 1.4", "Item 1.5", "Item 1.6", "Item 1.7", "Item 1.8")));
        picker1.setWidgetListener(new FloatingLabelItemPicker.OnWidgetEventListener<String>() {
            @Override
            public void onShowItemPickerDialog(FloatingLabelItemPicker<String> source) {
                StringPickerDialogFragment itemPicker1 = StringPickerDialogFragment.newInstance(
                        source.getId(),
                        "Picker 1",
                        "OK", "Cancel",
                        true,
                        source.getSelectedIndices(),
                        new ArrayList<String>(source.getAvailableItems()));
                itemPicker1.setTargetFragment(ItemWidgetsFragment.this, 0);
                itemPicker1.show(getChildFragmentManager(), "ItemPicker1");
            }
        });

        picker2 = (FloatingLabelItemPicker<String>) root.findViewById(R.id.picker2);
        picker2.setItemPickerListener(this);
        picker2.setAvailableItems(new ArrayList<String>(Arrays.asList("Item 2.1", "Item 2.2", "Item 2.3", "Item 2.4")));
        picker2.setWidgetListener(new FloatingLabelItemPicker.OnWidgetEventListener<String>() {
            @Override
            public void onShowItemPickerDialog(FloatingLabelItemPicker source) {
                StringPickerDialogFragment itemPicker2 = StringPickerDialogFragment.newInstance(
                        source.getId(),
                        "Picker 2",
                        "OK", "Cancel",
                        false,
                        source.getSelectedIndices(),
                        new ArrayList<String>(source.getAvailableItems()));
                itemPicker2.setTargetFragment(ItemWidgetsFragment.this, 0);
                itemPicker2.show(getChildFragmentManager(), "ItemPicker2");
            }
        });

        picker3 = (FloatingLabelItemPicker<String>) root.findViewById(R.id.picker3);
        picker3.setItemPickerListener(this);
        picker3.setAvailableItems(new ArrayList<String>(Arrays.asList("Item 1.1", "Item 1.2", "Item 1.3", "Item 1.4", "Item 1.5", "Item 1.6", "Item 1.7", "Item 1.8")));
        picker3.setWidgetListener(new FloatingLabelItemPicker.OnWidgetEventListener<String>() {
            @Override
            public void onShowItemPickerDialog(FloatingLabelItemPicker<String> source) {
                StringPickerDialogFragment itemPicker3 = StringPickerDialogFragment.newInstance(
                        source.getId(),
                        "Picker 3",
                        "OK", "Cancel",
                        true,
                        source.getSelectedIndices(),
                        new ArrayList<String>(source.getAvailableItems()));
                itemPicker3.setTargetFragment(ItemWidgetsFragment.this, 0);
                itemPicker3.show(getChildFragmentManager(), "ItemPicker3");
            }
        });

        picker4 = (FloatingLabelItemPicker<String>) root.findViewById(R.id.picker4);
        picker4.setItemPickerListener(this);
        picker4.setAvailableItems(new ArrayList<String>(Arrays.asList("Item 2.1", "Item 2.2", "Item 2.3", "Item 2.4")));
        picker4.setWidgetListener(new FloatingLabelItemPicker.OnWidgetEventListener<String>() {
            @Override
            public void onShowItemPickerDialog(FloatingLabelItemPicker source) {
                StringPickerDialogFragment itemPicker4 = StringPickerDialogFragment.newInstance(
                        source.getId(),
                        "Picker 4",
                        "OK", "Cancel",
                        false,
                        source.getSelectedIndices(),
                        new ArrayList<String>(source.getAvailableItems()));
                itemPicker4.setTargetFragment(ItemWidgetsFragment.this, 0);
                itemPicker4.show(getChildFragmentManager(), "ItemPicker4");
            }
        });

        // Picker 5 is initally empty and is populated 10 seconds later
        picker5 = (FloatingLabelItemPicker<String>) root.findViewById(R.id.picker5);
        picker5.setItemPickerListener(this);
        picker5.setAvailableItems(new ArrayList<String>());
        picker5.setWidgetListener(new FloatingLabelItemPicker.OnWidgetEventListener<String>() {
            @Override
            public void onShowItemPickerDialog(FloatingLabelItemPicker source) {
                if (source.getAvailableItems().isEmpty()) {
                    Toast.makeText(getActivity(), "Wait a little bit more", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringPickerDialogFragment itemPicker5 = StringPickerDialogFragment.newInstance(
                        source.getId(),
                        "Picker 4",
                        "OK", "Cancel",
                        false,
                        source.getSelectedIndices(),
                        new ArrayList<String>(source.getAvailableItems()));
                itemPicker5.setTargetFragment(ItemWidgetsFragment.this, 0);
                itemPicker5.show(getChildFragmentManager(), "ItemPicker5");
            }
        });

        handler.postDelayed(new Runnable() {
            long startTime = System.currentTimeMillis();

            @Override
            public void run() {
                long elapsedSec = (long) ((System.currentTimeMillis() - startTime) / 1000);
                if (elapsedSec < 10) {
                    picker5.setLabelText(String.format("This is empty, it will be enabled in %d seconds", 10 - elapsedSec));
                    handler.postDelayed(this, 1000);
                } else {
                    picker5.setLabelText("You can pick an item now");
                    picker5.setAvailableItems(new ArrayList<String>(Arrays.asList("Item 5.1", "Item 5.2", "Item 5.3", "Item 5.4")));
                }
            }
        }, 1000);

        return root;
    }

    // Implementation of the OnItemPickerEventListener interface

    @Override
    public void onSelectionChanged(FloatingLabelItemPicker<String> source, Collection<String> selectedItems) {
        Toast.makeText(getActivity(), source.getItemPrinter().printCollection(selectedItems), Toast.LENGTH_SHORT).show();
    }

    // Implementation of the InstantPickerListener interface

    @Override
    public void onCancelled(int pickerId) {
    }

    @Override
    public void onItemsSelected(int pickerId, int[] selectedIndices) {
        if (pickerId == R.id.picker1) {
            picker1.setSelectedIndices(selectedIndices);
        } else if (pickerId == R.id.picker2) {
            picker2.setSelectedIndices(selectedIndices);
        } else if (pickerId == R.id.picker3) {
            picker3.setSelectedIndices(selectedIndices);
        } else if (pickerId == R.id.picker4) {
            picker4.setSelectedIndices(selectedIndices);
        }
    }
}
