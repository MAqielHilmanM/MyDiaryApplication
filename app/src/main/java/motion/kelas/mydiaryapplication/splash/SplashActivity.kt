package motion.kelas.mydiaryapplication.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import motion.kelas.mydiaryapplication.R
import motion.kelas.mydiaryapplication.list_diary.ListDiaryActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val time : Long = 3000
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, ListDiaryActivity::class.java))
        },time)

        // ctrl + p : untuk lihat parameter
        // ctrl + space : lihat suggest
    }
}
