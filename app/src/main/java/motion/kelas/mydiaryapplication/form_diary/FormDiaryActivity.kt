package motion.kelas.mydiaryapplication.form_diary

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_form_diary.*
import motion.kelas.mydiaryapplication.R
import motion.kelas.mydiaryapplication.dao.DiaryDao
import motion.kelas.mydiaryapplication.detail_diary.DetailDiaryActivity
import motion.kelas.mydiaryapplication.list_diary.ListDiaryModel
import motion.kelas.mydiaryapplication.load


class FormDiaryActivity : AppCompatActivity() {

    // variable untuk mengecek apakah edit atau bukan (default nilainya false)
    var isEdit = false
    lateinit var model: ListDiaryModel

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

            val db = FirebaseFirestore.getInstance()

            // jika form tersebut edit
            if (isEdit) {
                // Buat model berdasarkan data dari form
                val data = DiaryDao(
                    model.id,
                    Timestamp.now(),
                    etCreateDiaryDescription.text.toString(),
                    etCreateDiaryTitle.text.toString(),
                    model.url
                )

                db.collection("diarys").document(model.id)
                    .set(data).addOnSuccessListener {
                        //berpindah ke Activity Detail Diary
                        val intent = Intent(this, DetailDiaryActivity::class.java)
                        intent.putExtra("id", model.id)
                        startActivity(intent)
                        finish()
                    }.addOnFailureListener {
                        Toast.makeText(this, "Failed change data", Toast.LENGTH_SHORT).show()
                    }

            } else {
                // Buat model berdasarkan data dari form
                val data = DiaryDao(
                    "",
                    Timestamp.now(),
                    etCreateDiaryDescription.text.toString(),
                    etCreateDiaryTitle.text.toString(),
                    "https://www.talonplus.co.th/Mirror%20Effect%20_%20Demo%201%20_%20Codrops_files/1.jpg"
                )

                val docRec = db.collection("diarys")
                docRec.add(data).addOnSuccessListener {
                    finish()
                }.addOnFailureListener {
                    Toast.makeText(this, "Failed add data", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
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

            model = intent.extras?.getSerializable("model") as ListDiaryModel
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