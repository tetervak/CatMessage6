package ca.javateacher.catmessage6;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

public class InputFragment extends Fragment {

  public interface InputListener {
    void updateMessage(String text);
  }

  private RadioGroup mMessageGroup;

  private InputListener mInputListener;


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
    sendButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        showOutput();
      }
    });

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
      mInputListener.updateMessage(message);
    }
  }


  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    try {
      mInputListener = (InputListener) context;
    } catch (ClassCastException e) {
      throw new ClassCastException(context.toString()
          + " must implement InputListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mInputListener = null;
  }
}
