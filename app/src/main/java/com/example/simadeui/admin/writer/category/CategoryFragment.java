package com.example.simadeui.admin.writer.category;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simadeui.R;
import com.example.simadeui.admin.writer.category.addcategory.AddCategoryActivity;

import java.util.List;

import Api.ApiClient;
import Model.Response;

public class CategoryFragment extends Fragment implements CategoryAdapter.OnClickListener, CategoryView{

    private FloatingActionButton floatingActionButton;
    private List<Response> responseList;
    private RecyclerView recyclerView;
    private CategoryAdapter adapter;
    private CategoryPresenter presenter;
    private Response response;
    private ProgressDialog progressDialog;
    private TextView textView;
    private SwipeRefreshLayout swipeRefreshLayout;

    public CategoryFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_admin, container, false);
        floatingActionButton = view.findViewById(R.id.add_category);
        recyclerView = view.findViewById(R.id.rv_category_admin);
        textView = view.findViewById(R.id.tv_category_admin_kosong);
        swipeRefreshLayout = view.findViewById(R.id.refresh);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Now Loading...");

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter = new CategoryPresenter(this, ApiClient.getService(getContext()));
        presenter.showCatInfo();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddCategoryActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(int position) {

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
    public void onSuccess(List<Response> responseList) {
        if (responseList.isEmpty()){
            textView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else {
            textView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            this.responseList = responseList;
            adapter = new CategoryAdapter(getContext(), responseList);
            adapter.setOnClickListener(this);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
        }
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
