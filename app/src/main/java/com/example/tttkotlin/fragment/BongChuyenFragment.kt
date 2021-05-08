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
import kotlinx.android.synthetic.main.fragment_bong_chuyen.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BongChuyenFragment : Fragment() {
    lateinit var listMauTin:ArrayList<MauTin>
    lateinit var viewBongChuyen:View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        viewBongChuyen  = inflater.inflate(R.layout.fragment_bong_chuyen, container, false)
        listMauTin = arrayListOf()
        loadListView()
        return viewBongChuyen
    }

    override fun onResume() {
        super.onResume()
        viewBongChuyen.shimmerFrame.startShimmer()
    }
    fun loadListView(){
        RetrofitInstance.instance.getListMauTin2().enqueue(object:Callback<ArrayList<MauTin>>{
            override fun onResponse(call: Call<ArrayList<MauTin>>, response: Response<ArrayList<MauTin>>) {
                response.body()?.let { bao->
                    listMauTin.addAll(bao)
                    val adapterMauTin = MauTinAdapter(requireContext(), listMauTin)

                    viewBongChuyen.apply {
                        listViewMauTinBC.apply {
                            adapter = adapterMauTin
                            setOnItemClickListener { parent, view, position, id ->
                                showChiTietPost(listMauTin.get(position).getIdMauTin().toString())
                            }
                        }
                        shimmerFrame.apply {
                            stopShimmer()
                            visibility = View.GONE
                        }

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