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

        initToolbar()
        initListener()
        initRecycler()
        loadData()
        initNewData()
    }

    fun initNewData(){
        if( intent?.extras?.getSerializable("model") != null){
            val model = intent?.extras?.getSerializable("model") as ListDiaryModel
            lists.add(model)
            rvListDiary.adapter?.notifyDataSetChanged()
        }
    }


    fun initToolbar(){
        setSupportActionBar(toolbar)
    }

    fun initListener(){
        fab.setOnClickListener {
            val intent = Intent(this@ListDiaryActivity, FormDiaryActivity::class.java)
            intent.putExtra("isEdit", false)
            startActivity(intent)
        }
    }


    val lists = arrayListOf<ListDiaryModel>()
    fun initRecycler(){
        rvListDiary.adapter = ListDiaryAdapter(lists)
        rvListDiary.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
    }

    fun loadData(){
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
        rvListDiary.adapter?.notifyDataSetChanged()
    }
}

