package motion.kelas.mydiaryapplication.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import motion.kelas.mydiaryapplication.R
import motion.kelas.mydiaryapplication.list_diary.ListDiaryActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val time: Long = 3000  // tetapkan waktu dari splash (3000 millisecond = 3 detik)
        Handler().postDelayed({
            //jalankan startActivity ketika sudah 3 detik.
            startActivity(Intent(this@SplashActivity, ListDiaryActivity::class.java))
        }, time)

        // shortcut
        // ctrl + p : untuk lihat parameter
        // ctrl + space : lihat suggest
        // alt + enter : ketika terdapat error
        // ctrl + alt + L : untuk merapihkan
        // ctrl + W : untuk blok suatu text
        // ctrl + D : untuk duplikat
        // ctrl + / : untuk komentar per line
    }
}
