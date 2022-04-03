data class Note(
    val title: String,
    val text: String,
    val privacy: Int,
    val commentPrivacy: Int,
    var deleted: Boolean = false
)