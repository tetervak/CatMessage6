package ca.javateacher.catmessage6;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity{

  private static final String ABOUT_FRAGMENT_TAG = "aboutFragment";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // make the toolbar "up" button work with the navigation
    Toolbar toolbar = findViewById(R.id.toolbar);
    NavController navController
            = Navigation.findNavController(this, R.id.nav_host_fragment);
    AppBarConfiguration appBarConfiguration
            = new AppBarConfiguration.Builder(navController.getGraph()).build();
    NavigationUI.setupWithNavController(toolbar, navController,appBarConfiguration);

    setSupportActionBar(toolbar);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch(item.getItemId()){
      case R.id.about:{
        showAbout();
        return true;
      }
      case android.R.id.home:{
        showInput();
        return true;
      }
      default: {
        return super.onOptionsItemSelected(item);
      }
    }
  }

    private void showInput() {
        Navigation.findNavController(this, R.id.nav_host_fragment)
                .navigate(R.id.action_global_input);
    }

    private void showAbout() {
        AboutFragment aboutFragment = AboutFragment.newInstance();
        aboutFragment.show(getSupportFragmentManager(), ABOUT_FRAGMENT_TAG);
    }

}
