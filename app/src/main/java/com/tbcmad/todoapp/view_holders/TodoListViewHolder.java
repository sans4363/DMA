package com.tbcmad.todoapp.view_holders;
import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tbcmad.todoapp.R;
import com.tbcmad.todoapp.activity.EditActivity;
import com.tbcmad.todoapp.model.ETodo;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class TodoListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView title, date, description, priority, complete;
    private Button completeButton, updateButton, deleteButton;
    private LinearLayout mRootLayout;
    private ETodo mTodo;
    private Resources mResources;
    private TodoListViewHolderCallbacks mCallbacks;
    private int mPosition;

    public TodoListViewHolder(View layoutView, TodoListViewHolderCallbacks todoListViewHolderCallbacks){
        super(layoutView);

        setAllReferences(layoutView, todoListViewHolderCallbacks);
    }

    private void setAllReferences(View layoutView, TodoListViewHolderCallbacks todoListViewHolderCallbacks) {
        mCallbacks = todoListViewHolderCallbacks;
        mResources = layoutView.getContext().getResources();

        mRootLayout = layoutView.findViewById(R.id.root_layout);
        title = layoutView.findViewById(R.id.title_tv);
        date = layoutView.findViewById(R.id.date_tv);
        description = layoutView.findViewById(R.id.description_tv);
        priority = layoutView.findViewById(R.id.priority_tv);
        complete = layoutView.findViewById(R.id.complete_tv);
        completeButton = layoutView.findViewById(R.id.complete_btn);
        updateButton = layoutView.findViewById(R.id.update_btn);
        deleteButton = layoutView.findViewById(R.id.delete_btn);

        completeButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.complete_btn:
                handleCompleteTodoClick();
                break;
            case R.id.update_btn:
                handleUpdateTodoClick();
                break;
            case R.id.delete_btn:
                handleDeleteTodoClick();
                break;
        }
    }

    private void handleCompleteTodoClick() {
        mTodo.setCompleted(!mTodo.isCompleted());
        mCallbacks.onUpdateItem(mTodo, mPosition);
        setValuesForCompleteField();
    }

    private void handleUpdateTodoClick() {
        Intent intent = new Intent(mRootLayout.getContext(), EditActivity.class);
        intent.putExtra("TodoId", mTodo.getId());
        mRootLayout.getContext().startActivity(intent);
    }

    private void handleDeleteTodoClick() {
        mCallbacks.onDeleteItem(mTodo, mPosition);
    }

    public void setValues(ETodo todo, int position){
        this.mTodo = todo;
        mPosition = position;
        setValues();
        setPriorityColor();
    }

    private void setValues() {
        setText(title, mTodo.getTitle(), "Title");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        setText(date, sdf.format(mTodo.getTodoDate()), "Date Not listed");
        setText(description, mTodo.getDescription(), "Description not available");
        setText(priority, "Priority : " + mTodo.getPriority(), "0");
        setValuesForCompleteField();
    }

    private void setValuesForCompleteField() {
        if (mTodo.isCompleted()) {
            setText(complete, "Complete", "Not Complete");
            completeButton.setText("Set Not Complete");
        } else {
            setText(complete, "Not Complete", "Not Complete");
            completeButton.setText("Set Complete");
        }
    }

    private void setText(TextView textView, String value, String defaultValue) {
        String textToSet = "";

        if (value == null || value.trim().length() == 0) {
            textToSet = defaultValue;
        }
        textToSet = value;

        textView.setText(textToSet);
    }

    private void startUpdateActivity() {

    }

    private void setPriorityColor() {

        switch (mTodo.getPriority()) {
            case 1:
                mRootLayout.setBackgroundColor(getResources().getColor(R.color.color_high));
                break;
            case 2:
                mRootLayout.setBackgroundColor(getResources().getColor(R.color.color_medium));
                break;
            case 3:
                mRootLayout.setBackgroundColor(getResources().getColor(R.color.color_low));
                break;
        }
    }

    private Resources getResources() {
        return mResources;
    }
}
