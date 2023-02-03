package Controller.Adapter;


import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import Model.Data.CategoriasWithProd;

import Model.Data.Extra;
import Model.Data.Precio;
import Model.Data.Producto;
import androidx.cardview.widget.CardView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import yosva.cu.example.DetallesProducto;
import yosva.cu.example.R;


public class ListaProductoAdapterSection extends SectionedRecyclerViewAdapter<RecyclerView.ViewHolder> {

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();


    public static ArrayList<CategoriasWithProd> categorias;
    public static Context context;
    public static CategoriasWithProd categoria;
    public static ListaProductoAdapterSection adapter;
    private static View.OnClickListener listener;



    public ArrayList<CategoriasWithProd> getArrayList() {
        return categorias;
    }

    public ListaProductoAdapterSection(ArrayList<CategoriasWithProd> categorias, Context context) {
        // Log.e("ASD", "" + productos);
        this.categorias = categorias;
        this.context = context;

    }
    public void setData(ArrayList<CategoriasWithProd> listcats) {

        this.categorias = listcats;
        //  Log.d("productos","" + listcats);
        notifyDataSetChanged();
    }


    @Override
    public int getSectionCount() {
        return categorias == null ? 0 : categorias.size();

        //return categorias.size();
        //return 0;
    }

    @Override
    public int getItemCount(int section) {
        return categorias.get(section).getProducto().size();
        //return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, boolean header) {
        if (header) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.item_recycler_section, parent, false);
            return new SectionViewHolder(view);
        } else {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.element_list_productos, parent, false);
            return new ProductoViewHolder(view);
        }

    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int section) {
       // String sectionName = Util.getDateAndTime(checkoutHistories.get(section));
        //producto = productos.get(position);
        categoria = categorias.get(section);
        String sectionName = categoria.getCategoria();
        SectionViewHolder sectionViewHolder = (SectionViewHolder) holder;
        sectionViewHolder.txtHeader.setText(sectionName);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int section, int relativePosition, int absolutePosition) {

        final Producto producto = categorias.get(section).getProducto().get(relativePosition);
        final ProductoViewHolder itemViewHolder =  (ProductoViewHolder) holder;
        //String id = String.valueOf(post.getIdPosts());
        // postsViewHolder.tvTitle.setText(usserid);
      //  String itemEventName = producto.getTitulo();
        itemViewHolder.tvTitle.setText(producto.getTitulo());
        itemViewHolder.tvdescription.setText(producto.getDescripcion());
       //final String url = producto.getUrlImagen();
        //Glide.with(context).load(producto.getUrlImagen()).into(productoViewHolder.imgProducto);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .circleCrop()
                .error(R.mipmap.ic_launcher_round);


        Glide.with(context).load(producto
                .getUrlImagen())
                .apply(options)
                .into(itemViewHolder.imgProducto);

        /*LinearLayoutManager layoutManager = new LinearLayoutManager(
                productoViewHolder.recyclerViewPrecio.getContext(),
                LinearLayoutManager.HORIZONTAL, false
        );*/
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                itemViewHolder.recyclerViewPrecio.getContext(),
                LinearLayoutManager.HORIZONTAL, false
        );
        layoutManager.setInitialPrefetchItemCount(producto.getPrecios().size());

        // Log.e("lisProd","->" + categoria.getProducto() );
        ListaPrecioAdapter listaPrecAdapter = new ListaPrecioAdapter(producto.getPrecios(), context);
        itemViewHolder.recyclerViewPrecio.setLayoutManager(layoutManager);
        itemViewHolder.recyclerViewPrecio.setAdapter(listaPrecAdapter);
        itemViewHolder.recyclerViewPrecio.setRecycledViewPool(viewPool);
        itemViewHolder.setIsRecyclable(true);

        itemViewHolder.linCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String url = producto.getUrlImagen();
                final String titulo = producto.getTitulo();
                final String description = producto.getDescripcion();
                //ArrayList<String> milista = new ArrayList<String>();
                final ArrayList<Precio> pre =producto.getPrecios();

                final ArrayList<Extra> extras = producto.getExtras();

                Intent inte = new Intent(context, DetallesProducto.class);
                inte.putExtra("url", url);
                inte.putExtra("titulo", titulo);
                inte.putExtra("descrition", description);
                inte.putParcelableArrayListExtra("arrayPre", pre);
                inte.putParcelableArrayListExtra("arrayExtras", extras);
                context.startActivity(inte);
               // Toast.makeText(context, "titulo "  + titulo, Toast.LENGTH_LONG).show();

            }
        });
    }

    class SectionViewHolder extends RecyclerView.ViewHolder {

        //@BindView(R.id.txt_header)
        TextView txtHeader;

        SectionViewHolder(View itemView) {

            super(itemView);
            txtHeader = itemView.findViewById(R.id.txt_header);
           // ButterKnife.bind(this, itemView);
        }
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder implements AdapterView.OnClickListener {

        //private ImageView imgFotos;

        private ImageView imgProducto;
        private TextView position;
        private TextView tvTitle;
        private TextView tvdescription;
        private RecyclerView recyclerViewPrecio;
        private CardView linCard;

        public ProductoViewHolder(final View itemView) {

            super(itemView);
           // ButterKnife.bind(this, itemView);
            position = itemView.findViewById(R.id.position);
            tvTitle = itemView.findViewById(R.id.tvtitleprod);
            tvdescription = itemView.findViewById(R.id.tvdescription);
            imgProducto = itemView.findViewById(R.id.imgProducto);
            recyclerViewPrecio = itemView.findViewById(R.id.rvPrecio);
            linCard = itemView.findViewById(R.id.cardProd);

            //tvUsserId = itemView.findViewById(R.id.tvUsserID);
            // tvID = itemView.findViewById(R.id.tvId);
            //position = itemView.findViewById(R.id.position);
           /* tvTitle = itemView.findViewById(R.id.tvtitleprod);
            tvdescription = itemView.findViewById(R.id.tvdescription);
            imgProducto = itemView.findViewById(R.id.imgProducto);
            recyclerViewPrecio = itemView.findViewById(R.id.rvPrecio);
            item_show = itemView.findViewById(R.id.cardProd);*/
            //context = itemView.getContext();
            //  itemView.setOnClickListener(this);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @SuppressLint("RestrictedApi")
//                @Override
//                public void onClick(View view) {
//
//                    int selected = getLayoutPosition();
//                   // int selected = Integer.parseInt(position.getText().toString());
//
//                    final String url = .get(selected).getUrlImagen();
//                    final String titulo = productos.get(selected).getTitulo();
//                    final String description = productos.get(selected).getDescripcion();
//                    //ArrayList<String> milista = new ArrayList<String>();
//                    final ArrayList<Precio> pre = productos.get(selected).getPrecios();
//
//                    final ArrayList<Extra> extras = productos.get(selected).getExtras();
//                   // ArrayList<Items> it ;
//                   // it = extras.get(selected).getItems();
//                    //Log.e("item","" + item);
//                    //Log.e("extras","e: "+ extras.get(selected).getItems());
//                   Toast.makeText(context,"pocision "+ selected +  titulo , Toast.LENGTH_LONG).show();
//
//                    //final String postId= productos.get(selected).getIdPosts();
//
//                  /*  @SuppressLint("RestrictedApi") MenuBuilder menuBuilder = new MenuBuilder(context);
//                    MenuInflater inflater = new MenuInflater(context);
//                    inflater.inflate(R.menu.menu_popou_verdetall_producto, menuBuilder);
//                    @SuppressLint("RestrictedApi") MenuPopupHelper optionsMenuComent = new MenuPopupHelper(context, menuBuilder, view);
//                    optionsMenuComent.setForceShowIcon(true);
//
//                    // Set Item Click Listener
//                    menuBuilder.setCallback(new MenuBuilder.Callback() {
//                        @Override
//                        public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
//                            switch (item.getItemId()) {
//                                case R.id.action_detallesProducto: // Handle option1 Click
//
//                                    Intent inte = new Intent(context, DetallesProducto.class);
//                                    inte.putExtra("url", url);
//                                    inte.putExtra("titulo", titulo);
//                                    inte.putExtra("descrition", description);
//                                   // inte.putExtra("selected", selected);
//                                    inte.putParcelableArrayListExtra("arrayPre", pre);
//                                    inte.putParcelableArrayListExtra("arrayExtras", extras);
//                                   // inte.putParcelableArrayListExtra("arrayIt", it);
//                                    //inte.putExtra("arrayPrecios", precios);
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
//                        public void onMenuModeChange(MenuBuilder menu) {
//                        }
//                    });
//                    // Display the menu
//                    optionsMenuComent.show();*/
//
//                }
//            });

        }

        @Override
        public void onClick(View v) {
            if (listener != null)
                listener.onClick(v);
//            int position = getAdapterPosition();
//            Toast.makeText(context, ""+productos.get(position).getTitulo(), Toast.LENGTH_SHORT).show();

        }


    }
}
