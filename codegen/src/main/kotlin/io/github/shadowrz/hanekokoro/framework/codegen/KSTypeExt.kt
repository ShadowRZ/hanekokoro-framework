package io.github.shadowrz.hanekokoro.framework.codegen

import com.google.devtools.ksp.getAllSuperTypes
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSType
import com.squareup.kotlinpoet.ClassName

fun KSType.extendsClass(className: ClassName): Boolean {
    val superTypes =
        (this.declaration as? KSClassDeclaration)?.getAllSuperTypes() ?: emptySequence()

    return superTypes.any { it.declaration.qualifiedName?.asString() == className.canonicalName }
}
