package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding

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
    }
}
