package cake.petitscomptesentreennemis;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateEventActivity extends AppCompatActivity {

    private Button mBtnCreate = null;
    private EditText mEditName = null;
    private Spinner mSpinCurrency = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        mBtnCreate = (Button) findViewById(R.id.btnCreate);
        mEditName = (EditText) findViewById(R.id.editEventName);
        mSpinCurrency = (Spinner) findViewById(R.id.spinCurrency);

        mBtnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditName.getText().length() == 0 || mSpinCurrency.getSelectedItemId() == AdapterView.INVALID_ROW_ID) {
                    return;
                }

                // TODO: ajouté l'évent dans la base de donnée


                finish();
            }
        });
    }
}
