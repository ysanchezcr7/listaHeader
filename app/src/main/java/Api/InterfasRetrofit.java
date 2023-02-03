package Api;

import java.util.ArrayList;

import Model.Data.CategoriasWithProd;
import retrofit2.Call;
import retrofit2.http.GET;

//import java.util.List;

public interface InterfasRetrofit {

    @GET("categoriasWithProd.php")
    Call <ArrayList<CategoriasWithProd>> getCategorias();
//
//    @GET("comments")
//    Call <ArrayList<PostsComent>> getPostsComents(
//            @Query("postId") int id);

}
