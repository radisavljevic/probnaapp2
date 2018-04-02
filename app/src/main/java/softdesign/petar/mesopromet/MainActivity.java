package softdesign.petar.mesopromet;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigationView;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){

            PocetnaFragment fragment = new PocetnaFragment();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.layout_for_fragments, fragment, "1");
            transaction.commit();

        }
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawar_layout);
        mToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                R.string.open,
                R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
        //getSupportActionBar().setSubtitle(subTitle);
    }
    public void setActionBarTitle(String title,String subTitle) {
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setSubtitle(subTitle);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.pocetna) {

            final PocetnaFragment fragment = new PocetnaFragment();
            final FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.layout_for_fragments, fragment,"1");
            transaction.commit();


        } else if (id == R.id.artikli) {

            Fragment fragment = new ArtikliFragment();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.layout_for_fragments, fragment,"2");
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.trebovanje) {

            TrebovanjeFragment fragment = new TrebovanjeFragment();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.layout_for_fragments, fragment,"3");
            transaction.addToBackStack(null);
            transaction.commit();


        } else if (id == R.id.istorija) {

            IstorijaFragment fragment = new IstorijaFragment();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.layout_for_fragments, fragment,"4");
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.odjava) {

            dialog = new Dialog(this);
            dialog.setContentView(R.layout.custom_dialog);
            dialog.setTitle("ODJAVA");


            Button dialogButton = (Button) dialog.findViewById(R.id.dugmeOK);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Narudzbina.listaGrupeZaSlanje.clear();
                    finish();
                    dialog.dismiss();
                }
            });
            Button dialogButton2 = (Button) dialog.findViewById(R.id.dugme);
            dialogButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog.dismiss();
                }
            });

            dialog.show();
            dialog.setCancelable(false);

        }
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawar_layout);
        mDrawerLayout.closeDrawers();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentByTag("1");
        // FragmentManager fm2 = getSupportFragmentManager();
        // Fragment frag2 = fm2.findFragmentByTag("2");

        if(frag.isVisible()){

            dialog = new Dialog(this);
            dialog.setContentView(R.layout.custom_dialog);

            Button dialogButton = (Button) dialog.findViewById(R.id.dugmeOK);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Narudzbina.listaGrupeZaSlanje.clear();
                    finish();
                    dialog.dismiss();
                }
            });
            Button dialogButton2 = (Button) dialog.findViewById(R.id.dugme);
            dialogButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog.dismiss();
                }
            });
            dialog.show();
            dialog.setCancelable(false);

        }else
            super.onBackPressed();
    }
}
