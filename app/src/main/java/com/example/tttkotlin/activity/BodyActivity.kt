package com.example.tttkotlin.activity

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RatingBar
import android.widget.Toast
import coil.api.load
import com.example.tttkotlin.R
import com.example.tttkotlin.api.RetrofitInstance
import com.example.tttkotlin.model.MauTin
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.android.synthetic.main.activity_body.*
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class BodyActivity : AppCompatActivity() {
    lateinit var mauTin: MauTin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_body)
        var actionBar = getSupportActionBar()
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        val id_post : String? = intent.getStringExtra("id_post")
        //val a: String = "https://apisimpleappp.herokuapp.com/post/" + id_post
        //ReadJSON().execute(url)
   //     Log.d("dfds", id_post.toString())
        id_post?.let{

            loadBodyMauTin(it)

        }
//        rtbDGBody.setOnRatingBarChangeListener(object : RatingBar.OnRatingBarChangeListener {
//            override fun onRatingChanged(p0: RatingBar?, p1: Float, p2: Boolean) {
////                RetrofitInstance.instance.danhGiaBaiViet(rtbDGBody.getRating()).enqueue(object : Callback<ArrayList<MauTin>>{
////                    override fun onResponse(call: Call<ArrayList<MauTin>>, response: Response<ArrayList<MauTin>>) {
//                        Toast.makeText(applicationContext,"Đánh giá "+ rtbDGBody.getRating().toString()+" sao thành công!", Toast.LENGTH_SHORT).show()
////                    }
////
////                    override fun onFailure(call: Call<ArrayList<MauTin>>, t: Throwable) {
////                        Toast.makeText(applicationContext,"Đánh giá thất bại: "+ t, Toast.LENGTH_SHORT).show()
////                    }
////
////                })
//
//            }
//        })
    }
    fun loadBodyMauTin(id_post : String){
        RetrofitInstance.instance.getMauTin(id_post).enqueue(object : Callback<ArrayList<MauTin>>{
            override fun onResponse(call: Call<ArrayList<MauTin>>, response: Response<ArrayList<MauTin>>) {
                response.body()?.let {
                    tvTieuDeBody.setText(it.get(0).getTieuDe())
                    tvNoiDungBody.setText(it.get(0).getNoiDung())
                    tvNgayTaoBody.setText((it.get(0).getSoPhut()).substring(0, 16))
                    imgHinhAnhBody.load(it.get(0).getHinhAnh())
                    //rtbDGBody.setRating(it.get(0).getDanhGia().toFloat());
                    tvTacGiaBody.setText(it.get(0).getNguoiTao())
                    //shimmerFrame.apply {
//                        stopShimmer()
//                        visibility = View.GONE
    //                }
                }
            }

            override fun onFailure(call: Call<ArrayList<MauTin>>, t: Throwable) {

            }

        })
    }

//    override fun onResume() {
//        super.onResume()
//        shimmerFrame.startShimmer()
//    }

//    inner class ReadJSON : AsyncTask<String, Void, String>() {
//        override fun doInBackground(vararg params: String?): String {
//            var content: StringBuffer = StringBuffer()
//            val url: URL = URL(params[0])
//            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
//            var inputStreamReader: InputStreamReader = InputStreamReader(urlConnection.inputStream)
//            val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
//            var line: String = ""
//            try {
//                do {
//                    line = bufferedReader.readLine()
//                    if (line != null) {
//                        content.append(line)
//                    }
//                } while (line != null)
//                bufferedReader.close()
//            } catch (e: Exception) {
//                Log.d("loi", e.toString())
//            }
//            return content.toString()
//        }
//
//        override fun onPostExecute(result: String?) {
//            super.onPostExecute(result)
//            val jsonArray: JSONArray = JSONArray(result)
//            val listMauTin: ArrayList<MauTin> = ArrayList()
//            for (i in 0..jsonArray.length() - 1) {
//                var jsonOb: JSONObject = jsonArray.getJSONObject(i)
//                tvTieuDeBody.setText(jsonOb.getString("title"))
//                tvNoiDungBody.setText(jsonOb.getString("content"))
//                tvNgayTaoBody.setText((jsonOb.getString("create_time")).substring(0, 16))
//                imgHinhAnhBody.load(jsonOb.getString("img"))
//                rtbDGBody.setRating(jsonOb.getString("rating").toFloat());
//                tvTacGiaBody.setText(jsonOb.getString("username"))
//            }
//        }
//    }
}