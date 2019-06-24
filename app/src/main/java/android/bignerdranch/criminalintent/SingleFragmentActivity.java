package android.bignerdranch.criminalintent;

import android.app.AppComponentFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container); //get container from activity
        if (fragment == null) {
            fragment = createFragment(); //set the container to be a crimeFragment
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }
}
