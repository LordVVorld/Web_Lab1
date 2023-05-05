import java.util.*
import kotlin.random.Random

class MobilePhone(
    private val myNumber: String
) {
    private var contactList: MutableList<Contact> = mutableListOf()
    init {
        for (index in 1..(Random.nextInt(1, 6))){
            contactList.add(Contact(generatePhoneNumber(), generateName()))
        }
    }

    fun addNewContact(contact: Contact): Boolean {
        return if (contactList.contains(contact)){
            false
        } else {
            contactList.add(contact)
            true
        }
    }

    fun updateContact(oldContact: Contact, newContact: Contact): Boolean {
        return if (contactList.contains(oldContact)){
            contactList[contactList.indexOf(oldContact)] = newContact
            true
        } else {
            false
        }
    }

    fun findContact(contact: Contact): Int {
        return if (contactList.contains(contact)) {
            contactList.indexOf(contact)
        } else {
            -1
        }
    }

    fun queryContact(name: String): Contact? {

        return if (contactList.any { contact: Contact ->  contact.name == name}) {
            contactList.find { contact: Contact -> contact.name == name }
        } else {
            null
        }
    }

    fun printContacts() {
        for (contact in contactList){
            println(contact)
        }
    }
}

data class Contact(
    val phoneNumber: String,
    val name: String
)

fun generatePhoneNumber(): String {
    return Random.nextInt(0,10).toString() +
            " " + Random.nextInt(100,1000).toString() +
            " " + Random.nextInt(100,1000).toString() +
            "-" + Random.nextInt(10,100).toString() +
            "-" + Random.nextInt(10,100).toString()
}

fun generateName(): String {
    val alphabet: List<Char> = ('a'..'z').toList()
    return (1..Random.nextInt(3, 7))
        .map { Random.nextInt(0, alphabet.size).let { alphabet[it] } }
        .joinToString("")
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}
