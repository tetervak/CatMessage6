package ca.javateacher.catmessage6;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.view.Menu;
import android.view.MenuItem;

//import static androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

public class MainActivity extends AppCompatActivity implements
    InputFragment.InputListener, OutputFragment.OutputListener {

  // public static final String INPUT_STATE = "input_state";
  public static final String ABOUT_FRAGMENT = "about_fragment";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    if(savedInstanceState == null){
      InputFragment inputFragment = InputFragment.newInstance();
      FragmentManager fragmentManager = getSupportFragmentManager();
      fragmentManager.beginTransaction()
          .add(R.id.fragment_container, inputFragment)
          .commit();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.about) {
      AboutFragment aboutFragment = AboutFragment.newInstance();
      aboutFragment.show(getSupportFragmentManager(), ABOUT_FRAGMENT);
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void updateMessage(String message) {
      OutputFragment outputFragment = OutputFragment.newInstance(message);
      FragmentManager fragmentManager = getSupportFragmentManager();
      fragmentManager.beginTransaction()
          .replace(R.id.fragment_container,outputFragment)
          //.addToBackStack(INPUT_STATE)
          .addToBackStack(null)
          .commit();
  }

  @Override
  public void showInput() {
    getSupportFragmentManager()
            //.popBackStack(INPUT_STATE, POP_BACK_STACK_INCLUSIVE);
    .popBackStack();
  }
}
