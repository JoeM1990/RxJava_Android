package com.creasmit.task.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.creasmit.task.R;
import com.creasmit.task.data.model.Task;
import com.creasmit.task.data.repository.TaskRepository;
import com.creasmit.task.databinding.FragmentTaskFormBinding;
import com.creasmit.task.vm.TaskViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskFormFragment extends Fragment {

    @BindView(R.id.add)
    TextView add;

    private Task task;
    private TaskViewModel taskViewModel;
    private TaskRepository taskRepository;
    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.taskViewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentTaskFormBinding fragmentTaskFormBinding = FragmentTaskFormBinding.inflate(inflater);
        View view = fragmentTaskFormBinding.getRoot();
        ButterKnife.bind(this, view);
        this.task = new Task();
        fragmentTaskFormBinding.setTask(task);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindEvents();
        this.navController= Navigation.findNavController(view);
    }

    public void bindEvents() {

        this.taskViewModel.getError().observe(requireActivity(), message -> {
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show();
        });

        this.taskViewModel.getSuccess().observe(requireActivity(), message -> {
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show();
        });

        this.add.setOnClickListener(view -> {
            if (!this.task.getDescription().isEmpty() && !this.task.getDelay().isEmpty()) {
                this.taskViewModel.add(this.task);
                //Log.d("T","Valeurs"+this.task.getDescription().toString()+""+this.task.getDelay().toString());
                //NavHostFragment.findNavController(TaskFormFragment.this).navigate(R.id.action_taskFormFragment_to_listTaskFragment);
            } else {
                Toast.makeText(requireContext(), "Renseigner tout les champs", Toast.LENGTH_LONG).show();
            }
        });
    }

}