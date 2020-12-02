import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.MyViewModel;
import com.example.myapplication.R;

import java.util.Objects;

public class InputLineFragment extends Fragment  implements AdapterView.OnItemSelectedListener{

    MyViewModel model;
    EditText valueFrom;
    EditText valueTo;
    Spinner spinner;
    Spinner spinner2;
    Spinner spinner_mesuares;
    Button buttonChange;
    ClipboardManager clipboardManager;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewHierarchy = inflater.inflate(R.layout.input_line_fragment, container, false);
        spinner = viewHierarchy.findViewById(R.id.spinner_firstConversion);
        spinner2 = viewHierarchy.findViewById(R.id.spinner_secondConversion);
        spinner_mesuares = viewHierarchy.findViewById(R.id.spinner_mesuares);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Objects.requireNonNull(getActivity()), R.array.mesuares, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_mesuares.setAdapter(adapter);
        spinner_mesuares.setOnItemSelectedListener(this);
        spinner.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        model = ViewModelProviders.of(getActivity()).get(MyViewModel.class);
        viewHierarchy.findViewById(R.id.change_field_but).setOnClickListener(item -> model.changeFields());
        clipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        viewHierarchy.findViewById(R.id.fcopy).setOnClickListener(item -> model.copyInBuffer(1,clipboardManager));
        viewHierarchy.findViewById(R.id.scopy).setOnClickListener(item -> model.copyInBuffer(2,clipboardManager));
        return viewHierarchy;
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView == spinner_mesuares)
        {
            String measures = adapterView.getItemAtPosition(i).toString();
            Toast.makeText(adapterView.getContext(),measures, Toast.LENGTH_SHORT).show();
            int mesFrom = 0;
            int mesTo = 0;
            switch (measures) {
                case "CURRENCY":
                    mesFrom = R.array.currencies;
                    mesTo = R.array.currencies2;
                    break;
                case "WEIGHT":
                    mesFrom = R.array.Weight;
                    mesTo = R.array.Weight2;
                    break;
                case "DISTANCE":
                    mesFrom = R.array.Distance;
                    mesTo = R.array.Distance2;
                    break;
            }
            ArrayAdapter<CharSequence> adapterFrom = ArrayAdapter.createFromResource(Objects.requireNonNull(getActivity()), mesFrom, android.R.layout.simple_spinner_item);
            adapterFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapterFrom);
            spinner.setOnItemSelectedListener(this);
            ArrayAdapter<CharSequence> adapterTo= ArrayAdapter.createFromResource(getActivity(), mesTo, android.R.layout.simple_spinner_item);
            adapterTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(adapterTo);
            spinner2.setOnItemSelectedListener(this);
        }
        else
        {
            String temp = spinner.getSelectedItem().toString() + spinner2.getSelectedItem().toString();
            int strId = getResources().getIdentifier(temp, "string", Objects.requireNonNull(getActivity()).getPackageName());
            model.changeData(Float.parseFloat(getString(strId)));
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        valueFrom = view.findViewById(R.id.et_firstConversion);
        valueTo = view.findViewById(R.id.et_secondConversion);
        model.getValueFrom().observe(requireActivity(), value -> valueFrom.setText(value));
        model.getValueTo().observe(requireActivity(), value -> valueTo.setText(value));
    }

}