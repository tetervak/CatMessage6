package ca.javateacher.catmessage6;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

@SuppressWarnings("ConstantConditions")
public class OutputFragment extends Fragment {

  private TextView mMessageView;

  public OutputFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_output, container, false);

    mMessageView = view.findViewById(R.id.message_text);

    Button closeButton = view.findViewById(R.id.back_button);
    closeButton.setOnClickListener(this::showInput);

    return view;
  }

  private void showInput(View v) {
    Navigation.findNavController(v).popBackStack(R.id.inputFragment,false);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    MessageViewModel viewModel = ViewModelProviders.of(getActivity()).get(MessageViewModel.class);
    viewModel.getMessageData().observe(this, message -> mMessageView.setText(message));
  }

}
