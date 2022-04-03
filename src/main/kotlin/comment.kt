data class Comment(
    val noteID: Int,
    val message: String,
    var deleted: Boolean = false
)