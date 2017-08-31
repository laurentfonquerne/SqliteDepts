package com.example.administrateur.sqlitedepts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import static com.example.administrateur.sqlitedepts.Tools.*;

public class MainActivity extends AppCompatActivity {
    private EditText txtSearch;
    private EditText txtNoDept;
    private EditText txtNoRegion;
    private EditText txtNom;
    private EditText txtNomStd;
    private EditText txtSurface;
    private EditText txtDateCreation;
    private EditText txtChefLieu;
    private EditText txtUrlWiki;
    //private Departement dept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtSearch       = (EditText) findViewById(R.id.txtSearch);
        txtNoDept       = (EditText) findViewById(R.id.txtNoDept);
        txtNoRegion     = (EditText) findViewById(R.id.txtNoRegion);
        txtNom          = (EditText) findViewById(R.id.txtNom);
        txtNomStd       = (EditText) findViewById(R.id.txtNomStd);
        txtSurface      = (EditText) findViewById(R.id.txtSurface);
        txtDateCreation = (EditText) findViewById(R.id.txtDateCreation);
        txtChefLieu     = (EditText) findViewById(R.id.txtChefLieu);
        txtUrlWiki      = (EditText) findViewById(R.id.txtUrlWiki);

    }

    public void btnSearch(View v) {

        String search = txtSearch.getText().toString();

        if (!search.equals("")) {
            try {
                Departement d = new Departement(this, search);

                txtNoDept.setText(d.getNoDept());
                txtNoRegion.setText(String.valueOf(d.getNoRegion()));
                txtNom.setText(d.getNom());
                txtNomStd.setText(d.getNomStd());
                txtSurface.setText(String.valueOf(d.getSurface()));
                txtDateCreation.setText(datToStr(d.getDateCreation()));
                txtChefLieu.setText(d.getChefLieu());
                txtUrlWiki.setText(d.getUrlWiki());
                txtSearch.setEnabled(false);
            } catch (Exception ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
        else
            Toast.makeText(this, "pas de départements saisi", Toast.LENGTH_LONG).show();
    }

    public void btnClear(View v) {

        txtSearch.setText("");
        txtNoDept.setText("");
        txtNoRegion.setText("");
        txtNom.setText("");
        txtNomStd.setText("");
        txtSurface.setText("");
        txtDateCreation.setText("");
        txtChefLieu.setText("");
        txtUrlWiki.setText("");
        txtSearch.setEnabled(true);

    }
    //public void btnDelete(View v){
    //    deleteDatabase("geo");
    //}
    public void btnDelete(View v) {

        String search = txtNoDept.getText().toString();

        if (!search.isEmpty()) {
            try {
                Departement d = new Departement(this, search);
                d.delete();
                Toast.makeText(this, search + " delete ok", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.getMessage();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }
        else {
            try {
                search = txtSearch.getText().toString();
                Departement d = new Departement(this, search);
                d.delete();
                Toast.makeText(this, search + " delete ok", Toast.LENGTH_LONG).show();
            } catch (Exception ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
            }


        }
    }
    public void btnTest(View v) {

        Departement d = new Departement(this);

        try {
            d.setNoDept("101");
            d.setNoRegion(93);
            d.setNom("Nouveau dept - test");
            d.setNomStd("NOUVEAU DEPT");
            d.setSurface(5000);
            d.setDateCreation("2017-01-01");
            d.setChefLieu("Ville");
            d.setUrlWiki("http://");
            d.insert();
        } catch (Exception e) {
            e.getMessage();
        }


        Toast.makeText(this, d.getNom(), Toast.LENGTH_LONG).show();

    }
    public void btnSave(View v) throws Exception {

        Departement d  = new Departement(this);
        d.setNoDept(txtNoDept.getText().toString());
        d.setNoRegion(Integer.parseInt(txtNoRegion.getText().toString()));
        d.setNom(txtNom.getText().toString());
        d.setNomStd(txtNomStd.getText().toString());
        d.setSurface(Integer.parseInt(txtSurface.getText().toString()));
        d.setDateCreation(txtDateCreation.getText().toString());
        d.setChefLieu(txtChefLieu.getText().toString());
        d.setUrlWiki(txtUrlWiki.getText().toString());

        try {
                Departement d2  = new Departement(this, txtNoDept.getText().toString() );
                d.update();
                d.setNoDept(txtNoDept.getText().toString());
                d.setNoRegion(Integer.parseInt(txtNoRegion.getText().toString()));
                d.setNom(txtNom.getText().toString());
                d.setNomStd(txtNomStd.getText().toString());
                d.setSurface(Integer.parseInt(txtSurface.getText().toString()));
                d.setDateCreation(txtDateCreation.getText().toString());
                d.setChefLieu(txtChefLieu.getText().toString());
                d.setUrlWiki(txtUrlWiki.getText().toString());
                Toast.makeText(this, "Dept mis à jour", Toast.LENGTH_LONG).show();
            }
        catch (Exception e) {e.getMessage();
                try { d.insert(); } catch (Exception ex2)
                {
                    Toast.makeText(this, "???", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(this, "Dept ajouté", Toast.LENGTH_LONG).show();
            }

        }



}



