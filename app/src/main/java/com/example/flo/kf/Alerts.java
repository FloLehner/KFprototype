package com.example.flo.kf;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.content.Context;

/**
 * Created by flo on 26.04.17.
 */

public class Alerts {


    public void registrationSuccessful(final Context context){
        new AlertDialog.Builder(context)
                .setTitle("Glückwunsch!")
                .setMessage("Sie haben sich erfolgreich am System registriert.")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent successful = new Intent(context, MainActivity.class);
                        context.startActivity(successful);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }



    public void registrationNotSuccessful(final Context context){
        new AlertDialog.Builder(context)
                .setTitle("Es ist ein Fehler aufgetreten")
                .setMessage("Bitte überprüfen Sie Ihre Registrierungsdaten und versuchen Sie es erneut.")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent successful = new Intent(context, RegistrationActivity.class);
                        context.startActivity(successful);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }



    public void userAvailable(final Context context){
        new AlertDialog.Builder(context)
                .setTitle("Login erfolgreich!")
                .setMessage("Sie sind nun am System angemeldet.")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent overview = new Intent(context, OverviewActivity.class);
                        context.startActivity(overview);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }



    public void userNotAvailable(final Context context){
        new AlertDialog.Builder(context)
                .setTitle("Login nicht erfolgreich!")
                .setMessage("Bitte überprüfen Sie ihre Anmeldedaten.")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }



    public void correctAnswer(final Context context){
        new AlertDialog.Builder(context)
                .setTitle("Richtig!")
                .setMessage("Glückwunsch! Sie haben die Frage richtig beantwortet.")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }



    public void falseAnswer(final Context context){
        new AlertDialog.Builder(context)
                .setTitle("Falsch!")
                .setMessage("Leider ist die von Ihnen gewählte Antwort falsch.")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }



    public void allQuestionsfinished(final Context context){
        new AlertDialog.Builder(context)
                .setTitle("Glückwunsch!")
                .setMessage("Sie haben alle Fragen dieses Themas beantwortet. Sie werden nun zur Themübersicht geleitet.")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent backToOverview = new Intent(context, OverviewActivity.class);
                        context.startActivity(backToOverview);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
