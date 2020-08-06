package motion.kelas.mydiaryapplication.detail_diary

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_detail_diary.*
import motion.kelas.mydiaryapplication.R
import motion.kelas.mydiaryapplication.dao.DiaryDao
import motion.kelas.mydiaryapplication.form_diary.FormDiaryActivity
import motion.kelas.mydiaryapplication.list_diary.ListDiaryModel
import motion.kelas.mydiaryapplication.load


class DetailDiaryActivity : AppCompatActivity() {

    // membuat property/variable global yang akan di inisialisasi nanti
    lateinit var id: String
    lateinit var title: String
    lateinit var date: String
    lateinit var datetime: Timestamp
    lateinit var url: String
    lateinit var story: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_diary)

        // memanggil fungsi toolbar dan load data
        initToolbar()
        loadData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // memasang menu di Res/menu/menu_detail ke menu toolbar
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // membuat aksi ketika item di toolbar menu di klik
        return when (item.itemId) {
            R.id.menuEdit -> {      // Ketika menuEdit di klik (sesuai dgn id di res/menu/menu_detail.xml)
                val intent = Intent(this, FormDiaryActivity::class.java)
                intent.putExtra("isEdit", true) // mengirim extra kalau ini tujuannya untuk edit
                intent.putExtra(
                    "model", ListDiaryModel(
                        id, title, date, url, story
                    )
                ) // mengirim isi data ke form
                startActivity(intent) // mulai berpindah activity
                finish()
                return true
            }
            R.id.menuDelete -> {    // Ketika menuDelete di klik (sesuai dgn id di res/menu/menu_detail.xml)
                // membuat builder alert dialog (rancangan alert dialog)
                val builder = AlertDialog.Builder(this@DetailDiaryActivity)
                builder.setTitle("Warning")     // set title dari dialog
                builder.setMessage("Are you sure to delete this diary?")    // set pesan dari dialog
                builder.setPositiveButton("OK") { dialog, which ->
                    // aksi ketika tombol ok di klik

                    val db = FirebaseFirestore.getInstance()
                    db.collection("diarys").document(id)
                        .delete()
                        .addOnSuccessListener {
                            finish()
                        }
                        .addOnFailureListener { e ->
                            Log.w("Error", "Error deleting document", e)
                        }
                }
                builder.setNegativeButton("Cancel") { dialog, which ->
                    // aksi ketika tombol cancel di klik
                    dialog.dismiss()
                }

                // membuat alert dialog dari rancangan sebelumnya
                val dialog: AlertDialog = builder.create()
                dialog.show()   // tampilkan alert dialog

                return true
            }
            else -> {
                // default jika tombol selain edit dan delete di klik
                return super.onOptionsItemSelected(item)
            }
        }
    }

    fun initToolbar() {
        setSupportActionBar(toolbar)                        //set action bar dengan toolbar pada layout
        supportActionBar?.setDisplayHomeAsUpEnabled(true)   //tampilkan tombol back
        toolbar.setNavigationOnClickListener {
            finish() // menghancurkan aktivitas
        }
        supportActionBar?.title = ""
    }

    fun loadData() {
        // Cara mengambil extra dari intent
        id = intent?.extras?.getString("id","")?:""

        if(id?.isNotEmpty() == true){
            val db = FirebaseFirestore.getInstance()
            db.collection("diarys").document(id).addSnapshotListener { snapshot, error ->
                if(snapshot == null){
                    return@addSnapshotListener
                }

                if(snapshot != null && snapshot.exists()){
                    val model = snapshot.toObject(DiaryDao::class.java)

                    // pindahkan data dari model ke variable title,date,url, dan story
                    title = model?.title?: ""
                    date = model?.date?.toDate().toString()?:""
                    url = model?.url?:""
                    story = model?.story?:""
                    datetime = model?.date?: Timestamp.now()

                    // tampilkan data tersebut ke tampilan (idTextView).text = data
                    tvDetailDiaryDate.text = date
                    tvDetailDiaryTitle.text = title
                    tvDetailDiaryDescription.text = story
                    ivDetailDiary.load(url!!) // gunakan load pada extension untuk load gambar
                }
            }
        }

    }
}
