package dam.virtual_library

abstract class Book(
    val title: String,
    val author: String,
    private val year: Int,
    availableCopies: Int
){
    init {
        println("$title by $author created!")
    }
    val publicationYear: String
    get() = when {
        year < 1980 -> "Classic"
        year in 1980..2010 -> "Modern"
        else -> "Contemporary"
    }

    var availableCopies: Int = availableCopies
    set(value) {
        if (value < 0)
            println("Error: Number of copies cannot be negative!")
        else
            field = value
            if (value == 0)
                println("Warning: Book $title is now out of stock!")
    }

    abstract fun getStorageInfo(): String

    override fun toString(): String {
        return "Title: $title, Author: $author, Era: $publicationYear, Copies: $availableCopies"
    }
}

class DigitalBook(
    title: String,
    author: String,
    year: Int,
    availableCopies: Int,
    val fileSize: Double,
    val format: String
) : Book(title, author, year, availableCopies) {

    override fun getStorageInfo(): String {
        return "Stored digitally: $fileSize MB, Format: $format"
    }

    override fun toString(): String {
        return super.toString()
    }
}

class PhysicalBook(
    title: String,
    author: String,
    year: Int,
    availableCopies: Int,
    val weight: Int,
    val hasHardcover: Boolean = true
) : Book(title, author, year, availableCopies) {

    override fun getStorageInfo(): String {
        val hardcoverText = if (hasHardcover) "Yes" else "No"
        return "Physical book: ${weight}g, Hardcover: $hardcoverText"
    }

    override fun toString(): String {
        val hardcoverText = if (hasHardcover) "Yes" else "No"
        return super.toString()
    }
}

class Library(val name: String) {
    private val books = mutableListOf<Book>()

    fun addBook(book: Book) {
        books.add(book)
        println("Book ${book.title} by ${book.author} added to library!")
    }

    fun borrowBook(title: String) {
        val book = books.find { it.title == title }

        if (book == null) {
            println("Error: Book $title is not part of the library!")
        }
        else if (book.availableCopies > 0) {
            book.availableCopies -= 1
            println("Success: You borrowed ${book.title}! Copies remaining: ${book.availableCopies}")
        }
        else {
            println("Warning: No copies available of book $title.")
        }
    }

    fun returnBook(title: String) {
        val book = books.find { it.title == title }

        if (book == null) {
            println("Error: Book not found.")
        } else {
            book.availableCopies += 1
            println("Success: You returned ${book.title}.")
        }
    }

    fun showBooks() {
        println("\n--- Library Catalog ---")

        if (books.isEmpty()) {
            println("No books in the library.")
            return
        }

        books.forEach { book ->
            println(book)
            println("   Storage: ${book.getStorageInfo()}")
        }
    }

    fun searchByAuthor(author: String) {
        val results = books.filter { it.author == author }

        if (results.isEmpty()) {
            println("Error: No books found by $author.")
        } else {
            println("Books by $author:")
            results.forEach {
                println(" - ${it.title} (${it.publicationYear}, ${it.availableCopies} copies available)")
            }
        }
    }

    companion object {
        private var totalBooksAdded: Int = 0

        fun getTotalBooksCreated(): Int {
            return totalBooksAdded
        }
    }
}

data class LibraryMember(
    val name: String,
    val membershipId: String,
    val borrowedBooks: List<String>
)

fun main() {
    val library = Library("Central Library")
    val digitalBook = DigitalBook(
        "Kotlin in Action",
        "Dmitry Jemerov",
        2017,
        5,
        4.5,
        "PDF"
    )
    val physicalBook = PhysicalBook(
        "Clean Code",
        "Robert C. Martin",
        2008,
        3,
        650,
        true
    )
    val classicBook = PhysicalBook(
        "1984",
        "George Orwell",
        1949,
        2,
        400,
        false
    )

    library.addBook(digitalBook)
    library.addBook(physicalBook)
    library.addBook(classicBook)
    library.showBooks()

    println("\n--- Borrowing Books ---")
    library.borrowBook("Clean Code")
    library.borrowBook("1984")
    library.borrowBook("1984")
    library.borrowBook("1984")

    println("\n--- Returning Books ---")
    library.returnBook("1984")
    println("\n--- Search by Author ---")
    library.searchByAuthor("George Orwell")

}