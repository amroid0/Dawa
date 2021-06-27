package com.amroid.dawa

import android.opengl.Visibility
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.util.Log
import android.view.View
import androidx.wear.widget.WearableLinearLayoutManager
import com.amroid.dawa.api.UnsafeOkHttpClient
import com.amroid.dawa.api.UserApi
import com.amroid.dawa.databinding.ActivityMainBinding
import com.amroid.dawa.model.UserAnalysis
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : WearableActivity() {

    private lateinit var userApi: UserApi
    private lateinit var adapter: UserDataAdpater
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val okHttpClient: OkHttpClient = UnsafeOkHttpClient.unsafeOkHttpClient
        val retrofit: Retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(UserApi.END_POINT)
            .build()

         userApi = retrofit.create(UserApi::class.java)
         makeRequest(userApi)
        binding.retry.setOnClickListener {
            makeRequest(userApi)

        }



        setAmbientEnabled()



    }

    private fun makeRequest(userApi: UserApi) {

        binding.loading.visibility= View.VISIBLE
        binding.retry.visibility= View.GONE
        userApi.getUserAnalysis().enqueue(object : Callback<UserAnalysis> {
            override fun onResponse(call: Call<UserAnalysis>?, response: Response<UserAnalysis>?) {
                binding.loading.visibility= View.GONE

                if (response!=null&&response.isSuccessful){
                    showResult(response.body())
                }else{
                    binding.retry.visibility= View.VISIBLE
                }


            }

            override fun onFailure(call: Call<UserAnalysis>?, t: Throwable?) {
                binding.loading.visibility= View.GONE
                binding.retry.visibility= View.VISIBLE

                Log.d("userAys", "onFailure: Sorry no more data for you")
            }
        })


    }

    private fun showResult(userAnalysis:UserAnalysis){
        adapter = UserDataAdpater(userAnalysis.data!!.toMutableList())
        binding.recyclerView.isEdgeItemsCenteringEnabled = true
        binding.recyclerView.layoutManager = WearableLinearLayoutManager(this,CustomScrollingLayoutCallback())
        binding.recyclerView.isCircularScrollingGestureEnabled = true;
        binding.recyclerView.isEdgeItemsCenteringEnabled = true

        binding.recyclerView.adapter = adapter


    }


}