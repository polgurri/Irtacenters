package cat.irta.irtacenters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class IrtaCentersList extends ActionBarActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    String[] center_list = {
            "Cabrils",
            "CReSA\n(Centre de Recerca en Sanitat Animal)",
            "Estació Experimental de Lleida\n(Finca Gimenells)",
            "Estació Experimental de Lleida\n(Finca Mollerussa)",
            "Estació Experimental de Lleida\n(Finca Borges Blanques)",
            "Estació Experimental del Ebre",
            "Estació Experimental Mas Badia",
            "Fruitcentre",
            "Granja Experimental d’Alcarràs",
            "Mas de Bover",
            "Monells",
            "Sant Carles de la Ràpita",
            "Torre Marimon",
            "CRAG\n(Centre de Recerca en Agrigenòmica)",
            "CREDA\n(Centre de Recerca en Economia i Desenvolupament Agroalimentari)"
    };

    String[] center_list_webs = {
            "http://www.0.cat",
            "http://www.1.cat",
            "http://www.2.cat",
            "http://www.irta.cat",
            "http://www.irta.cat",
            "http://www.irta.cat",
            "http://www.irta.cat",
            "http://www.irta.es",
            "http://www.irta.cat",
            "http://www.irta.cat",
            "http://www.irta.cat",
            "http://www.irta.cat",
            "http://www.irta.cat/en-US/RIT/Centres/Pages/TorreMarimon.aspx",
            "http://www.irta.cat",
            "http://www.irta.cat"
    };

    String[] center_list_locations = {
            "google.streetview:cbll=46.414382,10.013988",
            "google.streetview:cbll=46.414382,10.013988",
            "http://www.2.cat",
            "http://www.irta.cat",
            "http://www.irta.cat",
            "http://www.irta.cat",
            "http://www.irta.cat",
            "http://www.irta.cat",
            "http://www.irta.cat",
            "http://www.irta.cat",
            "http://www.irta.cat",
            "http://www.irta.cat",
            "geo:41.6134271,2.1709251?q=41.6134271,2.1709251(Torre Marimon)",
            "http://www.irta.cat",
            "http://www.irta.cat"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irta_centers_list);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);


        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                show_Dialog(childPosition);

                return false;
            }
        });

    }


   public void show_Dialog(final int position) {

       AlertDialog.Builder builder = new AlertDialog.Builder(this);

       builder.setMessage(center_list[position])
                .setCancelable(true)
                .setPositiveButton("Web page", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        web_page_activity(position);
                    }
                })

                .setNegativeButton("Maps", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        maps_activity(position);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

   private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Irta Centers");


        // Adding child data
        List<String> irtaCenters = new ArrayList<String>();
        irtaCenters.add(center_list[0]);
        irtaCenters.add(center_list[1]);
        irtaCenters.add(center_list[2]);
        irtaCenters.add(center_list[3]);
        irtaCenters.add(center_list[4]);
        irtaCenters.add(center_list[5]);
        irtaCenters.add(center_list[6]);
        irtaCenters.add(center_list[7]);
        irtaCenters.add(center_list[8]);
        irtaCenters.add(center_list[9]);
        irtaCenters.add(center_list[10]);
        irtaCenters.add(center_list[11]);
        irtaCenters.add(center_list[12]);
        irtaCenters.add(center_list[13]);
        irtaCenters.add(center_list[14]);

        listDataChild.put(listDataHeader.get(0), irtaCenters); // Header, Child data
    }

   private void maps_activity(int number){
       Uri gmmIntentUri = Uri.parse(center_list_locations[number]);
       Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
       mapIntent.setPackage("com.google.android.apps.maps");
       startActivity(mapIntent);
   }

   private void web_page_activity(int number){
       String url = center_list_webs[number];
       Intent i = new Intent(Intent.ACTION_VIEW);
       i.setData(Uri.parse(url));
       startActivity(i);
   }

}
