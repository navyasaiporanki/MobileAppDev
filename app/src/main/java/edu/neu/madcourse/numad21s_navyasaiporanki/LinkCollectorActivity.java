package edu.neu.madcourse.numad21s_navyasaiporanki;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import static androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE;
import static androidx.appcompat.app.AlertDialog.Builder;

public class LinkCollectorActivity extends AppCompatActivity {

    ArrayList<String> listOfNames;
    ArrayList<String> listOfUrls;

    ListView listView;
    ItemsList itemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listOfNames = new ArrayList<>();
        listOfUrls = new ArrayList<>();
        setContentView(R.layout.activity_link_collector);

        itemsList = new ItemsList(this, listOfNames, listOfUrls);
        listView = findViewById(R.id.linkListViewID);
        listView.setAdapter(itemsList);

    }

    public void addUrlButton(final View view) {
        LinearLayout linearLayout = new LinearLayout(view.getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        final EditText editTextName = new EditText(this);
        editTextName.setInputType(InputType.TYPE_CLASS_TEXT);
        editTextName.setHint("Enter a name");
        editTextName.setHintTextColor(Color.green(0));
        //editTextName.
        linearLayout.addView(editTextName);

        final EditText editTextURL = new EditText(this);
        editTextURL.setInputType(InputType.TYPE_CLASS_TEXT);
        editTextURL.setHint("Enter a valid URL");
        editTextURL.setHintTextColor(Color.green(0));
        editTextURL.setTextColor(Color.BLUE);
        linearLayout.addView(editTextURL);

        final AlertDialog alertDialogFORM = new Builder(this)
                        .setTitle("Please Enter the details")
                        .setView(linearLayout)
                        .setPositiveButton("OK", null)
                        .setNegativeButton("Cancel", null)
                        .show();


        alertDialogFORM.getButton(BUTTON_POSITIVE).setOnClickListener(v -> {
            String nameEntered = editTextName.getText().toString();
            String inputURLEntered = editTextURL.getText().toString();

//            try {
//                URL url = new URL(inputURLEntered);
//                if (!Patterns.WEB_URL.matcher(url.toString()).matches()) {
//                    Snackbar snackBarFail = Snackbar.make(v,R.string.string_error_message, Snackbar.LENGTH_LONG);
//                    snackBarFail.show();
//                }
//            } catch (MalformedURLException e) {
//                Snackbar snackBarFail = Snackbar.make(v,R.string.string_error_message, Snackbar.LENGTH_LONG);
//                snackBarFail.show();
//            }
            if (nameEntered.isEmpty() || inputURLEntered.isEmpty()
                    //Patterns.WEB_URL.matcher(inputURLEntered).matches()
            ) {

                Snackbar snackBarFail = Snackbar.make(v,R.string.string_error_message, Snackbar.LENGTH_LONG);
                snackBarFail.show();

            }

            else {
                listOfNames.add(nameEntered);
                listOfUrls.add(inputURLEntered);
                View emptyListMessageView = findViewById(R.id.addURLTextView);
                //emptyListMessageView.setVisibility(View.GONE);
                Snackbar snackBarSuccess = Snackbar.make(view, R.string.string_url_added, Snackbar.LENGTH_LONG);
                snackBarSuccess.show();
                alertDialogFORM.dismiss();
                itemsList.notifyDataSetChanged();
                listView.post(new Runnable() {
                    @Override
                    public void run() {
                        listView.smoothScrollToPosition(0);
                    }
                });
            }
        });

    }
}