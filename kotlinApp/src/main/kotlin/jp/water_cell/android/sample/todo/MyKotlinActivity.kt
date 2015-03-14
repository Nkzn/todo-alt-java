package jp.water_cell.android.sample.todo

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

/**
 * Created by nakagawa on 15/03/14.
 */
class MyKotlinActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textView = TextView(this)

        textView.setText("Hello, Kotlin")

        setContentView(textView)
    }
}