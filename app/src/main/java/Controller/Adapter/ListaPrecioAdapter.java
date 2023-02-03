package Controller.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;

import Model.Data.Precio;
import androidx.recyclerview.widget.RecyclerView;
import yosva.cu.example.R;


public class ListaPrecioAdapter extends RecyclerView.Adapter<ListaPrecioAdapter.PrecioViewHolder> {


    public static ArrayList<Precio> precios;
    public static Context context;
    public static Precio precio;
    public static ListaPrecioAdapter adapter;
    private static View.OnClickListener listener;


    public ArrayList<Precio> getArrayList() {
        return precios;
    }

    public ListaPrecioAdapter(ArrayList<Precio> precios, Context context) {

       // Log.e("ASD", "" + productos);
        this.precios = precios;
        this.context = context;
        adapter = this;


    }

    @Override
    public PrecioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_list_precio, parent, false);
        return new PrecioViewHolder(v);

    }


    @Override
    public void onBindViewHolder(PrecioViewHolder precioViewHolder, int position) {

        precio = precios.get(position);
        //String id = String.valueOf(post.getIdPosts());
        // postsViewHolder.tvTitle.setText(usserid);
        precioViewHolder.tvTitle.setText(precio.getTitulo());
        String pr = String.valueOf(precio.getPrecio());
        precioViewHolder.tvPrecio.setText(pr);



    }



    @Override
    public int getItemCount() {

        return precios == null ? 0 : precios.size();
    }

    public void setData(ArrayList<Precio> precios) {

        this.precios = precios;
        notifyDataSetChanged();
    }


    public class PrecioViewHolder extends RecyclerView.ViewHolder implements AdapterView.OnClickListener {


        private TextView tvTitle;
        private TextView tvPrecio;



        public PrecioViewHolder(final View itemView) {

            super(itemView);
            //tvUsserId = itemView.findViewById(R.id.tvUsserID);
            // tvID = itemView.findViewById(R.id.tvId);
            tvTitle = itemView.findViewById(R.id.tvtitleprec);
            tvPrecio = itemView.findViewById(R.id.tvprecio);

            //recyclerViewProductos = itemView.findViewById(R.id.rvProductos);

            context = itemView.getContext();
            //itemView.setOnClickListener(this);
//        itemView.setOnClickListener(new View.OnClickListener() {
//                @SuppressLint("RestrictedApi")
//                @Override
//                public void onClick(View view) {
//
//                    int selected = getLayoutPosition();
//                    final int postId= posts.get(selected).getIdPosts();
//
//                    @SuppressLint("RestrictedApi") MenuBuilder menuBuilder = new MenuBuilder(context);
//                    MenuInflater inflater = new MenuInflater(context);
//                    inflater.inflate(R.menu.menu_popou_vercomentposts, menuBuilder);
//                    @SuppressLint("RestrictedApi") MenuPopupHelper optionsMenuComent = new MenuPopupHelper(context, menuBuilder, view);
//                    optionsMenuComent.setForceShowIcon(true);
//
//                    // Set Item Click Listener
//                    menuBuilder.setCallback(new MenuBuilder.Callback() {
//                        @Override
//                        public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
//                            switch (item.getItemId()) {
//                                case R.id.action_verComents: // Handle option1 Click
//                                    Intent inte = new Intent(context, VerComentPost.class);
//                                    inte.putExtra("idPosts", postId);
//                                    context.startActivity(inte);
//                                    //overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
//
//                                    return true;
//                                default:
//                                    return false;
//                            }
//                        }
//
//
//                        @Override
//                        public void onMenuModeChange(MenuBuilder menu) {}
//                    });
//                    // Display the menu
//                    optionsMenuComent.show();
//
//                }
//            });

        }

        @Override
        public void onClick(View v) {
            if (listener != null)
                listener.onClick(v);
        }


    }
}
