package Model.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import Model.Data.Extra;
import Model.Data.Precio;

public class Producto  implements Serializable  {

    private int pos =-1;

    public Producto(){

    }

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("tiendaId")
    @Expose
    private String tiendaId;

    @SerializedName("titulo")
    @Expose
    private String titulo;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    @SerializedName("urlImagen")
    @Expose
    private String urlImagen;

    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("descuento")
    @Expose
    private int descuento;

    @SerializedName("precios")
    @Expose
    private ArrayList<Precio> precios;

    @SerializedName("extras")
    @Expose
    private ArrayList<Extra> extras;


    public ArrayList<Extra> getExtras() {
        return extras;
    }

    public void setExtras(ArrayList<Extra> extras) {
        this.extras = extras;
    }

    public ArrayList<Precio> getPrecios() {
        return precios;
    }

    public void setPrecios(ArrayList<Precio> precios) {
        this.precios = precios;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTiendaId() {
        return tiendaId;
    }

    public void setTiendaId(String tiendaId) {
        this.tiendaId = tiendaId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getPos() {
        return pos;
    }
}
