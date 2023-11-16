package com.example.plife.ui.cadastro.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MaskUtil {

    private static final String CPFMask = "###.###.###-##";
    private static final String CNPJMask = "##.###.###/####-##";

    public static String unmask(String s) {
        return s.replaceAll("[^0-9]*", "");
    }


    public static TextWatcher insert(final EditText editText) {
        return new TextWatcher() {
            private boolean isUpdating;
            String old = "";

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String str = MaskUtil.unmask(s.toString());
                String mask;
                String defaultMask = getDefaultMask(str);

                switch (str.length()) {
                    case 11:
                        mask = CPFMask;
                        break;
                    case 14:
                        mask = CNPJMask;
                        break;

                    default:
                        mask = defaultMask;
                        break;
                }

                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (char m : mask.toCharArray()) {
                    if ((m != '#' && str.length() > old.length()) || (m != '#' && str.length() < old.length() && str.length() != i)) {
                        mascara += m;
                        continue;
                    }

                    try {
                        mascara += str.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                editText.setText(mascara);
                editText.setSelection(mascara.length());

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        };
    }

    private static String getDefaultMask(String str) {
        String defaultMask = CPFMask;
        if (str.length() > 11) {
            defaultMask = CNPJMask;
        }
        return defaultMask;
    }


}
