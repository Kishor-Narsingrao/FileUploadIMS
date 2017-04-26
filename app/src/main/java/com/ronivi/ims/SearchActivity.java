package com.ronivi.ims;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchActivity extends ActionBarActivity implements View.OnClickListener {

    EditText etESICNumber,etClaimId;
    Button btSearch,btAttachments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        init();
        btSearch.setOnClickListener(this);
        btAttachments.setOnClickListener(this);
    }

    private void init() {
        etESICNumber=(EditText)findViewById(R.id.etESICno);
        etClaimId=(EditText)findViewById(R.id.etClaimid);
        btSearch=(Button)findViewById(R.id.btSearch);
        btAttachments=(Button)findViewById(R.id.btAttachment);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btSearch:
                if(validate())
                {
                    Toast.makeText(this,"Valid ESIC Number",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btAttachment:
                Intent intent=new Intent(SearchActivity.this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    private boolean validate() {
        if(etESICNumber.getText().length() <= 0)
        {
            Toast.makeText(this,"Please Enter the ESIC Card Number",Toast.LENGTH_LONG).show();
            return false;
        }
        else if(etESICNumber.getText().length() !=10 && etESICNumber.getText().length() <10)
        {
            Toast.makeText(this,"Please enter the valid ESIC Card Number",Toast.LENGTH_LONG).show();
            return false;
        }
        else
            {
            return true;
        }
    }
}
