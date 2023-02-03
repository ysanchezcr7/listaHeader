package Controller.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;

import Model.Data.Extra;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import yosva.cu.example.R;


public class ListaExtrasAdapter extends RecyclerView.Adapter<ListaExtrasAdapter.ExtrasViewHolder> {

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    public static ArrayList<Extra> extras;
    public static Context context;
    public static Extra extra;
    public static ListaExtrasAdapter adapter;
    private static View.OnClickListener listener;


    public ArrayList<Extra> getArrayList() {
        return extras;
    }

    public ListaExtrasAdapter(ArrayList<Extra> extras, Context context) {

        // Log.e("ASD", "" + productos);
        this.extras = extras;
        this.context = context;
        adapter = this;


    }

    @Override
    public ExtrasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_list_extra, parent, false);
        return new ExtrasViewHolder(v);

    }


    @Override
    public void onBindViewHolder(ExtrasViewHolder extrasViewHolder, int position) {

        extra = extras.get(position);
        //String id = String.valueOf(post.getIdPosts());
        // postsViewHolder.tvTitle.setText(usserid);
        extrasViewHolder.tvtitleextra.setText(extra.getTitulo());
        extrasViewHolder.tvdescriptionextra.setText(extra.getDescripcion());

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                extrasViewHolder.recyclerViewItems.getContext(),
                LinearLayoutManager.VERTICAL, false
        );
        layoutManager.setInitialPrefetchItemCount(extra.getItems().size());

        // Log.e("lisProd","->" + categoria.getProducto() );
        ListaItemsAdapter listaItemsAdapter = new ListaItemsAdapter(extra.getItems(), context);
        extrasViewHolder.recyclerViewItems.setLayoutManager(layoutManager);
        extrasViewHolder.recyclerViewItems.setAdapter(listaItemsAdapter);
        extrasViewHolder.recyclerViewItems.setRecycledViewPool(viewPool);

        extrasViewHolder.setIsRecyclable(false);



    }



    @Override
    public int getItemCount() {

        return extras == null ? 0 : extras.size();
    }

    public void setData(ArrayList<Extra> extras) {

        this.extras = extras;
        notifyDataSetChanged();
    }


    public class ExtrasViewHolder extends RecyclerView.ViewHolder implements AdapterView.OnClickListener {

        //private ImageView imgFotos;

        private TextView tvtitleextra;
        private TextView tvdescriptionextra;
        private RecyclerView recyclerViewItems;


        public ExtrasViewHolder(final View itemView) {

            super(itemView);
            //tvUsserId = itemView.findViewById(R.id.tvUsserID);
            // tvID = itemView.findViewById(R.id.tvId);
            tvtitleextra = itemView.findViewById(R.id.tvtitleextra);
            tvdescriptionextra = itemView.findViewById(R.id.tvdescriptionextra);
            recyclerViewItems = itemView.findViewById(R.id.rvItems);

            context = itemView.getContext();


        }

        @Override
        public void onClick(View v) {
            if (listener != null)
                listener.onClick(v);
        }


    }
}
