package pl.boleck.delask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.boleck.delask.adapter.RecyclerAdapter;
import pl.boleck.delask.comunication.Endpoints;
import pl.boleck.delask.model.Question;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "PB";
    @Bind(R.id.circular_progressbar)
    LinearLayout progressBar;
    @Bind(R.id.content)
    RelativeLayout content;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private List<Question> questions;
    DelaskApp mApp;
    private Endpoints service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mApp = (DelaskApp) getApplication();
        service = mApp.getRetrofit().create(Endpoints.class);
        Call<List<Question>> call = service.getAllQuestions();
        call.enqueue(callback);
    }



    @OnClick(R.id.myFAB)
    public void addNewQuestion(View view){
        startActivity(new Intent(MainActivity.this,AddQuestionActivity.class));
    }

    private RecyclerAdapter.IMyViewHolderClicks onButtonClick = new RecyclerAdapter.IMyViewHolderClicks() {
        @Override
        public void onButtonClick(View view, int position, RecyclerAdapter.ViewHolder holder, boolean isPositive) {
            //service.se
            //Log.d(TAG,questions.get(position).getId());
            Question item = questions.get(position);
            //Animation animation = AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.slide_out_right);
            //view.startAnimation(animation);
            if(isPositive){
                service.incrementPositive(item.getId());
            }else {
                service.incrementNegative(item.getId());
            }
            //view.setVisibility(View.GONE);
            holder.delete(position);




        }
    };

    private Callback<List<Question>> callback = new Callback<List<Question>>() {
        @Override
        public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
            switch (response.code()){
                case 200:
                    progressBar.setVisibility(View.GONE);
                    questions = response.body();
                    RecyclerAdapter r = new RecyclerAdapter(questions,MainActivity.this);
                    r.setOnItemClickListener(onButtonClick);

                    mRecyclerView.setAdapter(r);
                    break;
            }
        }

        @Override
        public void onFailure(Call<List<Question>> call, Throwable t) {

        }
    };


}
