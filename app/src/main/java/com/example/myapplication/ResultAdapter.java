package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultItemViewHolder> {
    private List<Quiz> quizList;

    public ResultAdapter(List<Quiz> quizList) {
        this.quizList = quizList;
    }

    @Override
    public ResultItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result, parent, false);
        return new ResultItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ResultItemViewHolder holder, int position) {
        Quiz quiz = quizList.get(position);
        holder.bind(quiz, position);
    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }


    public class ResultItemViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView contentTextView;

        public ResultItemViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_text_view);
            contentTextView = itemView.findViewById(R.id.content_text_view);
        }

        public void bind(Quiz resultItem, int postion) {
            titleTextView.setText((postion+1) + ". " + resultItem.getQuestion());
            contentTextView.setText(resultItem.getMyAnswer());
        }
    }
}





