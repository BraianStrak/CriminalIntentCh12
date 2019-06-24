package android.bignerdranch.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {

    private List<Crime> mCrimes;
    private static CrimeLab sCrimeLab;
    public static CrimeLab get(Context context) {
        if(sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }
    private CrimeLab(Context context){ //
        mCrimes = new ArrayList<>(); //The empty <> infers the mCrimes contains crimes
        for (int i = 0; i < 100; i++) { //creates 100 boring, sample crimes
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i%2 == 0); //every other crime
            crime.setRequiresPolice(i%2 == 0);
            mCrimes.add(crime);
        }
    }

    public List<Crime> getCrimes() { //returns mcrimes
        return mCrimes;
    }

    public Crime getCrime(UUID id) { //return a crime by ID
        for (Crime crime : mCrimes) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;
    }


}
