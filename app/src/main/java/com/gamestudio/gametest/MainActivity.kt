package com.gamestudio.gametest

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var URL : String = getString(R.string.url)
        if (URL.isNotEmpty()){
            var intent = Intent(this,WebActivity:: class.java)
            intent.putExtra("url", URL)
            startActivity(intent)

        }

        play.setOnClickListener{
            val intent = Intent(this,PlayActivity::class.java)
            val guessWordsArray: Array<String> = resources.getStringArray(R.array.guessWords)

            val rand = Random().nextInt(guessWordsArray.size-0)+0
            val secretWord = guessWordsArray[rand]

            intent.putExtra("secretWord", secretWord)
            startActivity(intent)
        }
    }

}
