package motion.kelas.mydiaryapplication.list_diary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_form_diary.*
import kotlinx.android.synthetic.main.activity_list_diary.*
import kotlinx.android.synthetic.main.activity_list_diary.toolbar
import motion.kelas.mydiaryapplication.R
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
        initNewData()
    }

    fun initNewData(){
        // cek ketika intent mengirim extra berupa model/data (dari form)
        if( intent?.extras?.getSerializable("model") != null){
            // ambil data dari intent lalu load di model sebagau listdiarymodel
            val model = intent?.extras?.getSerializable("model") as ListDiaryModel
            lists.add(model)        // tambahkan list ke model
            rvListDiary.adapter?.notifyDataSetChanged()     // beri taukan kepada recyclerview adapter kalau ada perubahan data
        }
    }


    fun initToolbar(){
        // set support action bar dengan toolbar pada layout
        setSupportActionBar(toolbar)
    }

    fun initListener(){
        // set tombol fab ketika di klik
        fab.setOnClickListener {
            // jalankan intent ke Form Diary Activity
            val intent = Intent(this@ListDiaryActivity, FormDiaryActivity::class.java)
            intent.putExtra("isEdit", false) // tambahkan extra kalau form berupa create (isEdit = false)
            startActivity(intent) // jalankan actvity
        }
    }

    // buat variable untuk daftar data yang akan ditampilkan
    val lists = arrayListOf<ListDiaryModel>()

    fun initRecycler(){
        // inisialisasi list recycler
        rvListDiary.adapter = ListDiaryAdapter(lists)   // buat adapter untuk recylerview berdasarka ListDiaryAdapter
        // tampilkan isi recyclerview menjadi vertical
        rvListDiary.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
    }

    fun loadData(){
        // tambahkan data dummy ke dalam list
        lists.add(
            ListDiaryModel(
                "","paradise","10 jun 2020","https://i.ibb.co/y8XHwYS/island.png"
            )
        )
        lists.add(ListDiaryModel(
            "","paradise2","10 jun 2020","https://i.ibb.co/y8XHwYS/island.png"
        ))
        lists.add(ListDiaryModel(
            "","paradise3","10 jun 2020","https://i.ibb.co/y8XHwYS/island.png"
        ))
        lists.add(ListDiaryModel(
            "","paradise4","10 jun 2020","https://i.ibb.co/y8XHwYS/island.png"
        ))
        lists.add(
            ListDiaryModel(
                "", "paradise5", "10 jun 2020", "https://i.ibb.co/y8XHwYS/island.png"
            )
        )

        // beritaukan jika isi list berubah
        rvListDiary.adapter?.notifyDataSetChanged()
    }
}

