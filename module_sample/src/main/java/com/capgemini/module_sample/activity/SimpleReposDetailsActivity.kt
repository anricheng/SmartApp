package com.capgemini.module_sample.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.capgemini.api.ReposDetailsApi
import com.capgemini.entity.ReposDetails
import com.capgemini.http.RetrofitManager
import com.capgemini.lib_communicate.arouter.SampleModuleARouterPath.Companion.SAMPLE_REPOS_DETAILS
import com.capgemini.module_sample.R
import com.capgemini.module_sample.databinding.SimpleActivityReposDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.simple_activity_repos_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
@Route(path = SAMPLE_REPOS_DETAILS)
class SimpleReposDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataBinding =DataBindingUtil.setContentView<SimpleActivityReposDetailsBinding>(this,R.layout.simple_activity_repos_details)


        val api = RetrofitManager(this)?.createApi(ReposDetailsApi::class.java,"https://api.github.com/")

        api.getReposDetails("anricheng","MyNote").enqueue(object : Callback<ReposDetails>{
            override fun onResponse(call: Call<ReposDetails>, response: Response<ReposDetails>) {
                dataBinding.reposDetails = response.body()
                when{
                    response.body()?.private == true -> textViewPrivate.setText("私人")
                    response.body()?.private == false -> textViewPrivate.setText("公共")
                }
            }

            override fun onFailure(call: Call<ReposDetails>, t: Throwable) {

            }
        })


    }
}