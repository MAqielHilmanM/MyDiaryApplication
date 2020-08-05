package motion.kelas.mydiaryapplication

import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_diary.view.*

// buat fungsi load untuk image view dengan parameter url
fun ImageView.load(url : String){
    // load gambar dari url ke image view dengan picasso
    Picasso.get().load(url).centerCrop().into(this)
}