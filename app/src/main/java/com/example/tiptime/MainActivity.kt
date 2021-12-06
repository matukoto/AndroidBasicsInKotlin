package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    /*
    lateinit 初期化を後回しにして定義だけする
    二回目の入力などを考慮して var で定義
    でもイミュータブルにしたほうがいいよねぇ
    ToDo: val で実装できないか
    */
    private lateinit var binding: ActivityMainBinding

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
    private fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null || cost == 0.0) {
            displayTip(0.0)
            return
        }
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage * cost

        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }

        displayTip(tip)
    }
    // チップを表示するためのメソッド
    private fun displayTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

}
