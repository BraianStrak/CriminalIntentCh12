package android.bignerdranch.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity {
    public static final String EXTRA_CRIME_ID = "com.bignerdranch.android.criminalintent.crime_id";
    private ViewPager mViewPager;
    private List<Crime> mCrimes;

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager); //inflates the xml layout

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID); //get the intent it got started with (with crimeHolder)

        mViewPager = (ViewPager) findViewById(R.id.crime_view_pager); //calls the viewpager by id same layout as above

        mCrimes = CrimeLab.get(this).getCrimes(); //get our data set from crimeLab
        FragmentManager fragmentManager = getSupportFragmentManager(); //we need a fm for every fragment
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) { //we create the adapter to be an unnamed instance of FragmentStatePagerAdapter
                                                                            //Creating this requires a fragmentManager
                                                                            //FragmentStatePagerAdapter is controller
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }

        });

        for (int i = 0; i < mCrimes.size(); i++){
            if (mCrimes.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }


    }
}
