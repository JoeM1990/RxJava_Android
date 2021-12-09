package com.creasmit.task.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creasmit.task.R;
import com.creasmit.task.data.repository.TaskRepository;
import com.creasmit.task.view.adapter.TaskViewAdapter;
import com.creasmit.task.vm.TaskViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

public class ListTaskFragment extends Fragment {

    @BindView(R.id.list_tasks)
    RecyclerView list_tasks;

    @BindView(R.id.flot_btn)
    FloatingActionButton flot_btn;

    private TaskViewModel taskViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.taskViewModel=new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_list_task,container,false);
        ButterKnife.bind(this,view);
        init();
        bindEvents();
        return view;
    }

    public void init(){

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false);
        list_tasks.setLayoutManager(linearLayoutManager);

        TaskViewAdapter taskViewAdapter=new TaskViewAdapter(requireContext());
        taskViewAdapter.setTasks(new ArrayList<>());
        list_tasks.setAdapter(taskViewAdapter);

        this.taskViewModel.getAll().observe(requireActivity(),tasks->{
            taskViewAdapter.setTasks(tasks);
            taskViewAdapter.notifyDataSetChanged();
        });

    }

    public void bindEvents(){
        flot_btn.setOnClickListener(view -> {
            NavHostFragment.findNavController(ListTaskFragment.this).navigate(R.id.action_listTaskFragment_to_taskFormFragment);
        });
    }
}