package userReport;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.simadeui.R;
import com.example.simadeui.admin.writer.reportcategory.ReportCategoryPresenter;

import Api.ApiClient;
import Model.Response;

public class LaporanActivityFrag extends Fragment implements UserReportView {

    View v;
    Spinner s_report_category;
    Button bt_send_report;
    EditText et_etc;
    ReportCategoryPresenter reportCategoryPresenter;
    UserReportPresenter userReportPresenter;
    private ProgressDialog progressDialog;
    private String reportId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        v = inflater.inflate(R.layout.activity_laporan_frag, container, false);

        s_report_category = v.findViewById(R.id.s_report_category);
        bt_send_report = v.findViewById(R.id.bt_send_report);
        et_etc = v.findViewById(R.id.et_report_etc);

        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Now Loading...");

        userReportPresenter = new UserReportPresenter(this,ApiClient.getService(getContext()));

        String[] catInfo = new String[]{
                "Pilih Kategori",
                "Sumbangan",
                "Informasi",
                "Kegiatan",
                "Pengumuman",
                "Lainnya"
        };

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_dropdown_item_1line, catInfo
        );

        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        s_report_category.setAdapter(stringArrayAdapter);

        s_report_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                reportId = String.valueOf(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bt_send_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userReportPresenter.addReport(reportId,et_etc.getText().toString());
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
        Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
        et_etc.getText().clear();
        progressDialog.hide();
    }

    @Override
    public void onError() {
        Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
    }
}
