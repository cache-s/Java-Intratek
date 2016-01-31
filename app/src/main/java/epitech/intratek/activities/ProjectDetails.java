package epitech.intratek.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import chazot_a.epitech.intratek.R;
import epitech.intratek.api.ApiCalls;

public class ProjectDetails extends AppCompatActivity {

    TextView moduleTitle;
    TextView projectTitle;
    TextView dateStart;
    TextView dateEnd;
    TextView description;
    ImageView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
        final HashMap<String, String> params = new HashMap<>();
        Intent myItent = getIntent();

        moduleTitle = (TextView) findViewById(R.id.moduleTitle);
        projectTitle = (TextView) findViewById(R.id.projectTitle);
        dateStart = (TextView) findViewById(R.id.dateStart);
        dateEnd = (TextView) findViewById(R.id.dateEnd);
        description = (TextView) findViewById(R.id.description);
        register = (ImageView) findViewById(R.id.register);

        params.put("token", myItent.getStringExtra("token"));
        params.put("scolaryear", myItent.getStringExtra("scolaryear"));
        params.put("codemodule", myItent.getStringExtra("codemodule"));
        params.put("codeinstance", myItent.getStringExtra("codeinstance"));
        params.put("codeacti", myItent.getStringExtra("codeacti"));

        moduleTitle.setText(myItent.getStringExtra("moduleTitle"));
        projectTitle.setText(myItent.getStringExtra("projectTitle"));
        dateStart.setText(myItent.getStringExtra("dateStart"));
        dateEnd.setText(myItent.getStringExtra("dateEnd"));
        description.setText(myItent.getStringExtra("description"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date();
        try {
            Date dateReg = sdf.parse(myItent.getStringExtra("endRegister"));
            if (myItent.getStringExtra("registered") != null)
                if (curDate.before(dateReg)) {
                    register.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SubUnsub mProject = new SubUnsub("DELETE", params);
                            mProject.execute();
                            register.setImageResource(R.drawable.ic_sub);
                        }
                    });
                    register.setImageResource(R.drawable.ic_unsub);
                } else
                    register.setImageResource(R.drawable.ic_unsub_dis);
            else {
                if (curDate.before(dateReg)) {
                    register.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SubUnsub mProject = new SubUnsub("POST", params);
                            mProject.execute();
                        }
                    });
                    register.setImageResource(R.drawable.ic_sub);
                } else
                    register.setImageResource(R.drawable.ic_sub_dis);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        View.OnClickListener subProject = new View.OnClickListener() {
            public void onClick(View v) {
                // do something here
            }
        };
    }

    public class SubUnsub extends AsyncTask<Void, Void, Boolean> {

        String _call;
        private HashMap<String, String> _params = new HashMap<>();
        ApiCalls network = ApiCalls.getInstance();
        SubUnsub(String call, HashMap<String, String> params)
        {
            _call = call;
            _params = params;
        }

        protected Boolean doInBackground(Void... etc) {
            if (_call.equals("POST")) {
                network.performPostCall("https://epitech-api.herokuapp.com/project?", _params);

            }
            else
                network.performDeleteCall("https://epitech-api.herokuapp.com/project?", _params);
            return true;
        }
    }

}
