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
import android.widget.TextView;

public class OutputFragment extends Fragment {

  interface OutputListener{
    void showInput();
  }
  private OutputListener mOutputListener;

  private static final String MESSAGE = "message";
  private String mTextMessage;
  private TextView mMessageView;

  public OutputFragment() {
    // Required empty public constructor
  }

  public static OutputFragment newInstance(@NonNull String message){
    OutputFragment outputFragment = new OutputFragment();
    Bundle arguments = new Bundle();
    arguments.putString(MESSAGE, message);
    outputFragment.setArguments(arguments);
    return outputFragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if(savedInstanceState == null){
      Bundle arguments = getArguments();
      assert arguments != null;
      mTextMessage = arguments.getString(MESSAGE);
    }else{
      mTextMessage = savedInstanceState.getString(MESSAGE);
    }
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_output, container, false);

    mMessageView = view.findViewById(R.id.message_text);
    mMessageView.setText(mTextMessage);

    Button closeButton = view.findViewById(R.id.back_button);
    closeButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mOutputListener.showInput();
      }
    });

    return view;
  }

  @Override
  public void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putString(MESSAGE, mTextMessage);
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    try {
      mOutputListener = (OutputListener) context;
    } catch (ClassCastException e) {
      throw new ClassCastException(context.toString()
          + " must implement OutputListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mOutputListener = null;
  }

}
