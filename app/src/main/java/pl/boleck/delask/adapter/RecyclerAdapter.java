package pl.boleck.delask.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import pl.boleck.delask.R;
import pl.boleck.delask.model.Question;

/**
 * Created by Boleck on 2016-05-02.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static final String TAG = "PB";
    private final Context context;

    public interface IMyViewHolderClicks {
        void onButtonClick(View view, int position, ViewHolder holder, boolean isPositive);
    }

    public static IMyViewHolderClicks listener;
    private final List<Question> mQuestions;

    public void setOnItemClickListener(IMyViewHolderClicks listener) {
        this.listener = listener;
    }

    public RecyclerAdapter(List<Question> mQuestions, Context context) {
        this.mQuestions = mQuestions;
        this.context = context;
    }
    public void removeAt(int position) {
        mQuestions.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mQuestions.size());
    }


    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_item, parent, false);

        return  new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        Log.d(TAG,"Question is " + mQuestions.get(position).getText());
        holder.mTextView.setText(mQuestions.get(position).getText());



    }

    @Override
    public int getItemCount() {
        return mQuestions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final CardView cardView;
        private final ViewHolder holder;
        public Button btYes;
        public Button btNo;
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            holder = this;
            mTextView = (TextView) v.findViewById(R.id.text_question);
            btYes = (Button) v.findViewById(R.id.btn_yes);
            btNo = (Button) v.findViewById(R.id.btn_no);
            cardView = (CardView) v.findViewById(R.id.card_view);
            btYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        listener.onButtonClick(cardView,getAdapterPosition(),holder,true);
                    }
                }
            });

            btNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        listener.onButtonClick(cardView,getAdapterPosition(),holder,false);
                    }
                }
            });
        }

        public void delete(int postion) {
            removeAt(postion);
        }



    }
}
