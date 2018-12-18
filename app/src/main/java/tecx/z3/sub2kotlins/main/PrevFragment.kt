package tecx.z3.sub2kotlins.main

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.frag_prev.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity
import retrofit2.Call
import retrofit2.Response
import tecx.z3.sub2kotlins.R
import tecx.z3.sub2kotlins.adapter.AdapterLast
import tecx.z3.sub2kotlins.api.ApiClient
import tecx.z3.sub2kotlins.model.DataLastNext
import tecx.z3.sub2kotlins.model.ResponseLastNext
import tecx.z3.sub2kotlins.util.Constant.Companion.API_KEY
import tecx.z3.sub2kotlins.util.Constant.Companion.ID_EVENT
import tecx.z3.sub2kotlins.util.Constant.Companion.ID_LEAGUE

/**
 * Created by Z3_Brothers on 06/12/18.
 */
class PrevFragment : Fragment() {

    private var dataPrev: MutableList<DataLastNext> = mutableListOf()
    private lateinit var last: AdapterLast

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        getLast()
        last = AdapterLast(dataPrev) {
            startActivity<DetailActivity>(ID_EVENT to "${it.idEvent}")
        }

        return inflater.inflate(R.layout.frag_prev, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        lastSwipe.setOnRefreshListener { getLast() }
    }

    fun getLast() {
        val callLeague = ApiClient().getInstance().getLastMatch(API_KEY, ID_LEAGUE)
        callLeague.enqueue(object : retrofit2.Callback<ResponseLastNext> {
            override fun onResponse(call: Call<ResponseLastNext>, response: Response<ResponseLastNext>) {
                if (response.isSuccessful) {
                    val dataLastNext = response.body()?.events
                    recyclerPrev.layoutManager = LinearLayoutManager(context)
                    dataPrev.clear()
                    dataPrev.addAll(dataLastNext as List<DataLastNext>)
                    last.notifyDataSetChanged()
                    recyclerPrev.adapter = last
                    lastSwipe.isRefreshing = false
                    progresLast.visibility = View.GONE

                }

            }

            override fun onFailure(call: Call<ResponseLastNext>, t: Throwable) {
                Log.e("Error : Load Last = ", t.message)
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                progresLast.visibility = View.GONE
                lastSwipe.isRefreshing = false

            }

        })

    }
}