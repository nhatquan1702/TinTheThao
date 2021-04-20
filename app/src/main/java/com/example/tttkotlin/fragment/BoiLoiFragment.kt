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
import com.example.tttkotlin.model.MauTin
import com.example.tttkotlin.adapter.MauTinAdapter
import com.example.tttkotlin.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BoiLoiFragment : Fragment() {
    lateinit var listView: ListView
    lateinit var listMauTin:ArrayList<MauTin>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_boi_loi, container, false)
        listView = view.findViewById(R.id.listViewMauTinBL)
        listMauTin = arrayListOf()
        loadListView()
        return view
    }

    fun loadListView(){
        RetrofitInstance.instance.getListMauTin3().enqueue(object : Callback<ArrayList<MauTin>>{
            override fun onResponse(call: Call<ArrayList<MauTin>>, response: Response<ArrayList<MauTin>>) {
                response.body()?.let {
                    listMauTin.addAll(it)
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
    private fun showChiTietPost(id: String?) {
        val intent = Intent(context, BodyActivity::class.java)
        intent.putExtra("id_post", id)
        requireActivity().startActivity(intent)
    }
}