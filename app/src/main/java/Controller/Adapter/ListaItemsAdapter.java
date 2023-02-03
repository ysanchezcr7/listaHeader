package Controller.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;

import Model.Data.Items;
import androidx.recyclerview.widget.RecyclerView;
import yosva.cu.example.R;


public class ListaItemsAdapter extends RecyclerView.Adapter<ListaItemsAdapter.ItemsViewHolder> {


    public static ArrayList<Items> items;
    public static Context context;
    public static Items item;
    public static ListaItemsAdapter adapter;
    private static View.OnClickListener listener;


    public ArrayList<Items> getArrayList() {
        return items;
    }

    public ListaItemsAdapter(ArrayList<Items> items, Context context) {

       // Log.e("ASD", "" + productos);
        this.items = items;
        this.context = context;
        adapter = this;


    }

    @Override
    public ItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_list_items, parent, false);
        return new ItemsViewHolder(v);

    }


    @Override
    public void onBindViewHolder(ItemsViewHolder itViewHolder, int position) {

        item = items.get(position);
        //String id = String.valueOf(post.getIdPosts());
        // postsViewHolder.tvTitle.setText(usserid);
        itViewHolder.tvNombre.setText(item.getNombre());
        String pr = String.valueOf(item.getPrecio());
        itViewHolder.tvPrecio.setText(pr);

        // itemViewHolder.tvPrecio.setText(pr);



    }



    @Override
    public int getItemCount() {

        return items == null ? 0 : items.size();
    }

    public void setData(ArrayList<Items> items) {

        this.items = items;
        notifyDataSetChanged();
    }


    public class ItemsViewHolder extends RecyclerView.ViewHolder implements AdapterView.OnClickListener {


        private TextView tvNombre;
        private TextView tvPrecio;



        public ItemsViewHolder(final View itemView) {

            super(itemView);
            //tvUsserId = itemView.findViewById(R.id.tvUsserID);
            // tvID = itemView.findViewById(R.id.tvId);
            tvNombre = itemView.findViewById(R.id.tvnombreitem);
            tvPrecio = itemView.findViewById(R.id.tvprecioitem);

            //recyclerViewProductos = itemView.findViewById(R.id.rvProductos);

            context = itemView.getContext();


        }

        @Override
        public void onClick(View v) {
            if (listener != null)
                listener.onClick(v);
        }


    }
}
