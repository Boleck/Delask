package pl.boleck.delask;

import android.app.Application;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.GsonBuilder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import pl.boleck.delask.comunication.Endpoints;
import pl.boleck.delask.helper.Constants;
import pl.boleck.delask.model.Question;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "PB";
    @Bind(R.id.circular_progressbar)
    LinearLayout progressBar;
    @Bind(R.id.content)
    RelativeLayout content;

    private Question question;
    DelaskApp mApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mApp = (DelaskApp) getApplication();
        Endpoints service = mApp.getRetrofit().create(Endpoints.class);
        Call<List<Question>> call = service.getAllQuestions();
        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                switch (response.code()){
                    case 200:


                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {

            }
        });
    }



    @OnClick(R.id.myFAB)
    public void addNewQuestion(View view){
        startActivity(new Intent(MainActivity.this,AddQuestionActivity.class));
    }


}
