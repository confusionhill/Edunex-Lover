package models

data class KelasModel(
    val course_id: Long,
    val course_code: String,
    val courses_name: String,
    val class_id: Long,
    val class_name: String,
    val semester: Int?,
    val year: String,
    val presences: String? = null
)
