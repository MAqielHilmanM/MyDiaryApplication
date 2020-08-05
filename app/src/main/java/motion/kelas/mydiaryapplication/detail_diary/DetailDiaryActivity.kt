package motion.kelas.mydiaryapplication.detail_diary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_detail_diary.*
import motion.kelas.mydiaryapplication.R
import motion.kelas.mydiaryapplication.form_diary.FormDiaryActivity
import motion.kelas.mydiaryapplication.list_diary.ListDiaryModel
import motion.kelas.mydiaryapplication.load

class DetailDiaryActivity : AppCompatActivity() {

    lateinit var model : ListDiaryModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_diary)

        initToolbar()
        loadData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuEdit -> {
                val intent = Intent(this, FormDiaryActivity::class.java)
                intent.putExtra("isEdit", true)
                intent.putExtra("model",model)
                startActivity(intent)
                return true
            }
            R.id.menuDelete -> {
                val builder = AlertDialog.Builder(this@DetailDiaryActivity)

                builder.setTitle("Warning")
                builder.setMessage("Are you sure to delete this diary?")

                builder.setPositiveButton("OK") { dialog, which ->
                    finish()
                }
                builder.setNegativeButton("Cancel") { dialog, which ->
                    dialog.dismiss()
                }

                val dialog: AlertDialog = builder.create()
                dialog.show()

                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
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
//        val title = intent?.extras?.getString("title","")
//        val date = intent?.extras?.getString("date","")
//        val url = intent?.extras?.getString("url","")
//        val story = intent?.extras?.getString("story","")

        model = intent?.extras?.getSerializable("model") as ListDiaryModel
        val title = model.title
        val date = model.date
        val url = model.url
        val story = model.story

        tvDetailDiaryDate.text = date
        tvDetailDiaryTitle.text = title
        tvDetailDiaryDescription.text = story
        ivDetailDiary.load(url!!)
    }}
