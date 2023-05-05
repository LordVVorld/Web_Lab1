import java.lang.NullPointerException

class Node(var value: Int) {
    var left: Node? = null
    var right: Node? = null
}

class BinaryTree {
    private var root: Node? = null

    fun find(value: Int): Node {
        return findRecursive(root, value)
    }

    fun insert(value: Int) {
        root = insertRecursive(root, value)
    }

    fun remove(value: Int) {
        root = removeRecursive(root, value)
    }

    fun traverse(): List<Int> {
        val result = mutableListOf<Int>()
        traverseRecursive(root, result)
        return result
    }

    private fun findRecursive(current: Node?, value: Int): Node {
        current ?: throw NoSuchElementException()

        return when {
            value < current.value -> findRecursive(current.left, value)
            value > current.value -> findRecursive(current.right, value)
            else -> current
        }
    }

    private fun insertRecursive(current: Node?, value: Int): Node {
        current ?: return Node(value)

        when {
            value < current.value -> current.left = insertRecursive(current.left, value)
            value > current.value -> current.right = insertRecursive(current.right, value)
            else -> return current
        }

        return current
    }

    private fun removeRecursive(current: Node?, value: Int): Node? {
        current ?: return null

        when {
            value < current.value -> current.left = removeRecursive(current.left, value)
            value > current.value -> current.right = removeRecursive(current.right, value)
            else -> {
                when {
                    current.left == null -> return current.right
                    current.right == null -> return current.left
                    else -> {
                        current.value = findMinValue(current.right)
                        current.right = removeRecursive(current.right, current.value)
                    }
                }
            }
        }

        return current
    }

    private fun traverseRecursive(node: Node?, result: MutableList<Int>) {
        node?.let {
            traverseRecursive(it.left, result)
            result.add(it.value)
            traverseRecursive(it.right, result)
        }
    }

    private fun findMinValue(node: Node?): Int {
        return node?.left?.let { findMinValue(it) } ?: node?.value ?: throw NullPointerException()
    }
}