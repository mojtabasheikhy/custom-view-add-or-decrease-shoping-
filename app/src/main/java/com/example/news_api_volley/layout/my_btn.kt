package com.example.news_api_volley.layout

import android.annotation.SuppressLint
import android.app.Notification
import android.content.Context
import android.os.Looper
import android.renderscript.Sampler
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RestrictTo
import com.example.news_api_volley.R
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import java.util.logging.Handler

@SuppressLint("ClickableViewAccessibility")
class my_btn : LinearLayout, View.OnClickListener, View.OnTouchListener, View.OnLongClickListener {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

   lateinit  var backgroundExecutor: ScheduledExecutorService
    lateinit var root_view: View
    lateinit var btn_pluse: ImageView
    lateinit var btn_mines: ImageView
    lateinit var textValue: TextView
    var is_long_pressed_plus = false
    var is_long_pressed_mines = false
    var min_value = 0
    var max_value = 100

    lateinit var handler:Handler
    init {
        root_view = LayoutInflater.from(context).inflate(R.layout.my_custom_btn, this)
        btn_pluse = root_view.findViewById(R.id.btn_pluse)
        btn_mines = root_view.findViewById(R.id.btn_mines)
        textValue = root_view.findViewById(R.id.text_value)
        btn_pluse.setOnClickListener(this)
        backgroundExecutor = Executors.newSingleThreadScheduledExecutor()
        btn_mines.setOnClickListener(this)

        btn_pluse.setOnLongClickListener(this)
        btn_mines.setOnLongClickListener(this)
        btn_pluse.setOnTouchListener(this)
        btn_mines.setOnTouchListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            btn_mines.id -> {
                mines_number()
            }
            btn_pluse.id -> {
                pluse_number()
            }

        }
    }


    fun getvalue(): Int {
        var old_value = textValue.text
        if (old_value.isEmpty()) {
            textValue.text = min_value.toString()
            return 0
        } else return Integer.parseInt(textValue.text.toString())
    }

    fun setvalue(new_number: Int) {
        if (new_number > max_value) {
            textValue.text = max_value.toString()

        } else if (new_number < min_value) {
            textValue.text = min_value.toString()

        } else textValue.text = new_number.toString()
    }

    fun pluse_number() {
        var old2 = getvalue()
        setvalue(old2 + 1)

    }

    fun mines_number() {
        var old: Int = getvalue()
        setvalue(old - 1)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
       if (event?.action == MotionEvent.ACTION_CANCEL||event?.action==MotionEvent.ACTION_UP)
       {
            is_long_pressed_mines=false
            is_long_pressed_plus=false
       }

        return false
    }

    override fun onLongClick(v: View?): Boolean {
       when (v?.id)
       {
           btn_pluse.id ->
           {
               is_long_pressed_plus=true

               android.os.Handler().postDelayed( { autoincrimnet().run()},100L)
           }
           btn_mines.id-> {

               is_long_pressed_mines=true
               android.os.Handler().postDelayed( {autodicrimnet().run()},100L)

           }

       }
        return false
    }

   inner class autoincrimnet:Runnable {
       override fun run() {
           if (is_long_pressed_plus) {
               pluse_number()
               android.os.Handler().postDelayed({ pluse_number() }, 100L)

           }
       }
   }
      inner class autodicrimnet:Runnable {
          override fun run() {

              if (is_long_pressed_mines) {
                  mines_number()
                  android.os.Handler().postDelayed({ mines_number() }, 100L)
              }
          }
      }
}