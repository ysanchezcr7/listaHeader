package Model.Data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Extra implements Parcelable {
    public Extra () {

    }

    //private int pos =-1;

    @SerializedName("id")
    public String id;

    @SerializedName("titulo")
    public String titulo;

    @SerializedName("descripcion")
    public String descripcion;

    @SerializedName("status")
    public int status;

    @SerializedName("maximo")
    public int maximo;

    @SerializedName("minimo")
    public int minimo;

    @SerializedName("obligatorio")
    public int obligatorio;


    @SerializedName("items")
    public ArrayList<Items> items;

    public ArrayList<Items> getItems() {
        return items;
    }

    public void setItems(ArrayList<Items> items) {
        this.items = items;
    }

    protected Extra(Parcel in) {
        id = in.readString();
        titulo = in.readString();
        descripcion = in.readString();
        status = in.readInt();
        maximo = in.readInt();
        minimo = in.readInt();
        obligatorio = in.readInt();
        items = (ArrayList<Items>) in.readValue(Items.class.getClassLoader());

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMaximo() {
        return maximo;
    }

    public void setMaximo(int maximo) {
        this.maximo = maximo;
    }

    public int getMinimo() {
        return minimo;
    }

    public void setMinimo(int minimo) {
        this.minimo = minimo;
    }

    public int getObligatorio() {
        return obligatorio;
    }

    public void setObligatorio(int obligatorio) {
        this.obligatorio = obligatorio;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(titulo);
        dest.writeString(descripcion);
        dest.writeInt(status);
        dest.writeInt(maximo);
        dest.writeInt(minimo);
        dest.writeInt(obligatorio);
        dest.writeValue(items);
    }
    public static final Creator<Extra> CREATOR = new Creator<Extra>() {
        @Override
        public Extra createFromParcel(Parcel in) {
            return new Extra(in);
        }

        @Override
        public Extra[] newArray(int size) {
            return new Extra[size];
        }
    };


}
