package com.example.anote.SearchPage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.anote.Adapters.HistorySearchAdapter;
import com.example.anote.MainActivity;
import com.example.anote.R;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private LinearLayout suggestions, historyl;
    ArrayList<String> history = new ArrayList<>();

    public SearchFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        Initialization(view);
        initToolbar(view);

        HistorySearchAdapter historySearchAdapter = initHistoryRV(container, view);
        SearchAdapter searchAdapter = initSuggestionRV(container, view);
        initSearchView(container, searchAdapter, historySearchAdapter, view);

        return view;
    }

    private void initToolbar(View view){
        AppCompatActivity parent = (AppCompatActivity)getActivity();
        Toolbar toolbar = view.findViewById(R.id.my_toolbar);
//        parent.setSupportActionBar(toolbar);
//            ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        Log.v("LOG6546548979842", "search.parent : " + parent);
    }

    private void Initialization(View view){
        suggestions = view.findViewById(R.id.search_page_suggestions);
        historyl = view.findViewById(R.id.search_page_history);
        history.add("درس عمومی");
        history.add("ریاضی");
    }

    private HistorySearchAdapter initHistoryRV(ViewGroup container, View view){
        final HistorySearchAdapter historySearchAdapter = new HistorySearchAdapter(container.getContext(), history);
        RecyclerView recyclerView = view.findViewById(R.id.search_fragment_recycler_view_history);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.setAdapter(historySearchAdapter);
        return historySearchAdapter;
    }

    private SearchAdapter initSuggestionRV(ViewGroup container, View view){
        final SearchAdapter searchAdapter = new SearchAdapter(container.getContext(), MainActivity.getHandNotes());
        RecyclerView recyclerView = view.findViewById(R.id.search_fragment_recycler_view_suggestions);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.setAdapter(searchAdapter);
        return searchAdapter;
    }
    private void initSearchView(final ViewGroup container, final SearchAdapter searchAdapter, final HistorySearchAdapter historySearchAdapter, final View view){
        final SearchView searchView = view.findViewById(R.id.search_fragment_search_view);
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(container.getContext(), "در حال جستجو ...", Toast.LENGTH_SHORT).show();
                history.add(s);
                historySearchAdapter.filter(s);
                searchView.setQuery("",false);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s.equals("")){
                    suggestions.setVisibility(View.GONE);
                    historyl.setVisibility(View.VISIBLE);
                } else {
                    suggestions.setVisibility(View.VISIBLE);
                    historyl.setVisibility(View.GONE);
                    searchAdapter.filter(s, suggestions);
                }
                return false;
            }
        });
    }
}
