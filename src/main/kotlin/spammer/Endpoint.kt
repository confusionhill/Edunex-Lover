package spammer

object Endpoint {
    fun getClassesEndpoint(): String {
        return "https://api-edunex.cognisia.id/course/presences/list"
    }

    fun getCoursesEndpoint(courseId: Int, classId: Int): String {
        return "https://api-edunex.cognisia.id/course/presences?filter[course_id][is]=39702&filter[class_id][is]=35016&sort=start_at&page[limit]=35&page[offset]=0"
    }
}