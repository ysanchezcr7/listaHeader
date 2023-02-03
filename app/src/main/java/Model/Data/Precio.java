package Model.Data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Precio  implements Parcelable {

   public  Precio () {
    }
    @SerializedName("titulo")
    private String titulo;
    @SerializedName("descripcion")
    private String descripcion;
    @SerializedName("precio")
    private int precio;

    protected Precio(Parcel in) {
        titulo = in.readString();
        descripcion = in.readString();
        precio = in.readInt();
    }

    public static final Creator<Precio> CREATOR = new Creator<Precio>() {
        @Override
        public Precio createFromParcel(Parcel in) {
            return new Precio(in);
        }

        @Override
        public Precio[] newArray(int size) {
            return new Precio[size];
        }
    };

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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titulo);
        dest.writeString(descripcion);
        dest.writeInt(precio);
    }
}
