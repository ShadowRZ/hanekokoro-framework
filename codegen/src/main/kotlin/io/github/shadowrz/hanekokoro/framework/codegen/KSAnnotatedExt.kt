package io.github.shadowrz.hanekokoro.framework.codegen

import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSAnnotation
import com.squareup.kotlinpoet.ClassName
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

fun KSAnnotated.isAnnotatedWith(annotation: ClassName): Boolean =
    this.annotations.any {
        it.shortName.getShortName() == annotation.simpleName &&
            it.annotationType.resolve().declaration.qualifiedName?.asString() == annotation.canonicalName
    }

fun <T : Annotation> KSAnnotated.isAnnotatedWith(annotationKClass: KClass<T>): Boolean =
    this.annotations.any {
        it.shortName.getShortName() == annotationKClass.simpleName &&
            it.annotationType.resolve().declaration.qualifiedName?.asString() == annotationKClass.qualifiedName
    }
