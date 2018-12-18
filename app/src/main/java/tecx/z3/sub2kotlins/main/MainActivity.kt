package tecx.z3.sub2kotlins.main

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import tecx.z3.sub2kotlins.R
import tecx.z3.sub2kotlins.R.id.navigation_prev
import tecx.z3.sub2kotlins.R.id.navigation_next

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                navigation_prev -> {
                    loadsPrevFragment(savedInstanceState)
                }
                navigation_next -> {
                    loadsNextFragment(savedInstanceState)
                }
            }
            true
        }

        bottom_navigation.selectedItemId = navigation_prev
    }

    private fun loadsNextFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.layout_main, NextFragment(), NextFragment::class.java.simpleName)
                    .commit()

        }
    }

    private fun loadsPrevFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.layout_main, PrevFragment(), PrevFragment::class.java.simpleName)
                    .commit()
        }
    }
}