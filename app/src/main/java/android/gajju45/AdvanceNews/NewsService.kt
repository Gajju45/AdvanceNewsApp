package android.gajju45.AdvanceNews

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
//
//http://newsapi.org/v2/top-headlines?country=in&apiKey=API_KEY
//API KEY=1d0290aec40e4e15be033ae6d902cfa4

const val BASE_URL="https://newsapi.org/"
const val API_KEY="1d0290aec40e4e15be033ae6d902cfa4"


interface NewsInterface {

    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHealines(@Query("country") country:String, @Query("page") Page:Int):Call<News>

    //http://newsapi.org/v2/top-headlines?apiKey=$API_KEY&Country=in&page=1
}

object NewsService{
    val newInstances: NewsInterface
    init {
        //retrofit object
        val retrofit:Retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newInstances =retrofit.create(NewsInterface::class.java)
    }
}
