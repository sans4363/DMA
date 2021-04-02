package com.tbcmad.todoapp.view_holders;

import com.tbcmad.todoapp.model.ETodo;

public interface TodoListViewHolderCallbacks {
    void onDeleteItem(ETodo item, int position);
    void onUpdateItem(ETodo item, int position);
    void onItemInserted(ETodo item);
}
