package com.example.administrateur.sqlitedepts;

/**
 * Created by Administrateur on 29/08/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.administrateur.sqlitedepts.Tools.*;


/**
 * Created by Administrateur on 29/08/2017.
 */

public class Departement {

    private SQLiteDatabase db;
    private Context ctxt;
    private static final String TABLE_NAME = "departements";
    private int noRegion, surface;
    private String noDept, nom, nomStd, chefLieu, urlWiki;
    private Date dateCreation;

    public Departement(Context c, String no) throws Exception{

        this(c);
        select(no);

    }

    public Departement(Context context){

        super();
        this.ctxt = context;

        DbInit initDb = DbInit.getInstance(ctxt);
        db = initDb.getWritableDatabase();


    }

//region propriétés

    public void setNoDept(String value)throws DbException{

        if (value.equals(""))
            throw new DbException("No Departement non renseigné");

        //l'exception bloque le traitement.


// 1er char de 0à9
// 2eme char de 0 a 9 (ou A ou B)
// 3eme char facultatif (0 à 9 pour les dom tom)

        Pattern pattern = Pattern.compile("[0-9][0-9AB][0-9]?");
        Matcher matcher = pattern.matcher(value);

        if (!matcher.find()){
            throw new DbException(ctxt.getString(R.string.errBadNoDept));
        }

        this.noDept = value;

    }

    public String getNoDept() {
        return noDept;
    }


    public void setNoRegion(int noRegion) throws DbException{
        if (noRegion != 0)
            this.noRegion = noRegion;
        else
            throw new DbException("No Région non renseigné");
    }

    public Integer getNoRegion() {
        return noRegion;
    }

    public void setNom(String value) throws DbException{
        if (!value.equals(""))
            this.nom = value;
        else
            throw new DbException("Nom Dept non renseigné");
    }

    public String getNom() {
        return nom;
    }

    public void setNomStd(String value) throws DbException{
        if (!value.equals(""))
            this.nomStd = value;
        else
            throw new DbException("Nom Std Dept non renseigné");
    }

    public String getNomStd() {
        return nomStd;
    }

    public void setSurface(int surface) throws DbException{
        if (surface != 0)
            this.surface = surface;
        else
            throw new DbException("Surface non renseignée");
    }

    public Integer getSurface() {
        return surface;
    }

    public void setDateCreation(String value)throws Exception{

        dateCreation = strToDat(value);

    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setChefLieu(String value)throws DbException{
        if (!value.equals(""))
            this.chefLieu = value;
        else
            throw new DbException("Chef Lieu non renseigné");
    }

    public String getChefLieu() {
        return chefLieu;
    }

    public void setUrlWiki(String value)throws DbException{
        if (!value.equals(""))
            this.urlWiki = value;
        else
            throw new DbException("Url Dept non renseigné");
    }

    public String getUrlWiki() {
        return urlWiki;
    }
//endregion propriétés

//les méthodes select :

    public void select(String no) throws Exception {

        String where = "no_dept = '" + no + "' ";
        String[] colonnes = {"no_dept","no_region","nom","nom_std","surface","date_creation","chef_lieu","url_wiki"};

        Cursor curs = db.query(false,TABLE_NAME, colonnes, where, null,null,null,null,null);

        //Toast.makeText(ctxt,"select " + "from " + TABLE_NAME + " " + where,Toast.LENGTH_LONG).show();
        if (!curs.moveToFirst()) {
            throw new DbDeptNotFoundException();
        }

        if (curs.moveToFirst()) {
                this.noDept       = curs.getString(0);
                this.noRegion     = curs.getInt(1);
                this.nom          = curs.getString(2);
                this.nomStd       = curs.getString(3);
                this.surface      = curs.getInt(4);
                this.chefLieu     = curs.getString(6);
                this.urlWiki      = curs.getString(7);
            try {
                this.dateCreation = sqlToDat(curs.getString(5));}
            catch(Exception ex){}
        }

            else
                Toast.makeText(ctxt, "département non trouvé " + no, Toast.LENGTH_LONG).show();


    }

    public void delete()throws DbException{

        Toast.makeText(ctxt, "dept " + this.noDept , Toast.LENGTH_LONG).show();

        if (this.noDept.equals(null)) {
            throw new DbException("Departement non initialisé");
        }
        String where = "no_dept = '" + this.noDept + "' ;";
        db.delete(TABLE_NAME, where, null);

    }
    public void update() throws Exception {

        if (this.noDept.equals("")){
            throw new DbException("departement non initialisé");
        }
        ContentValues values = iniValues();
        String where = "no_dept = " + this.noDept;

        db.update(TABLE_NAME,values,where,null);
    }
    private ContentValues iniValues() throws Exception {

        ContentValues values = new ContentValues();
        values.put("no_dept",this.noDept);
        values.put("no_region",this.noRegion);
        values.put("nom",this.nom);
        values.put("nom_std",this.nomStd);
        values.put("surface",this.surface);
        values.put("date_creation",datToSql(this.dateCreation));
        values.put("chef_lieu",this.chefLieu);
        values.put("url_wiki",this.urlWiki);
        return values;

    }

    public void insert()throws Exception{

        if (this.noDept.equals("")){
            throw new DbException("departement non initialisé");
        }
        ContentValues values = iniValues();
        db.insert(TABLE_NAME, null, values);
    }

    public class DbException extends Exception {
        public DbException(String msg){
            super(msg);
        }
        public DbException(Context ctxt, int stringId){
            super(ctxt.getString(stringId));
        }
    }
    public class DbDeptNotFoundException extends DbException {
        public DbDeptNotFoundException(){
            super(ctxt, R.string.errDeptNotFound);}
        }

 }


