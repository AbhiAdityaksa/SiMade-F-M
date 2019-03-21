package changePassword;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.simadeui.R;

import Api.ApiClient;
import Api.ApiService;
import Model.Response;
import utama.UtamaActivity;

public class GantiPassActivity extends AppCompatActivity implements GantiPassView {

    private EditText et_oldPass;
    private EditText et_newPass;
    private EditText et_confirmPass;

    private Button btn_save;

    private String oldPass;
    private String newPass;
    private String confirmPass;

    private GantiPassPresenter presenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_pass);

        init();
        changePass();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(GantiPassActivity.this, UtamaActivity.class);
        intent.putExtra("FRAG", "Profile");
        startActivity(intent);
        finish();
        // Don't add finish here.
        //This is necessary because you finished your last activity with finish();
    }

    private void init(){
        et_oldPass = findViewById(R.id.et_oldPass);
        et_newPass = findViewById(R.id.et_newPass);
        et_confirmPass = findViewById(R.id.et_confirmPass);
        btn_save = findViewById(R.id.btn_save_pass);

        presenter = new GantiPassPresenter(this, ApiClient.getService(this));

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Now Loading...");
    }

    private void changePass(){
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldPass = et_oldPass.getText().toString();
                newPass = et_newPass.getText().toString();
                confirmPass = et_confirmPass.getText().toString();

                presenter.changePassword(oldPass,newPass,confirmPass);
            }
        });
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.hide();
    }

    @Override
    public void onSuccess(Response response) {

        et_oldPass.getText().clear();
        et_newPass.getText().clear();
        et_confirmPass.getText().clear();
        progressDialog.hide();

        Intent intent = new Intent(GantiPassActivity.this,UtamaActivity.class);
        startActivity(intent);
        finish();

        Toast.makeText(this, "Success Change Password ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError() {
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }
}
