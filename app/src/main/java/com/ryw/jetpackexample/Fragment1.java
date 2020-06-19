package com.ryw.jetpackexample;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 *
 */
public class Fragment1 extends Fragment {

    private TextView tvResp;
    private MyViewModel myViewModel;


    public Fragment1() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myViewModel = new ViewModelProvider.AndroidViewModelFactory(
                requireActivity().getApplication()).create(MyViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        Button btn = view.findViewById(R.id.button);
        btn.setOnClickListener(v -> {
            // 跳转
            NavController controller = Navigation.findNavController(v);
            controller.navigate(R.id.action_fragment1_to_fragment2);
        });
        Button btnInsert = view.findViewById(R.id.btn_insert);
        btnInsert.setOnClickListener(onInsert);
        tvResp = view.findViewById(R.id.tv_resp);
        myViewModel.getInsertResp().observe(requireActivity(), s -> tvResp.setText(s));
        return view;
    }

    private View.OnClickListener onInsert = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // 监听数据
            myViewModel.loadData();
        }
    };


}
