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
            "http://www.irta.cat/es-es/RIT/Centres/paginas/Cabrils.aspx",
            "http://www.cresa.es",
            "http://www.irta.cat/es-es/RIT/Centres/paginas/Estaci%C3%B3ExperimentaldeLleida.aspx", //ERROR
            "http://www.irta.cat/es-es/RIT/Centres/paginas/Estaci%C3%B3ExperimentaldeLleida.aspx", //ERROR
            "http://www.irta.cat/es-es/RIT/Centres/paginas/Estaci%C3%B3ExperimentaldeLleida.aspx", //ERROR
            "http://www.irta.cat/es-es/RIT/Centres/paginas/Estaci%C3%B3_Experimental_de_l'Ebre.aspx", //ERROR
            "http://www.irta.cat/es-es/RIT/Centres/paginas/EstacioExperimentalMasBadia.aspx",
            "http://www.irta.cat/es-es/RIT/Centres/paginas/Fruitcentre.aspx",
            "http://www.irta.cat/es-es/RIT/Centres/paginas/EstacioExperimentalAlcarras.aspx",
            "http://www.irta.cat/es-es/RIT/Centres/paginas/MasdeBover.aspx",
            "http://www.irta.cat/es-es/RIT/Centres/paginas/Monells.aspx",
            "http://www.irta.cat/es-es/RIT/Centres/paginas/SantCarlesdelaR%C3%A0pita.aspx", //ERROR
            "http://www.irta.cat/en-US/RIT/Centres/Pages/TorreMarimon.aspx",
            "http://www.cragenomica.es/",
            "http://www.irta.cat/es-es/RIT/Centres/paginas/CREDA.aspx"
    };

    String[] center_list_locations = {
            "geo:41.5164019,2.3774935?q=41.5164019,2.3774935(Irta Cambrils)",
            "geo:41.5047853,2.0966397?q=41.5047853,2.0966397(CReSA)",
            "geo:41.656208,0.389714?q=41.656208,0.389714(Finca Gimenells)",
            "geo:41.617399,0.870172?q=41.617399,0.870172(Finca Mollerussa)",
            "geo:41.617399,0.870172?q=41.617399,0.870172(Finca Borges Blanques)",
            "geo:40.7082306,0.6323306?q=40.7082306,0.6323306(Estació Experimental del Ebre)",
            "geo:42.054152,3.061881?q=42.054152,3.061881(Estació Experimental Mas Badia)",
            "geo:41.6049364,0.6056739?q=41.6049364,0.6056739(Fruitcentre)",
            "geo:41.602478,0.525798?q=41.602478,0.525798(Granja Experimental d’Alcarràs)",
            "geo:41.169851,1.169335?q=41.169851,1.169335(Mas de Bover)",
            "geo:41.9785593,2.9941466?q=41.9785593,2.9941466(Irta Monells)", //check
            "geo:40.627698,0.659952?q=40.627698,0.659952(Sant Carles de la Ràpita)",
            "geo:41.6134271,2.1709251?q=41.6134271,2.1709251(Torre Marimon)",
            "geo:41.4976917,2.1080263?q=41.4976917,2.1080263(CRAG)",
            "geo:41.276581,1.9873431?q=41.276581,1.9873431(CREDA)"
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
