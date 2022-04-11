package com.example.mydictionary;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mydictionary.adapters.DetailAdapter;
import com.example.mydictionary.models.MeaningModel;
import com.example.mydictionary.models.WordFullModel;
import com.example.mydictionary.viewmodels.SharedViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //Declare attributes
    private TextView detailWord;
    private RecyclerView detailRecyclerView;
    private Button detailDeleteButton;
    private SharedViewModel sharedViewModel;
    private List<MeaningModel> meaningModelList;
    private DetailAdapter detailAdapter;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Initialize views
        detailWord = view.findViewById(R.id.detail_word);
        detailRecyclerView = view.findViewById(R.id.detail_recyclerView);
        detailDeleteButton = view.findViewById(R.id.detail_delete_button);

        //Initialize resources
        detailRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        meaningModelList = new ArrayList<>();
        detailAdapter = new DetailAdapter(meaningModelList, getContext());
        detailRecyclerView.setAdapter(detailAdapter);

       /* Bundle bundle = getArguments();
        List<WordFullModel> wordFullModelList = (List<WordFullModel>) bundle.getSerializable("wordDetails");
        String word = wordFullModelList.get(0).getWord();
        setDetailValues(wordFullModelList.get(0).getMeanings());
        //Set listener for delete button
        setDetailDeleteButtonListener(word); */
    }

    private void setDetailValues(List<MeaningModel> meaningModelList) {

    }

    private void setDetailDeleteButtonListener(String word) {
        detailDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Delete the item
                sharedViewModel.deleteWord(word);
            }
        });
    }
}