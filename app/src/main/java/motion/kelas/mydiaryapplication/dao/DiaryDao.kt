package motion.kelas.mydiaryapplication.dao

import com.google.firebase.Timestamp

data class DiaryDao(
    var id : String? = "",
    var date : Timestamp? = Timestamp.now(),
    var story : String? = "",
    var title : String? = "",
    var url : String? = ""
)