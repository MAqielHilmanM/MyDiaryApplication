package motion.kelas.mydiaryapplication.list_diary

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_list_diary.*
import motion.kelas.mydiaryapplication.R
import motion.kelas.mydiaryapplication.dao.DiaryDao
import motion.kelas.mydiaryapplication.form_diary.FormDiaryActivity


class ListDiaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_diary)

        // panggil fungsi yang telah dibuat
        initToolbar()
        initListener()
        initRecycler()
        loadData()
    }

    fun initToolbar() {
        // set support action bar dengan toolbar pada layout
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
    }

    fun initListener() {
        // set tombol fab ketika di klik
        fab.setOnClickListener {
            // jalankan intent ke Form Diary Activity
            val intent = Intent(this@ListDiaryActivity, FormDiaryActivity::class.java)
            intent.putExtra(
                "isEdit",
                false
            ) // tambahkan extra kalau form berupa create (isEdit = false)
            startActivity(intent) // jalankan actvity
        }
    }

    // buat variable untuk daftar data yang akan ditampilkan
    val lists = arrayListOf<ListDiaryModel>()

    fun initRecycler() {
        // inisialisasi list recycler
        rvListDiary.adapter =
            ListDiaryAdapter(lists)   // buat adapter untuk recylerview berdasarka ListDiaryAdapter
        // tampilkan isi recyclerview menjadi vertical
        rvListDiary.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    fun loadData() {
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("diarys")
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w("LoadData", "Listen failed.")
                return@addSnapshotListener
            }

            if (snapshot != null && !snapshot.isEmpty) {
                lists.clear()
                for (document in snapshot.documents) {
                    val data = document.toObject(DiaryDao::class.java)
                    lists.add(
                        ListDiaryModel(
                            document.id,
                            data?.title ?: "",
                            data?.date.toString(),
                            data?.url ?: "",
                            data?.story ?: ""
                        )
                    )
                }
                rvListDiary.adapter?.notifyDataSetChanged()
            } else {
                Toast.makeText(this,"Load Data Failed",Toast.LENGTH_SHORT).show()
            }
        }
    }
}

