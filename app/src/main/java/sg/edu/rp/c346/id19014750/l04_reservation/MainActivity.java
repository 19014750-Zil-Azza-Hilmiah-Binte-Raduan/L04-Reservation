package sg.edu.rp.c346.id19014750.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.DayOfWeek;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText name, pax, phone;
    CheckBox smoke;
    DatePicker dp;
    TimePicker tp;
    Button btnConfirm, btnReset;
    TextView confirmLabel, details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.editTextName);
        pax = findViewById(R.id.editTextPax);
        phone = findViewById(R.id.editTextMobileNo);
        smoke = findViewById(R.id.checkBoxSmoke);
        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        btnConfirm = findViewById(R.id.buttonConfirm);
        btnReset = findViewById(R.id.buttonReset);
        confirmLabel = findViewById(R.id.textViewConfirmation);
        details = findViewById(R.id.textViewDetails);

        //By default
        dp.updateDate(2020, 6, 1);
        tp.setCurrentHour(20);
        tp.setCurrentMinute(30);

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(hourOfDay < 20){
                    view.setCurrentHour(20);
                }
                else if(hourOfDay > 20){
                    view.setCurrentHour(20);
                }
            }
        });


        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().trim().length()!= 0 && pax.getText().toString().trim().length()!= 0 &&
                        phone.getText().toString().trim().length()!= 0){

                    confirmLabel.setText("Confirmation");
                    String dName = String.format("Name: %s\n", name.getText().toString());
                    String dPax = String.format("Pax: %s\n", pax.getText().toString());
                    String dPhone = String.format("Mobile no.: %s\n", phone.getText().toString());

                    String dSmoke;

                    if(smoke.isChecked()){
                        dSmoke = String.format("Table area is in the Smoking area.\n");
                    } else{
                        dSmoke = String.format("Table area is in the Non-Smoking area.\n");
                    }

                    int day = dp.getDayOfMonth();
                    int month = dp.getMonth() + 1;
                    int year = dp.getYear();

                    String dDate = String.format("Date booked: %d/%d/%d\n", day, month, year);
                    String dTime = String.format("Time booked: %d:%d", tp.getCurrentHour(), tp.getCurrentMinute());

                    details.setText(dName + dPax + dPhone + dSmoke + dDate + dTime);

                }
                else{
                    Toast.makeText(MainActivity.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                pax.setText("");
                phone.setText("");
                smoke.setChecked(false);
                dp.updateDate(2020, 6, 1);
                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);
            }
        });

    }
}