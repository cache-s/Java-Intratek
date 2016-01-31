package epitech.intratek.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

import chazot_a.epitech.intratek.R;
import epitech.intratek.api.ApiCalls;
import epitech.intratek.utils.MenuSetUp;

public class Planning extends MenuSetUp {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);
        setUpMenu();

        // Create the adapter that will return a fragment for each of the ten
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "SECTION " + (position + 1);
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private epitech.intratek.json.Planning planning;
        TextView actiName;
        EditText token;
        SharedPreferences preferences;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        private boolean checkDates(epitech.intratek.json.Planning planning, int week)
        {
            String start = planning.start;

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.clear(Calendar.MINUTE);
            cal.clear(Calendar.SECOND);
            cal.clear(Calendar.MILLISECOND);
            cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
            cal.add(Calendar.WEEK_OF_YEAR, (week));
            String weekStart = format.format(cal.getTime());
            Calendar cal2 = Calendar.getInstance();
            cal2.set(Calendar.HOUR_OF_DAY, 0);
            cal2.clear(Calendar.MINUTE);
            cal2.clear(Calendar.SECOND);
            cal2.clear(Calendar.MILLISECOND);
            cal2.set(Calendar.DAY_OF_WEEK, cal2.getFirstDayOfWeek());
            cal2.add(Calendar.WEEK_OF_YEAR, (week + 1));
            String weekEnd = format.format(cal2.getTime());

            int iend = start.indexOf(" ");
            start = start.substring(0 , iend);
            try {
                Date date1 = format.parse(start);
                Date date2 = format.parse(weekStart);
                Date date3 = format.parse(weekEnd);

                if (date1.compareTo(date2) >= 0 && date1.compareTo(date3) < 0) {
                    return true;
                }
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
            return false;
        }

        private ArrayList<epitech.intratek.json.Planning> getCurrentWeek(ArrayList<epitech.intratek.json.Planning> fullPlanning, int week)
        {
            ArrayList<epitech.intratek.json.Planning> currentWeek = new ArrayList<epitech.intratek.json.Planning>();

            for (epitech.intratek.json.Planning planning: fullPlanning) {
                if (checkDates(planning, week))
                    currentWeek.add(planning);
            }

            return currentWeek;
        }

        private String[]    getCurrentWeekDates(int week)
        {
            String dates[];

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.clear(Calendar.MINUTE);
            cal.clear(Calendar.SECOND);
            cal.clear(Calendar.MILLISECOND);
            cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
            cal.add(Calendar.WEEK_OF_YEAR, (week));
            String weekStart = format.format(cal.getTime());
            Calendar cal2 = Calendar.getInstance();
            cal2.set(Calendar.HOUR_OF_DAY, 0);
            cal2.clear(Calendar.MINUTE);
            cal2.clear(Calendar.SECOND);
            cal2.clear(Calendar.MILLISECOND);
            cal2.set(Calendar.DAY_OF_WEEK, cal2.getFirstDayOfWeek());
            cal2.add(Calendar.WEEK_OF_YEAR, (week + 1));
            cal2.add(Calendar.DATE, -1);
            String weekEnd = format.format(cal2.getTime());

            dates = new String[] {weekStart,weekEnd};

            return dates;
        }

        private LinearLayout[]  getDailyContentTexts(View rootView)
        {
            LinearLayout[] array;

            LinearLayout monday = (LinearLayout) rootView.findViewById(R.id.layoutContentMonday);
            LinearLayout tuesday = (LinearLayout) rootView.findViewById(R.id.layoutContentTuesday);
            LinearLayout wednesday = (LinearLayout) rootView.findViewById(R.id.layoutContentWednesday);
            LinearLayout thursday = (LinearLayout) rootView.findViewById(R.id.layoutContentThursday);
            LinearLayout friday = (LinearLayout) rootView.findViewById(R.id.layoutContentFriday);
            LinearLayout saturday = (LinearLayout) rootView.findViewById(R.id.layoutContentSaturday);
            LinearLayout sunday = (LinearLayout) rootView.findViewById(R.id.layoutContentSunday);

            array = new LinearLayout[] {monday,tuesday,wednesday,thursday,friday,saturday,sunday};

            return array;
        }

        private String getHour(String planning)
        {
            int index = planning.indexOf(" ");
            return planning.substring(index + 1, index + 6);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_planning, container, false);
            Gson gson = new Gson();
            preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            String infos = preferences.getString("MyPlanning", "");
            Type type = new TypeToken<ArrayList<epitech.intratek.json.Planning>>(){}.getType();
            ArrayList<epitech.intratek.json.Planning> plannings = gson.fromJson(infos, type);
            ArrayList<epitech.intratek.json.Planning> currentWeek = getCurrentWeek(plannings, getArguments().getInt(ARG_SECTION_NUMBER) - 2);

            TextView weekDates = (TextView) rootView.findViewById(R.id.weekDates);

            String currentWeekDates[] = getCurrentWeekDates((getArguments().getInt(ARG_SECTION_NUMBER) - 2));
            String  weekDatesString = getResources().getString(R.string.planning_from) + " " + currentWeekDates[0] + " " + getResources().getString(R.string.planning_to) + " " + currentWeekDates[1];
            weekDates.setText(weekDatesString);

            LinearLayout dailyContent[] = getDailyContentTexts(rootView);

            final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Collections.sort(currentWeek, new Comparator<epitech.intratek.json.Planning>() {
                public int compare(epitech.intratek.json.Planning o1, epitech.intratek.json.Planning o2) {
                    try {
                        Date a = format.parse(o1.start);
                        Date b = format.parse(o2.start);
                        if (a.compareTo(b) == 0)
                            return 0;
                        if (a.compareTo(b) < 0)
                            return -1;
                        if (a.compareTo(b) > 0)
                            return 1;
                    }
                    catch (ParseException e)
                    {
                        e.printStackTrace();
                    }
                    return 0;
                }
            });


            String weekStart = currentWeek.get(0).start;
            TextView toAdd;
            int iend = weekStart.indexOf(" ");
            weekStart = weekStart.substring(0 , iend);
            int it = 0;
            String toPrint;
            for (epitech.intratek.json.Planning planning: currentWeek) {
                if (!planning.start.substring(0, iend).equals(weekStart))
                {
                    weekStart = planning.start.substring(0, iend);
                    ++it;
                }
                toPrint = getHour(planning.start) + " - " + getHour(planning.end) + " : ";
                if (planning.acti_title == null)
                    toPrint += "Susie" + "\n";
                else
                    toPrint += planning.acti_title + "\n";
                toAdd = new TextView(getActivity());
                toAdd.setText(toPrint);
                HashMap<String, String> param = new HashMap<>();
                param.put("token", preferences.getString("token", ""));
                param.put("scolaryear", planning.scolaryear);
                param.put("codemodule", planning.codemodule);
                param.put("codeinstance", planning.codeinstance);
                param.put("codeacti", planning.codeacti);
                param.put("codeevent", planning.codeevent);
                setOnClick(toAdd, planning.acti_title, param);
                dailyContent[it].addView(toAdd);
            }

            return rootView;
        }

        private void setOnClick(final TextView tv, final String str, final HashMap<String, String> param)
        {
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final ApiCalls network = ApiCalls.getInstance();
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    LayoutInflater inflater = getActivity().getLayoutInflater();
                    builder.setView(inflater.inflate(R.layout.layout_dialog_token, null)).setTitle(getResources().getString(R.string.enter_token) + " " + str)
                            .setPositiveButton(R.string.valid, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    EditText edit = (EditText) ((AlertDialog) dialog).findViewById(R.id.token);
                                    param.put("tokenvalidationcode", edit.getText().toString());
                                    network.performPostCall("/token", param);
                                }
                            })
                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    builder.show();
                }
            });
        }
    }
}
