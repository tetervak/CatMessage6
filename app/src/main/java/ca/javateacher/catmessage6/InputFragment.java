package ca.javateacher.catmessage6;

import android.content.Context;
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

@SuppressWarnings("ConstantConditions")
public class InputFragment extends Fragment {

  public interface InputListener {
    void showOutput();
  }

  private RadioGroup mMessageGroup;
  private InputListener mInputListener;
  private MessageViewModel mViewModel;

  public InputFragment() {
    // Required empty public constructor
  }

  public static InputFragment newInstance(){
    return new InputFragment();
  }


  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_input, container, false);

    mMessageGroup = view.findViewById(R.id.message_group);

    Button sendButton = view.findViewById(R.id.send_button);
    sendButton.setOnClickListener(v -> showOutput());

    return view;
  }

  private void showOutput() {
    if(mInputListener != null){
      // get the selected message
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
      mInputListener.showOutput();
    }
  }


  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    if(context instanceof InputListener){
      mInputListener = (InputListener) context;
    }else{
      throw new RuntimeException(context.toString()
              + " must implement InputFragment.InputListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mInputListener = null;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = ViewModelProviders.of(getActivity()).get(MessageViewModel.class);
    // TODO: Use the ViewModel
  }
}
