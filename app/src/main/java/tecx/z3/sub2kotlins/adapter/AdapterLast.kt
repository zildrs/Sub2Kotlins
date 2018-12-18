package tecx.z3.sub2kotlins.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.itemlist.*
import tecx.z3.sub2kotlins.model.DataLastNext
import tecx.z3.sub2kotlins.R
import tecx.z3.sub2kotlins.util.Util

/**
 * Created by Z3_Brothers on 02/12/18.
 */
class AdapterLast(private val lastNext: List<DataLastNext>, private val listener: (DataLastNext) -> Unit) :
        RecyclerView.Adapter<AdapterLast.LastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            LastViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.itemlist, parent, false))


    override fun getItemCount(): Int = lastNext.size

    override fun onBindViewHolder(holder: LastViewHolder, position: Int) {
        holder.bindItem(lastNext[position], listener)
    }

    class LastViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {

        fun bindItem(lastNext: DataLastNext, listener: (DataLastNext) -> Unit) {
            dateTxt.text = Util.formatDate(lastNext.dateEvent)
            txtAway.text = lastNext.strAwayTeam
            timeTxt.text = Util.formatTime(lastNext.strTime)
            txtHome.text = lastNext.strHomeTeam
            txtScoreAway.text = lastNext.intAwayScore
            txtScoreHome.text = lastNext.intHomeScore

            itemView.setOnClickListener { listener(lastNext) }


        }
    }
}