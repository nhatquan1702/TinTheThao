package com.example.tttkotlin.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tttkotlin.R
import com.example.tttkotlin.activity.BodyActivity
import com.example.tttkotlin.activity.LoginActivity
import com.example.tttkotlin.adapter.TabAdapter
import com.example.tttkotlin.api.RetrofitInstance
import com.example.tttkotlin.model.DanhMuc
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    lateinit var listDanhMuc : ArrayList<DanhMuc>
    lateinit var tabLayout : TabLayout
    lateinit var tvHoten : TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root : View = inflater.inflate(R.layout.fragment_home, container, false)
        tabLayout = root.findViewById(R.id.tabLayout)
        listDanhMuc = arrayListOf()
        loadTabLayout()
        root.iconToolbar.setOnClickListener(View.OnClickListener {
            drawerlayout.toggleMenu()
        })
        root.nav_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.H -> {
                    Toast.makeText(context, "Home!", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.CS -> {

                    Toast.makeText(requireContext(), "Chia sẻ!", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.GY -> {

                    Toast.makeText(requireContext(), "Góp ý!", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.CD -> {

                    Toast.makeText(requireContext(), "Cài đặt!", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.HT -> {

                    Toast.makeText(requireContext(), "Hỗ trợ!", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.DGUD -> {

                    Toast.makeText(requireContext(), "Đánh giá ứng dụng!", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.DX -> {

                    //Toast.makeText(requireContext(), "Đăng xuất!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, LoginActivity::class.java)
                    requireActivity().startActivity(intent)
                    true

                }
                else -> false
            }
        }
        return root
    }
    fun loadTabLayout(){
        RetrofitInstance.instance.getListNameDanhMuc().enqueue(object : Callback<ArrayList<DanhMuc>>{
            override fun onResponse(call: Call<ArrayList<DanhMuc>>, response: Response<ArrayList<DanhMuc>>) {
                response.body()?.let {
                    listDanhMuc.addAll(it)
                    for (i in 0..listDanhMuc.size-1){
                        tabLayout.addTab(tabLayout.newTab().setText(listDanhMuc.get(i).getName()))
                    }
                    tabLayout.tabGravity = TabLayout.GRAVITY_FILL
                    val tabadapter = TabAdapter(requireContext(), childFragmentManager, tabLayout.tabCount)
                    viewPager.adapter = tabadapter
                    viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
                    tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                        override fun onTabSelected(tab: TabLayout.Tab) {
                            viewPager.currentItem = tab.position
                        }

                        override fun onTabUnselected(tab: TabLayout.Tab) {}
                        override fun onTabReselected(tab: TabLayout.Tab) {}
                    })
                }
            }

            override fun onFailure(call: Call<ArrayList<DanhMuc>>, t: Throwable) {

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
//                Log.d("Lỗi", e.toString())
//            }
//            return content.toString()
//        }
//
//        override fun onPostExecute(result: String?) {
//            super.onPostExecute(result)
//            val jsonArray: JSONArray = JSONArray(result)
//            for (i in 0..jsonArray.length() - 1) {
//                var jsonOb: JSONObject = jsonArray.getJSONObject(i)
//                tabLayout.addTab(tabLayout.newTab().setText(jsonOb.getString("name")))
//            }
//            tabLayout.tabGravity = TabLayout.GRAVITY_FILL
//            var tabadapter = TabAdapter(requireContext(), childFragmentManager, tabLayout.tabCount)
//            viewPager.adapter = tabadapter
//            viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
//            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//                override fun onTabSelected(tab: TabLayout.Tab) {
//                    viewPager.currentItem = tab.position
//                }
//
//                override fun onTabUnselected(tab: TabLayout.Tab) {}
//                override fun onTabReselected(tab: TabLayout.Tab) {}
//            })
//        }
//    }
    fun AppCompatActivity.replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_layout, fragment)
            .addToBackStack(null)
            .commit()
    }
}