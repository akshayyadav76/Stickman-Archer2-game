package com.example.ak.stickmanarcher2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

/**
 * Created by AK on 13-Sep-18.
 */

public class Dialog extends AppCompatDialogFragment {
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
        builder.setTitle("About").setMessage("It's time for another dual to the death. Several,in fact!" +
        " How long can you prevent your stickman form meeting" +
                " his maker in this intense action game?" +
                "\n" +
                "\n"+
                "Version 3.2\n"+
                "\n"+
                "Powerd by Across\n"+
                "Developed by Akshay\n"+
                "\n"+

                "(c)2018 Across.All rights reserved.").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }
}
