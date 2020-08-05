package motion.kelas.mydiaryapplication.form_diary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_form_diary.*
import motion.kelas.mydiaryapplication.R
import motion.kelas.mydiaryapplication.detail_diary.DetailDiaryActivity
import motion.kelas.mydiaryapplication.list_diary.ListDiaryActivity
import motion.kelas.mydiaryapplication.list_diary.ListDiaryModel
import motion.kelas.mydiaryapplication.load

class FormDiaryActivity : AppCompatActivity() {

    // variable untuk mengecek apakah edit atau bukan (default nilainya false)
    var isEdit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_diary)

        // memanggil fugnsi berikut ketika activity dibuat
        initToolbar()
        loadData()
        initListener()
    }


    private fun initListener() {
        // Ketika tombol Create / Update pada Diary Notes di klik
        btnDiaryNotes.setOnClickListener {

            // Buat model berdasarkan data dari form
            val data = ListDiaryModel(
                "",
                etCreateDiaryTitle.text.toString(),
                "10 AUG 2020",
                "https://i.ibb.co/y8XHwYS/island.png",
                etCreateDiaryDescription.text.toString()
            )

            // jika form tersebut edit
            if (isEdit) {
                //berpindah ke Activity Detail Diary
                val intent = Intent(this, DetailDiaryActivity::class.java)
                intent.putExtra("model", data) // kirim data ke activity baru
                startActivity(intent)
                finish()
            }else {
                //berpindah ke Activity List Diary
                val intent = Intent(this, ListDiaryActivity::class.java)
                intent.putExtra("model", data) // kirim data ke activity baru
                startActivity(intent)
            }
        }
    }

    fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish() // menghancurkan aktivitas
        }
    }

    fun loadData() {
        isEdit = intent?.extras?.getBoolean("isEdit", false) ?: false

        if (isEdit!!) {
            toolbarTitle.text = "Edit Diary"
            llCreateDiaryCover.visibility = View.GONE
            rlCreateDiaryCover.visibility = View.VISIBLE

            val model = intent.extras?.getSerializable("model") as ListDiaryModel
            etCreateDiaryTitle.setText(model.title)
            etCreateDiaryDescription.setText(model.story)
            ivCreateDiaryEdit.load(model.url)
            btnDiaryNotes.text = "Update"
        } else {
            toolbarTitle.text = "Create Diary"
            llCreateDiaryCover.visibility = View.VISIBLE
            rlCreateDiaryCover.visibility = View.GONE
            btnDiaryNotes.text = "Create"
        }
    }
}