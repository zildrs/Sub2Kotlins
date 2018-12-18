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
import kotlinx.android.synthetic.main.frag_next.*
import org.jetbrains.anko.support.v4.startActivity
import retrofit2.Call
import retrofit2.Response
import tecx.z3.sub2kotlins.R
import tecx.z3.sub2kotlins.adapter.AdapterLast
import tecx.z3.sub2kotlins.api.ApiClient
import tecx.z3.sub2kotlins.model.DataLastNext
import tecx.z3.sub2kotlins.model.ResponseLastNext
import tecx.z3.sub2kotlins.util.Constant
import tecx.z3.sub2kotlins.util.Constant.Companion.API_KEY
import tecx.z3.sub2kotlins.util.Constant.Companion.ID_LEAGUE

/**
 * Created by Z3_Brothers on 11/28/18.
 */
class NextFragment : Fragment() {

    private var dataNext: MutableList<DataLastNext> = mutableListOf()
    private lateinit var adapterNext: AdapterLast

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        nextSwipe.setOnRefreshListener {
            getNext()
        }

        adapterNext = AdapterLast(dataNext) {
            startActivity<DetailActivity>(Constant.ID_EVENT to "${it.idEvent}")
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        getNext()
        return inflater.inflate(R.layout.frag_next, container, false)
    }

    private fun getNext() {
        val leagueCall = ApiClient().getInstance().getNextMatch(API_KEY, ID_LEAGUE)
        leagueCall.enqueue(object : retrofit2.Callback<ResponseLastNext> {
            override fun onResponse(call: Call<ResponseLastNext>, response: Response<ResponseLastNext>) {
                if (response.isSuccessful) {
                    val dataLastNext = response.body()?.events
                    nextRecycle.layoutManager = LinearLayoutManager(context)
                    dataNext.clear()
                    dataNext.addAll(dataLastNext as List<DataLastNext>)
                    adapterNext.notifyDataSetChanged()
                    nextRecycle.adapter = adapterNext
                    nextSwipe.isRefreshing = false
                    nextProgress.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<ResponseLastNext>, t: Throwable) {
                Log.e("Error: Load Last = ", t.message)
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                nextProgress.visibility = View.GONE
                nextSwipe.isRefreshing = false

            }
        })
    }
}