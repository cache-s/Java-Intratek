package epitech.intratek.activities;

import android.os.Bundle;

import chazot_a.epitech.intratek.R;
import epitech.intratek.utils.MenuSetUp;

public class About extends MenuSetUp {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setUpMenu();
    }
}
