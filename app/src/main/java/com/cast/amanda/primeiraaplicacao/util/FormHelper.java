package com.cast.amanda.primeiraaplicacao.util;

import android.content.Context;
import android.widget.EditText;

import com.cast.amanda.primeiraaplicacao.R;

/**
 * Created by Amanda on 22/07/2015.
 */
public final class FormHelper {

   private FormHelper(){
    super();
   }

    public static boolean requireValidate(Context context, EditText... editTexts){
        boolean valid = true;
        for(EditText editText : editTexts){
            String value = editText.getText() == null ?null : editText.getText().toString();
            if(editText.getText() == null || value.trim().isEmpty()){
                String errorMessage = context.getString(R.string.required_field);
                editText.setError(errorMessage);
                valid = false;
            }
        }
        return valid;
    }
}
