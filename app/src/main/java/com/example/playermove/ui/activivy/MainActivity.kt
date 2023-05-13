package com.example.playermove.ui.activivy

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import com.example.playermove.R
import com.example.playermove.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var startY = 0f
    var isMoving = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpListener()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setUpListener() {
        binding.buttonLeft.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    startY = view.y
                    isMoving = true
                    moveViewLeft()
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    isMoving = false
                }
            }
            true
        }
        binding.buttonRight.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    startY = view.y
                    isMoving = true
                    moveViewRight()
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    isMoving = false
                }
            }
            true
        }
    }

    private fun moveViewLeft() {
        Thread {
            while (isMoving) {
                Thread.sleep(30)
                runOnUiThread {
                    val newY = binding.mainHero.x - 10
                    binding.mainHero.x = newY
                }
            }
        }.start()
    }

    private fun moveViewRight() {
        Thread {
            while (isMoving) {
                Thread.sleep(16)
                runOnUiThread {
                    val newY = binding.mainHero.x + 10
                    binding.mainHero.x = newY
                }
            }
        }.start()
    }
}