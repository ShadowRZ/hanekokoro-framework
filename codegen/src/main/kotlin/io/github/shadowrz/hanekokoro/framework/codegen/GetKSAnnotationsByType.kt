package io.github.shadowrz.hanekokoro.framework.codegen

import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSAnnotation
import kotlin.reflect.KClass
import kotlin.sequences.filter

fun <T : Annotation> KSAnnotated.getKSAnnotationsByType(annotationKClass: KClass<T>): Sequence<KSAnnotation> =
    this.annotations.filter {
        it.shortName.getShortName() == annotationKClass.simpleName && it.annotationType
            .resolve()
            .declaration
            .qualifiedName
            ?.asString() == annotationKClass.qualifiedName
    }
