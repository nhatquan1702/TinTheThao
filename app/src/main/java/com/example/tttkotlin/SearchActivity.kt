package com.example.tttkotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tttkotlin.activity.BodyActivity
import com.example.tttkotlin.adapter.MauTinAdapter
import com.example.tttkotlin.adapter.MauTinAdapterSearch
import com.example.tttkotlin.api.RetrofitInstance
import com.example.tttkotlin.model.MauTin
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_bong_chuyen.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class SearchActivity : AppCompatActivity() {
    lateinit var listMauTin:ArrayList<MauTin>
    lateinit var displayListMauTin:ArrayList<MauTin>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        listMauTin = arrayListOf()
        displayListMauTin = arrayListOf()
        val textSearch : String? = intent.getStringExtra("textSearch")
        loadListView(textSearch.toString())

    }
    override fun onResume() {
        super.onResume()
        shimmerFrameSearch.startShimmer()
    }

    fun loadListView( test:String){
        RetrofitInstance.instance.getListMauTin2().enqueue(object : Callback<ArrayList<MauTin>> {
            override fun onResponse(
                call: Call<ArrayList<MauTin>>,
                response: Response<ArrayList<MauTin>>
            ) {
                response.body()?.let { bao ->
                    listMauTin.addAll(bao)
                    displayListMauTin.clear()
                    listMauTin.forEach{
                        if(it.getTieuDe().contains(test, ignoreCase = true)){

                            displayListMauTin.add(it)
                        }
                    }

                    if(displayListMauTin.size>0){
                        val adapterMauTin = MauTinAdapterSearch(applicationContext, displayListMauTin)
                        listViewSearch.apply {
                            adapter = adapterMauTin
                            setOnItemClickListener { parent, view, position, id ->
                                showChiTietPost(
                                    displayListMauTin.get(position).getIdMauTin().toString()
                                )

                            }
                        }
                        shimmerFrameSearch.apply {
                            stopShimmer()
                            visibility = View.GONE
                        }
                    }
                    else{
                        Toast.makeText(applicationContext, "Không có kết quả tìm kiếm!", Toast.LENGTH_SHORT).show()
                    }

                }
            }

            override fun onFailure(call: Call<ArrayList<MauTin>>, t: Throwable) {
            }

        })
    }

    private fun showChiTietPost(id: String?) {
        val intent = Intent(applicationContext, BodyActivity::class.java)
        intent.putExtra("id_post", id)
        startActivity(intent)
    }
}