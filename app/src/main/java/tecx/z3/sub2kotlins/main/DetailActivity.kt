package tecx.z3.sub2kotlins.main

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.dialog.view.*
import org.jetbrains.anko.find
import retrofit2.Call
import retrofit2.Response
import tecx.z3.sub2kotlins.R
import tecx.z3.sub2kotlins.api.ApiClient
import tecx.z3.sub2kotlins.model.DataLastNext
import tecx.z3.sub2kotlins.model.ResponseLastNext
import tecx.z3.sub2kotlins.util.Constant
import tecx.z3.sub2kotlins.util.Constant.Companion.ID_EVENT
import tecx.z3.sub2kotlins.util.Util

/**
 * Created by Z3_Brothers on 11/28/18.
 */
class DetailActivity : AppCompatActivity() {
    var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setTitle("Detail Match")
        id = intent.getStringExtra(ID_EVENT)
        getDetailEvent()

    }

    fun getDetailEvent() {
        val callLeague = ApiClient().getInstance().getDetailEvent(Constant.API_KEY, id)
        callLeague.enqueue(object : retrofit2.Callback<ResponseLastNext> {
            override fun onResponse(call: Call<ResponseLastNext>, response: Response<ResponseLastNext>) {
                if (response.isSuccessful) {
                    val dataLastNext = response.body()?.events
                    setup(dataLastNext as List<DataLastNext>)
                }

            }

            override fun onFailure(call: Call<ResponseLastNext>, t: Throwable) {
                Log.e("Error: Load Last = ", t.message)
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

        })

    }

    fun setup(lastNext: List<DataLastNext>) {
        val txtDetailAway = find<TextView>(R.id.txtDetailAway)
        val txtDetailGoalHome = find<TextView>(R.id.txtDetailHomeGoal)
        val txtDetailGoalAway = find<TextView>(R.id.txtDetailAwayGoal)
        val txtDetailYellowHome = find<TextView>(R.id.txtDetailYellowHome)
        val txtDetailYellowAway = find<TextView>(R.id.txtDetailYellowAway)
        val txtDetailTime = find<TextView>(R.id.txtDateDetail)
        val txtDetailRedHome = find<TextView>(R.id.txtDetailRedHome)
        val txtDetailRedAway = find<TextView>(R.id.txtDetailRedAway)
        val imgDetailHome = find<ImageView>(R.id.imgHomeDetail)
        val txtDetailHome = find<TextView>(R.id.txtHomeDetail)
        val txtDetailScoreHome = find<TextView>(R.id.txtDetailScoreHome)
        val txtDetailScoreAway = find<TextView>(R.id.txtDetailScoreAway)
        val imgDetailAway = find<ImageView>(R.id.imgDetailAway)
        val btnLineup = find<Button>(R.id.btnLineup)
        val txtDetailDate = find<TextView>(R.id.txtDateDetail)

        lastNext.forEach {
            txtDetailDate.text = Util.formatDate(it.dateEvent)
            txtDetailTime.text = Util.formatTime(it.strTime)
            txtDetailHome.text = it.strHomeTeam
            txtDetailAway.text = it.strAwayTeam
            txtDetailScoreHome.text = it.intHomeScore
            txtDetailScoreAway.text = it.intAwayScore
            txtDetailGoalHome.text = Util.formatNumPlayer(it.strHomeGoalDetails)
            txtDetailGoalAway.text = Util.formatNumPlayer(it.strAwayGoalDetails)
            Util.loadBadge(it.idHomeTeam, imgDetailHome, applicationContext)
            Util.loadBadge(it.idAwayTeam, imgDetailAway, applicationContext)
            txtDetailYellowHome.text = Util.formatNumPlayer(it.strHomeYellowCards)
            txtDetailYellowAway.text = Util.formatNumPlayer(it.strAwayYellowCards)
            txtDetailRedHome.text = Util.formatNumPlayer(it.strHomeRedCards)
            txtDetailRedAway.text = Util.formatNumPlayer(it.strAwayRedCards)
            if (it.strAwayLineupDefense != null) {
                btnLineup.setOnClickListener {
                    val dialog = LayoutInflater.from(this).inflate(R.layout.dialog, null)
                    val mBuilder = AlertDialog.Builder(this).setView(dialog)
                    lastNext.forEach {
                        Util.loadBadge(it.idHomeTeam, dialog.imgdialoghome, applicationContext)
                        Util.loadBadge(it.idAwayTeam, dialog.imgdialogaway, applicationContext)
                        dialog.txtdialogkiperhome.text = Util.formatPlayer(it.strHomeLineupGoalkeeper)
                        dialog.txtdialogkiperaway.text = Util.formatPlayer(it.strAwayLineupGoalkeeper)
                        dialog.txtdialogdefenderhome.text = Util.formatPlayer(it.strHomeLineupDefense)
                        dialog.txtdialogdefenderaway.text = Util.formatPlayer(it.strAwayLineupDefense)
                        dialog.txtdialogmidfielderhome.text = Util.formatPlayer(it.strHomeLineupMidfield)
                        dialog.txtdialogmidfielderaway.text = Util.formatPlayer(it.strAwayLineupMidfield)
                        dialog.txtdialogforwardhome.text = Util.formatPlayer(it.strHomeLineupForward)
                        dialog.txtdialogforwardaway.text = Util.formatPlayer(it.strAwayLineupForward)
                        dialog.txtdialogsubtituteshome.text = Util.formatPlayer(it.strHomeLineupSubstitutes)
                        dialog.txtdialogsubtitutesaway.text = Util.formatPlayer(it.strAwayLineupSubstitutes)
                    }
                    val alert = mBuilder.create()
                    alert.show()
                }
            } else if (it.strAwayLineupDefense == null) {
                btnLineup.isEnabled = false
                btnLineup.setOnClickListener {
                    Toast.makeText(applicationContext, "Lineups is have not been made", Toast.LENGTH_SHORT).show()


                }
            }
        }
    }
}