package com.tbcmad.todoapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.tbcmad.todoapp.R;
import com.tbcmad.todoapp.activity.auth.AuthViewModel;
import com.tbcmad.todoapp.model.ETodo;
import com.tbcmad.todoapp.viewModel.TodoListViewModel;
import com.tbcmad.todoapp.view_holders.TodoListViewHolder;
import com.tbcmad.todoapp.view_holders.TodoListViewHolderCallbacks;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListViewHolder>{

    private TodoListViewModel todoListViewModel;
    private List<ETodo> todos;
    private long mUserId;
    private Context mContext;
    private TodoListViewHolderCallbacks mCallbackHandler = getCallbacksHandler();

    public TodoListAdapter(TodoListViewModel todoListViewModel, Context context)
    {
        this.todoListViewModel = todoListViewModel;
        mContext = context;
        mUserId = AuthViewModel.getUserId(context);
        this.todos = todoListViewModel.getUsersTodos(mUserId);
    }

    private TodoListViewHolderCallbacks getCallbacksHandler() {
        return new TodoListViewHolderCallbacks() {
            @Override
            public void onDeleteItem(ETodo item, int position) {
                TodoListAdapter.this.todoListViewModel.deleteById(item);
                TodoListAdapter.this.todos.remove(position);
                notifyItemRemoved(position);
            }

            @Override
            public void onUpdateItem(ETodo item, int position) {
                TodoListAdapter.this.todoListViewModel.update(item);
            }

            @Override
            public void onItemInserted(ETodo item) {

            }
        };
    }

    @NonNull
    @Override
    public TodoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.fragment_list_todo_item, parent, false);

        return new TodoListViewHolder(itemView, mCallbackHandler);
    }

    public void handleSwipeActions(int position, String action) {

    }

    @Override
    public void onBindViewHolder(@NonNull TodoListViewHolder holder, int position) {
        ETodo todo = todos.get(position);
        holder.setValues(todo, position);
    }

    @Override
    public int getItemCount() {


        if (todos == null) {
            Logger.getLogger("ToDoAdapter").info("is null = true");
            return 0;
        }
        Logger.getLogger("TodoAdapter").info("Size = " + todos.size());
        return todos.size();
    }

    public void deleteAllTodos() {
        if (this.todos != null && this.todos.size() > 0) {
            this.todoListViewModel.deleteAllTodos(mUserId);
            Toast.makeText(mContext, "All Todos deleted", Toast.LENGTH_SHORT).show();
            this.todos.clear();
            notifyDataSetChanged();
        } else {
            Toast.makeText(mContext, "No Todos available", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteAllCompletedTodos() {
        if (this.todos == null || this.todos.size() == 0) {
            Toast.makeText(mContext, "No todos available", Toast.LENGTH_SHORT).show();
            return;
        }
        this.todoListViewModel.deleteAllCompletedTodos(mUserId);
        List<ETodo> todoList = new ArrayList<>();
        for (int i = 0; i < this.todos.size(); i++) {
            ETodo item = this.todos.get(i);
            if (!item.isCompleted()) {
                todoList.add(item);
            }
        }

        if (this.todos.size() != todoList.size()) {
            this.todos = todoList;
            Toast.makeText(mContext, "All completed todos have been deleted", Toast.LENGTH_SHORT).show();
            notifyDataSetChanged();
        }
    }

    public void syncData() {
        List<ETodo> items = todoListViewModel.getUsersTodos(mUserId);
        if (items != null) {
            todos = items;
            notifyDataSetChanged();
        }
    }
}