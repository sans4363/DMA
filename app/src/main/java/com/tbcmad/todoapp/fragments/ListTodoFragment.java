package com.tbcmad.todoapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tbcmad.todoapp.R;
import com.tbcmad.todoapp.adapters.TodoListAdapter;
import com.tbcmad.todoapp.model.ETodo;
import com.tbcmad.todoapp.viewModel.TodoListViewModel;

import java.util.List;
import java.util.logging.Logger;

public class ListTodoFragment extends Fragment {
    View rootView;
    RecyclerView rvListTodo;
    TodoListViewModel todoListViewModel;
    private TodoListAdapter adapter;
    private boolean mJustCreatedView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_list_todo, container, false);
        rvListTodo = rootView.findViewById(R.id.list_item_todo_rv_list_todo);
        todoListViewModel = new ViewModelProvider(this).get(TodoListViewModel.class);
        mJustCreatedView = true;

        LinearLayoutManager manager= new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvListTodo.setLayoutManager(manager);

        adapter = new TodoListAdapter(todoListViewModel, getActivity());
        rvListTodo.setAdapter(adapter);

        Logger.getAnonymousLogger().info("on create view list fragment ");

        new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.handleSwipeActions(viewHolder.getAdapterPosition(), "delete");
            }
        }).attachToRecyclerView(rvListTodo);

        return  rootView;
    }

    public TodoListAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void onStart() {
        super.onStart();

        Logger.getAnonymousLogger().info("started list fragment");
        if (!mJustCreatedView) {
            adapter.syncData();
        } else {
            mJustCreatedView = false;
        }
    }
}