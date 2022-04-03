import Notes.NoteNotFound
import org.junit.Test

import org.junit.Assert.*
import java.lang.Exception

class NotesTest {

    @Test
    fun add() {
        val notes = Notes()
        var i = 1

        while (i < 20) {
            notes.add("Title$i", "This is note $i", 0, 0)
            i++
        }

        assertEquals(notes.add("Title 20", "This is note 20", 0, 0), 20)
    }

    @Test
    fun createComment() {
        val notes = Notes()
        var i = 1

        while (i <= 3) {
            notes.add("Title$i", "This is note $i", 0, 0)
            var j = 1
            while (j <= i) {
                notes.createComment(i, "This comment №$j about note $i")
                j++
            }
            i++
        }

        assertEquals(notes.createComment(1, "This comment №7 about note 1"), 7)
    }

    @Test
    fun deleteTrue() {
        val notes = Notes()
        var i = 1

        while (i < 20) {
            notes.add("Title$i", "This is note $i", 0, 0)
            i++
        }

        assertTrue(notes.delete(19))
    }

    @Test
    fun deleteFalse() {
        val notes = Notes()
        var i = 1

        while (i < 20) {
            notes.add("Title$i", "This is note $i", 0, 0)
            i++
        }

        assertFalse(notes.delete(20))
    }

    @Test
    fun deleteCommentTrue() {
        val notes = Notes()
        var i = 1

        while (i <= 3) {
            notes.add("Title$i", "This is note $i", 0, 0)
            var j = 1
            while (j <= i) {
                notes.createComment(i, "This comment №$j about note $i")
                j++
            }
            i++
        }
        assertTrue(notes.deleteComment(6))
    }

    @Test
    fun deleteCommentFalse() {
        val notes = Notes()
        var i = 1

        while (i <= 3) {
            notes.add("Title$i", "This is note $i", 0, 0)
            var j = 1
            while (j <= i) {
                notes.createComment(i, "This comment №$j about note $i")
                j++
            }
            i++
        }
        assertFalse(notes.deleteComment(7))
    }

    @Test
    fun editTrue() {
        val notes = Notes()
        var i = 1

        while (i < 20) {
            notes.add("Title$i", "This is note $i", 0, 0)
            i++
        }

        assertTrue(notes.edit(19, "Title", "This is new note 19", 0, 0))
    }

    @Test
    fun editFalse() {
        val notes = Notes()
        var i = 1

        while (i < 20) {
            notes.add("Title$i", "This is note $i", 0, 0)
            i++
        }

        assertFalse(notes.edit(20, "Title", "This is new note 20", 0, 0))
    }

    @Test
    fun editCommentTrue() {
        val notes = Notes()
        var i = 1

        while (i <= 3) {
            notes.add("Title$i", "This is note $i", 0, 0)
            var j = 1
            while (j <= i) {
                notes.createComment(i, "This comment №$j about note $i")
                j++
            }
            i++
        }
        assertTrue(notes.editComment(6, "new message"))
    }

    @Test
    fun editCommentFalse() {
        val notes = Notes()
        var i = 1

        while (i <= 3) {
            notes.add("Title$i", "This is note $i", 0, 0)
            var j = 1
            while (j <= i) {
                notes.createComment(i, "This comment №$j about note $i")
                j++
            }
            i++
        }
        assertFalse(notes.editComment(7, "new message"))
    }

    @Test
    fun get() {
        val notes = Notes()
        var i = 1
        while (i < 20) {
            notes.add("Title$i", "This is note $i", 0, 0)
            i++
        }

        val testNotes = Notes()
        var j = 2
        while (j <= 4) {
            testNotes.add("Title$j", "This is note $j", 0, 0)
            j++
        }

        assertEquals(notes.get("1 2 3 4 5", 1, 3), testNotes.get("1 2 3", 0, 3))
    }

    @Test
    fun getById() {
        val notes = Notes()
        var i = 1
        while (i < 20) {
            notes.add("Title$i", "This is note $i", 0, 0)
            i++
        }

        assertEquals(notes.getById(10).title, "Title10")
    }

    @Test (expected = NoteNotFound :: class)
    fun getById_withException() {
        val notes = Notes()
        var i = 1
        while (i < 20) {
            notes.add("Title$i", "This is note $i", 0, 0)
            i++
        }
        notes.getById(30).title
    }

    @Test
    fun getComments() {
        val notes = Notes()
        var i = 1

        while (i <= 3) {
            notes.add("Title$i", "This is note $i", 0, 0)
            var j = 1
            while (j <= i) {
                notes.createComment(i, "This comment №$j about note $i")
                j++
            }
            i++
        }

        assertEquals(notes.getComments(3)[0].message, "This comment №1 about note 3")
        assertEquals(notes.getComments(3)[1].message, "This comment №2 about note 3")
        assertEquals(notes.getComments(3)[2].message, "This comment №3 about note 3")
    }

    @Test
    fun restoreCommentTrue() {
        val notes = Notes()
        var i = 1

        while (i <= 3) {
            notes.add("Title$i", "This is note $i", 0, 0)
            var j = 1
            while (j <= i) {
                notes.createComment(i, "This comment №$j about note $i")
                j++
            }
            i++
        }
        notes.deleteComment(6)
        assertTrue(notes.restoreComment(6))
    }

    @Test
    fun restoreCommentFalse() {
        val notes = Notes()
        var i = 1

        while (i <= 3) {
            notes.add("Title$i", "This is note $i", 0, 0)
            var j = 1
            while (j <= i) {
                notes.createComment(i, "This comment №$j about note $i")
                j++
            }
            i++
        }
        notes.deleteComment(6)
        assertFalse(notes.restoreComment(7))
    }
}