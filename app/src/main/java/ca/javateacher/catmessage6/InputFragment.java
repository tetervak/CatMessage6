package ca.javateacher.catmessage6;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

@SuppressWarnings("ConstantConditions")
public class InputFragment extends Fragment {

  private RadioGroup mMessageGroup;

  private MessageViewModel mViewModel;

  public InputFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_input, container, false);

    mMessageGroup = view.findViewById(R.id.message_group);

    Button sendButton = view.findViewById(R.id.send_button);
    sendButton.setOnClickListener(this::showOutput);

    return view;
  }

  private void showOutput(View v) {
      String message;
      switch (mMessageGroup.getCheckedRadioButtonId()) {
        case R.id.purr_button:
          message = getString(R.string.cat_purr);
          break;
        case R.id.mew_button:
          message = getString(R.string.cat_mew);
          break;
        case R.id.hiss_button:
          message = getString(R.string.cat_hiss);
          break;
        default:
          message = getString(R.string.undefined);
      }
      mViewModel.getMessageData().setValue(message);
      Navigation.findNavController(v).navigate(R.id.action_input_to_output);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = ViewModelProviders.of(getActivity()).get(MessageViewModel.class);
  }
}
