import java.lang.RuntimeException

class Notes {
    private val notes = mutableListOf<Note>()
    private val comments = mutableListOf<Comment>()

    fun add(title: String, text: String, privacy: Int, commentPrivacy: Int): Int {
        notes.add(Note(title, text, privacy, commentPrivacy))
        return notes.size
    }

    fun createComment(noteID: Int, message: String): Int {
        if (notes.size >= noteID) {
            comments.add(Comment(noteID, message))
            return comments.size
        }
        return 0
    }

    fun delete(noteID: Int): Boolean {
        if (notes.size >= noteID) {
            notes[noteID - 1].deleted = true
            return true
        }
        return false
    }

    fun deleteComment(commentID: Int): Boolean {
        if (comments.size >= commentID) {
            comments[commentID - 1].deleted = true
            return true
        }
        return false
    }

    fun edit(noteID: Int, title: String, text: String, privacy: Int, commentPrivacy: Int): Boolean {
        if (notes.size >= noteID && !notes[noteID - 1].deleted) {
            notes[noteID - 1] = Note(title, text, privacy, commentPrivacy)
            return true
        }
        return false
    }

    fun editComment(commentID: Int, message: String): Boolean {
        if (comments.size >= commentID && !comments[commentID - 1].deleted && !notes[comments[commentID - 1].noteID - 1].deleted) {
            comments[commentID - 1] = comments[commentID - 1].copy(message = message)
            return true
        }
        return false
    }

    fun get(noteIDs: String, offset: Int, count: Int): List<Note> {
        val listRequest = noteIDs.split(" ")
        val listRequestNote = mutableListOf<Note>()
        var i = offset
        var cnt = count
        while (i < offset + cnt) {
            if (!notes[listRequest[i].toInt() - 1].deleted) {
                listRequestNote.add(notes[listRequest[i].toInt() - 1])
            } else {
                cnt++
            }
            i++
        }
        return listRequestNote
    }

    fun getById(noteID: Int): Note {
            if (notes.size >= noteID && !notes[noteID - 1].deleted) {
                return notes[noteID - 1]
            } else throw NoteNotFound ("Note $noteID not found")
    }

    class NoteNotFound (message: String): RuntimeException(message)

    fun getComments(noteID: Int): Array<Comment> {
        var arrComment = emptyArray<Comment>()
        if (notes.size >= noteID && !notes[noteID - 1].deleted) {
            for (comment in comments) {
                if (comment.noteID == noteID) {
                    arrComment += comment
                }
            }
        }
        return arrComment
    }

    fun restoreComment(commentID: Int): Boolean {
        if (comments.size >= commentID) {
            comments[commentID - 1].deleted = false
            return true
        }
        return false
    }
}