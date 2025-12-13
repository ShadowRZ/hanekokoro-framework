package io.github.shadowrz.hanekokoro.framework.codegen

import com.google.devtools.ksp.symbol.KSDeclaration

fun KSDeclaration.requireQualifiedName(): String = requireNotNull(qualifiedName?.asString()) { "$this doesn't have a qualified name!" }
