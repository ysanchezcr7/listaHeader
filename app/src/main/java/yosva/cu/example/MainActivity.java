package yosva.cu.example;

import Api.RetofitClient;
import Controller.Adapter.ListaProductoAdapterSection;
import Model.Data.CategoriasWithProd;
import Model.Data.Producto;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //private ListaCategoriaAdapter adaptadorCategoria;
    private ListaProductoAdapterSection adaptadorSectiom ;
    private Context context;
    ArrayList<CategoriasWithProd> categorias;

    //@BindView(R.id.rvCategorias)
    RecyclerView listaCategoria;

    //SwipeRefreshLayout refreshLayout;
    ProgressDialog progressDialog ;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkAndRequestPermissions();

       // setAdapter();
        listaCategoria = findViewById(R.id.rvCategorias);
       // listaCategoria.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaCategoria.setLayoutManager(llm);

        inicializarAdaptador();
        lanzarPeticion();


    }


    private void inicializarAdaptador() {
        adaptadorSectiom = new ListaProductoAdapterSection(categorias, this );
        listaCategoria.setAdapter(adaptadorSectiom);
        // adaptador.notifyDataSetChanged();

    }

    private void lanzarPeticion() {
        progressDialog = ProgressDialog.show(this, "",
                "Por favor espere, cargando las Categorias...", true);
       // categorias = new ArrayList<CategoriasWithProd>();
        //final android.app.AlertDialog progressDialog = new SpotsDialog(this, R.style.Custom1);
        //  progressDialog.show();
        // final AlertDialog progressDialog  = new SpotsDialog.Builder().setContext(context).build()

        Call<ArrayList<CategoriasWithProd>> call = RetofitClient
                .getInstance()
                .getApi()
                .getCategorias();
        call.enqueue(new Callback<ArrayList<CategoriasWithProd>>() {
            @Override
            public void onResponse(Call<ArrayList<CategoriasWithProd>> call, Response<ArrayList<CategoriasWithProd>> response) {
                //  Log.d("Respuesta", response.body() + "respuesta");
                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Error"+ response.message() , Toast.LENGTH_LONG).show();
                }

                Toast.makeText(getApplicationContext(),"Succesful Data " , Toast.LENGTH_LONG).show();
               //Log.d("Array ", "r: "+ response.body());
                adaptadorSectiom.setData(response.body());

                if (progressDialog != null) {
                    progressDialog.dismiss();
                    progressDialog = null;

                }
                // progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<ArrayList<CategoriasWithProd>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Ocurrio un error en la red : " + t , Toast.LENGTH_SHORT).show();
                // Log.d("error", t + "sin respuesta");
                if (progressDialog != null) {
                    progressDialog.dismiss();
                    progressDialog = null;

                }
            }
        });

    }

    private  boolean checkAndRequestPermissions() {
        int INTERNETpermission = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
//        int WRITE_EXTERNAL_STORAGEpermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        int READ_EXTERNAL_STORAGEpermission= ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE);
        int ACCESS_WIFI_STATEpermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE);
        int CHANGE_WIFI_STATEpermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CHANGE_WIFI_STATE);
       // int CAMERA = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);


        List<String> listPermissionsNeeded = new ArrayList<>();

        if (INTERNETpermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.INTERNET);
        }
//        if (WRITE_EXTERNAL_STORAGEpermission != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        }
//        if (READ_EXTERNAL_STORAGEpermission != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
//        }
        if (ACCESS_WIFI_STATEpermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_WIFI_STATE);
        }
        if (CHANGE_WIFI_STATEpermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CHANGE_WIFI_STATE);
        }
//        if (CAMERA != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.CAMERA);
//        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }




}