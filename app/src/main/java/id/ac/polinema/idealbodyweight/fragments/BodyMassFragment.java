package id.ac.polinema.idealbodyweight.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.ac.polinema.idealbodyweight.R;
import id.ac.polinema.idealbodyweight.util.BodyMassIndex;

public class BodyMassFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public BodyMassFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_body_mass, container, false);
        final EditText heightText  = view.findViewById(R.id.input_height);
        final EditText weightText = view.findViewById(R.id.input_weight);

        Button calculateButton = view.findViewById(R.id.button_calculate);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    String heightString = heightText.getText().toString();
                    String massString = weightText.getText().toString();
                    if (!TextUtils.isEmpty(heightString) && !TextUtils.isEmpty((massString))) {
                        int height = Integer.parseInt(heightString);
                        int weight = Integer.parseInt(massString);
                        BodyMassIndex bodyMass = new BodyMassIndex(weight, height);
                        mListener.onCalculateBodyMassIndexClicked(bodyMass.getIndex());
                    } else {
                        Toast.makeText(getActivity(), "Please input your mass and input your height", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onCalculateBodyMassIndexClicked(float index);
    }
}
