package android.gajju45.AdvanceNews

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofitexample.R
import com.littlemango.stacklayoutmanager.StackLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adapter: NewsAdapter
    private val article = mutableListOf<Article>()
    var pagenum=1
    var totalresult=-1




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = NewsAdapter(this@MainActivity, article)
        newsList.adapter = adapter

        //design
        val layoutManager = StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
        layoutManager.setPagerMode(true)
        layoutManager.setPagerFlingVelocity(3000)
        layoutManager.setItemChangedListener(object : StackLayoutManager.ItemChangedListener {
            override fun onItemChanged(position: Int) {
                container.setBackgroundColor(Color.parseColor(ColorPicker.getColor()))

                //total result ke liye logic
                if (totalresult>layoutManager.itemCount && layoutManager.getFirstVisibleItemPosition() >=layoutManager.itemCount -5){
                    //nextPage
                    pagenum++
                    getNews()
                }
            }

        })
        newsList.layoutManager = layoutManager
        getNews()
    }

    private fun getNews() {
        val news: Call<News> = NewsService.newInstances.getHealines("in", pagenum)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news: News? = response.body()
                if (news != null) {
                    Log.d("Gajju45", news.toString())
                    totalresult=news.totalResults
                    article.addAll(news.articles)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Gajju45", "Error in Fetching News", t)
            }

        })
    }
}



/*
Interface
api or nez contry or page num define
newz or artical class
newz m total num or artical aayenge
artical m jo dikaana vo
jo v krna interface m fun declare krdo bs
 */