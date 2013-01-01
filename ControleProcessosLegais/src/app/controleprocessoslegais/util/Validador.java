package app.controleprocessoslegais.util;

import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class Validador {

	public static boolean validateNotNull(View pView, String pMessage) {
		if (EditText.class.isInstance(pView)) {
			EditText edText = (EditText) pView;
			Editable text = edText.getText();
			if (text != null) {
				String strText = text.toString();
				if (!TextUtils.isEmpty(strText)) {
					return true;
				}
			}
			edText.setError(pMessage);
			edText.setFocusable(true);
			edText.requestFocus();
			return false;
		} else if (Spinner.class.isInstance(pView)) {
			Spinner spinnerText = (Spinner) pView;
			Object text = spinnerText.getSelectedItem();
			if (text != null) {
				String strText = text.toString();
				if (!TextUtils.isEmpty(strText)) {
					return true;
				}
			}
			spinnerText.setFocusable(true);
			spinnerText.requestFocus();
			return false;
		}
		return false;
	}

}
