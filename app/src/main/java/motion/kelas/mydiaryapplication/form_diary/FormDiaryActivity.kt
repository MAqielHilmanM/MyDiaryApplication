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

    var isEdit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_diary)

        initToolbar()
        loadData()
        initListener()
    }

    private fun initListener() {
        btnDiaryNotes.setOnClickListener {
            val data = ListDiaryModel(
                "",
                etCreateDiaryTitle.text.toString(),
                "10 AUG 2020",
                "https://i.ibb.co/y8XHwYS/island.png",
                etCreateDiaryDescription.text.toString()
            )

            if (isEdit) {
                val intent = Intent(this, DetailDiaryActivity::class.java)
                intent.putExtra("model", data)
                startActivity(intent)
                finish()
            }else {
                val intent = Intent(this, ListDiaryActivity::class.java)
                intent.putExtra("model", data)
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