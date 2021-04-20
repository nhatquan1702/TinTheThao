package com.example.tttkotlin.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.tttkotlin.R
import com.example.tttkotlin.activity.BodyActivity
import com.example.tttkotlin.adapter.MauTinAdapter
import com.example.tttkotlin.model.MauTin
import com.example.tttkotlin.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BongDaFragment : Fragment() {
    lateinit var listMauTin:ArrayList<MauTin>
    lateinit var listView:ListView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        val url : String = "https://apisimpleappp.herokuapp.com/post?state=id_category=2,status=1&fields=id_post,title,img,create_time&limit=14&sort=create_time,desc"
//        ReadJSON().execute(url)
        val view =  inflater.inflate(R.layout.fragment_bong_da, container, false)
        listMauTin = arrayListOf()
        listView = view.findViewById(R.id.listViewMauTinBD)
        loadListView()

        return view
    }
    fun loadListView(){
        RetrofitInstance.instance.getListMauTin1().enqueue(object : Callback<ArrayList<MauTin>>{
            override fun onResponse(call: Call<ArrayList<MauTin>>, response: Response<ArrayList<MauTin>>) {
                response.body()?.let { list ->
                    listMauTin.addAll(list)
                    var adapterMauTin = MauTinAdapter(requireContext(), listMauTin)
                    listView.adapter = adapterMauTin
                    listView.setOnItemClickListener { parent, view, position, id ->
                        showChiTietPost(listMauTin.get(position).getIdMauTin().toString())
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<MauTin>>, t: Throwable) {

            }

        })
    }
//    inner class ReadJSON : AsyncTask<String, Void, String>() {
//        override fun doInBackground(vararg params: String?): String {
//            var content: StringBuffer = StringBuffer()
//            val url: URL = URL(params[0])
//            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
//            val inputStreamReader = InputStreamReader(urlConnection.inputStream)
//            val bufferedReader = BufferedReader(inputStreamReader)
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
//                var id = jsonOb.getInt("id_post")
//                var tieuDe = jsonOb.getString("title")
//
//                var img = jsonOb.getString("img")
//                var soPhut = jsonOb.getString("create_time")
//                var mauTin: MauTin = MauTin(id, img, tieuDe, soPhut)
//                listMauTin.add(mauTin)
//            }
//            var adapterMauTin = MauTinAdapter(context!!, listMauTin)
//            listview.adapter = adapterMauTin
//            listview.setOnItemClickListener { parent, view, position, id ->
//                showChiTietPost(listMauTin.get(position).getIdMauTin().toString())
//            }
//        }
//
//    }
    private fun showChiTietPost(id: String?) {
        val intent = Intent(context, BodyActivity::class.java)
        intent.putExtra("id_post", id)
        requireActivity().startActivity(intent)
    }
}