package com.shouquan.statelayout.sample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.shouquan.statelayout.StateLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        // Custom state.
        private const val STATE_EMPTY = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layout_state.getView(StateLayout.STATE_ERROR)
            ?.findViewById<Button>(R.id.button_retry)
            ?.setOnClickListener {
                layout_state.setState(StateLayout.STATE_LOADING)
                layout_state.postDelayed({ layout_state.setState(StateLayout.STATE_CONTENT) }, 2000)
            }

        layout_state.setViewForState(STATE_EMPTY, R.layout.view_empty)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.content -> {
                layout_state.setState(StateLayout.STATE_CONTENT)
                return true
            }
            R.id.loading -> {
                layout_state.setState(StateLayout.STATE_LOADING)
                return true
            }
            R.id.error -> {
                layout_state.setState(StateLayout.STATE_ERROR)
                return true
            }
            R.id.custom -> {
                layout_state.setState(STATE_EMPTY)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
