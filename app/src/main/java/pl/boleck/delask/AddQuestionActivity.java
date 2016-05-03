package pl.boleck.delask;

import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.boleck.delask.comunication.Endpoints;
import pl.boleck.delask.model.Question;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddQuestionActivity extends AppCompatActivity {

    @Bind(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;
    @Bind(R.id.input_question)
    TextInputEditText textInputEditText;

    DelaskApp mApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        ButterKnife.bind(this);
        mApp = (DelaskApp) getApplication();


    }
    @OnClick(R.id.btn_add_question)
    public void addQuestion(View view){
        if(!TextUtils.isEmpty(textInputEditText.getText()) && textInputEditText.length()>5 && textInputEditText.length()<180) {
            String deviceid = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            Question testQuestion = new Question(textInputEditText.getText().toString(),deviceid);
            Endpoints service = mApp.getRetrofit().create(Endpoints.class);
            Call<Question> call = service.createQuestion(testQuestion);
            call.enqueue(new Callback<Question>() {
                public Question question;

                @Override
                public void onResponse(Call<Question> call, Response<Question> response) {
                    if (response.isSuccessful()) {
                        switch (response.code()) {
                            case 201:
                                question = response.body();
                                if (question != null) {
                                    //progressBar.setVisibility(View.GONE);
                                    Snackbar.make(coordinatorLayout, R.string.callback_200, Snackbar.LENGTH_SHORT);
                                    finish();
                                }
                                break;
                        }
                    }
                }

                @Override
                public void onFailure(Call<Question> call, Throwable t) {
                    Snackbar.make(coordinatorLayout,t.getMessage(),Snackbar.LENGTH_SHORT);
                    //textInputEditText.setError(t.getMessage());

                }
            });
        }else{
            textInputEditText.setError(getString(R.string.input_error));
        }
    }
}
