package objectcalisthenics

import JavaFile
import analyser.AllFine
import analyser.CodeAnalysis
import analyser.JavaFileFeedback
import analyser.RoomForImprovement
import com.github.javaparser.ast.body.FieldDeclaration
import com.github.javaparser.ast.type.Type

class FirstClassCollectionsConstraint : CodeAnalysis {
    override fun evaluate(javaFile: JavaFile): JavaFileFeedback {
        val fields: MutableList<FieldDeclaration> = javaFile.parse().fields
        if (fields.size <= 1) {
            return AllFine()
        }

        if (fields.none { it.elementType.isCollectionType() }) {
            return AllFine()
        }

        return RoomForImprovement(
            "This class has a collection field, and " +
                    "it's not the only field. You could consider wrapping the " +
                    "collection field into its own class, if that would simplify " +
                    "your program due to the role of this field in the problem " +
                    "domain."
        )
    }
}

private fun Type.isCollectionType(): Boolean {
    return toString().takeWhile { it != '<' }.run {
        listOf(
            "List",
            "Map",
            "ArrayList",
            "HashMap",
            "Set",
            "HashSet",
            "Enumeration",
            "TreeMap",
            "Iterator",
            "Iterable"
        ).contains(this)
    }
}