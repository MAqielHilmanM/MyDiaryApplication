package motion.kelas.mydiaryapplication.list_diary

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_diary.view.*
import motion.kelas.mydiaryapplication.R
import motion.kelas.mydiaryapplication.detail_diary.DetailDiaryActivity

class ListDiaryAdapter(var list: List<ListDiaryModel>) :
    RecyclerView.Adapter<ListDiaryAdapter.ListDiaryViewHolder>() {


    // buat view holder untuk recycler view item
    inner class ListDiaryViewHolder(itemView : View, val context: Context) : RecyclerView.ViewHolder(itemView){
        // buat fungsi untuk mengambil data dari list dan memasukkannya ke layout
        fun initData(data : ListDiaryModel){
            // ubah text pada layout sesuai data
            itemView.tvItemListDiaryTitle.text = data.title
            itemView.tvItemListDiaryDate.text = data.date

            // untuk file gambar dapat menggunakan picasso
            Picasso.get().load(data.url).into(itemView.ivItemListDiary)

            // tambahkan aksi ketika item/layout di klik
            itemView.setOnClickListener {
                // pindah ke activity detail diary
                val intent = Intent(context, DetailDiaryActivity::class.java)
                intent.putExtra("id", data.id)
                context.startActivity(intent)              // pindah ke activity baru
            }

            // duplicate : ctrl + d
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDiaryViewHolder {
        // load item_diary.xml ke dalam view
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_diary,parent,false)
        // tambahkan view ke ViewHolder
        return ListDiaryViewHolder(v,parent.context)
    }

    override fun getItemCount(): Int {
        return list.size    // jum;ah data yang ditampilkan sebanyak isi list
    }

    override fun onBindViewHolder(holder: ListDiaryViewHolder, position: Int) {
        // setiap kali data berubah panggil fungsi pada view holder yaitu initdata()
        holder.initData(list[position])
    }
}