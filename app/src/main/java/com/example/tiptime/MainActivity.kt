package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    // lateinit 初期化を後回しにして定義だけする
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // これで findVieById を使わずに binding オブジェクトから取得できる
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /* 例
        古いやり方
        val myButoon: Button = findViewById(R.id.my_button)
        myButton.text = "AAA"
        新しいやり方
        binding.myButton.text = "AAA"
        めっちゃ楽に書ける
         */
        /*
        バインディングのクラス名は XML ファイル名をキャメルケースに変換して末尾に Binding をつける
        例 activity_main.xml -> ActivityMainBinding
        各ビューの参照はアンダースコアを削除してビュー名をキャメルケースに変換することで生成される
        例 @id/text_view -> binding.textView
         */
        binding.calculateButton.setOnClickListener { calculateTip() }
    }
    fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDouble()
        val selectedId = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when (selectedId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage * cost
        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)


    }
}
