package Model.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import Model.Data.Producto;

public class CategoriasWithProd {

    @SerializedName("categoria")
    @Expose
    private String categoria;

    @SerializedName("productos")
    @Expose
    private ArrayList<Producto> producto;



    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public ArrayList<Producto> getProducto() {
        return producto;
    }

    public void setProducto(ArrayList<Producto> producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "CategoriasWithProd{" +
                "categoria='" + categoria + '\'' +
                ", producto=" + producto +
                '}';
    }
}
