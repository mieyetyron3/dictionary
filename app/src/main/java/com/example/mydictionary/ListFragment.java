package com.example.mydictionary;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mydictionary.adapters.WordAdapter;
import com.example.mydictionary.models.WordFullModel;
import com.example.mydictionary.viewmodels.SharedViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //Declare attributes
    private static final String TAG = ListFragment.class.getSimpleName();
    private EditText listSearchWord;
    private Button listSearchButton;
    private RecyclerView listRecyclerview;

    private SharedViewModel sharedViewModel;
    private WordAdapter wordAdapter;
    private List<List<WordFullModel>> wordFullModelRecyclerList;

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
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

        //Initialize ViewModel
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Initialize views
        listSearchWord = view.findViewById(R.id.list_search_word);
        listSearchButton = view.findViewById(R.id.list_search_button);
        listRecyclerview = view.findViewById(R.id.list_recyclerview);

        //Initialize resources
        listRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        wordFullModelRecyclerList = sharedViewModel.getWordFullModelRecyclerList();
        wordAdapter = new WordAdapter(wordFullModelRecyclerList, getContext());
        listRecyclerview.setAdapter(wordAdapter);

        //Observe Remote Data
        observeRemoteWord();

        //Set click listener for button
        setListSearchButtonListener(listSearchWord);

        //Observe Delete word
        observeDeleteWord();

    }

    private void observeDeleteWord() {
        final Observer<String> deleteWordObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                //TODO: Find and delete the corresponding word wordFullModelRecyclerList.
            }
        };

        sharedViewModel.getDeleteWordMutableLiveData().observe(getViewLifecycleOwner(), deleteWordObserver);
    }

    private void observeRemoteWord() {
        // Create the observer which observes the wordFullModelList live data
        final Observer<List<WordFullModel>> wordFullModelObserver = new Observer<List<WordFullModel>>() {
            @Override
            public void onChanged(List<WordFullModel> wordFullModels) {
                Log.d(TAG, "onChanged: called. Received Word from SharedViewModel");
                if(sharedViewModel.isClicked()) {
                    if(wordFullModels != null) {
                        wordFullModelRecyclerList.add(wordFullModels);
                        wordAdapter.notifyDataSetChanged();
                        //TODO: Navigate to DetaulFragment
                        NavController navController = NavHostFragment.findNavController(ListFragment.this);
                        navController.navigate(R.id.action_listFragment_to_detailFragment);
                    }
                    else {
                        //TODO:
                        Toast.makeText(ListFragment.this.getContext(), "Error occurred when receiving the word", Toast.LENGTH_SHORT).show();
                        List<WordFullModel> unAvailableWord = new ArrayList<>();
                        unAvailableWord.add(new WordFullModel());
                        unAvailableWord.get(0).setWord(sharedViewModel.getCurrentWord());
                        wordFullModelRecyclerList.add(unAvailableWord);
                    }
                }
                sharedViewModel.setClicked(false);

            }
        };

        // Observe the remote data LiveData, passing in this fragment as the LifecycleOwner and the observer.
        sharedViewModel.getWordFullModelListMutableLiveData().observe(getViewLifecycleOwner(), wordFullModelObserver);

    }

    private void setListSearchButtonListener(EditText editText) {
        listSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedViewModel.setClicked(true);
                //TODO:Retrieve the content of listSearchWord (Validate)
                String word = editText.getText().toString();
                sharedViewModel.getWord(word);
                //and get the word from the RemoteDataSource
                //Handle background based on success or failure response
            }
        });
    }
}