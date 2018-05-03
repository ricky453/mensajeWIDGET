package ejemplo.ejemplowidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Implementation of App Widget functionality.
 */
public class MiWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews controles = new RemoteViews(context.getPackageName(), R.layout.mi_widget);

        controles.setTextViewText(R.id.LblMensaje, "Mensaje de prueba");

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, controles);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    public static void actualizarWidget(Context context,
                                        AppWidgetManager appWidgetManager, int widgetId)
    {
        //Recuperamos el mensaje personalizado para el widget actual
        SharedPreferences prefs =
                context.getSharedPreferences("WidgetPrefs", Context.MODE_PRIVATE);
        String mensaje = prefs.getString("msg_" + widgetId, "Hora actual:");

        //Obtenemos la lista de controles del widget actual
        RemoteViews controles =
                new RemoteViews(context.getPackageName(), R.layout.mi_widget);

        //Actualizamos el mensaje en el control del widget
        controles.setTextViewText(R.id.LblMensaje, mensaje);

        //Obtenemos la hora actual
        Calendar calendario = new GregorianCalendar();
        String hora = calendario.getTime().toLocaleString();

        //Actualizamos la hora en el control del widget
        controles.setTextViewText(R.id.LblHora, hora);

        //Notificamos al manager de la actualización del widget actual
        appWidgetManager.updateAppWidget(widgetId, controles);
    }

}

