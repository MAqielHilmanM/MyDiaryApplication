package motion.kelas.mydiaryapplication.list_diary

import java.io.Serializable

// membuat model berdasarkan data yang akan ditampilkan di item_diary.xml
data class ListDiaryModel(
    var id: String,
    var title : String,
    var date : String,
    var url : String,
    var story : String = ""
) : Serializable // implement serializable agar data class dapat dikirimkan antar activity