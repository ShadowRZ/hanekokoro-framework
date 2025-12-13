package io.github.shadowrz.hanekokoro.framework.codegen

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.isAnnotationPresent
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSType
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.plusParameter
import com.squareup.kotlinpoet.STAR
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.toTypeName
import com.squareup.kotlinpoet.ksp.writeTo
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.Origin
import io.github.shadowrz.hanekokoro.framework.annotations.HanekokoroInject

class ContributesComponentSymbolProcessor(
    private val codeGenerator: CodeGenerator,
) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver
            .getSymbolsWithAnnotation(HanekokoroInject.ContributesComponent::class.qualifiedName!!)
        val (valid, invalid) = symbols.partition { it.validate() }

        if (valid.isEmpty()) return invalid

        valid.forEach {
            if (it is KSClassDeclaration) generateComponentAssistedFactory(it)
        }

        return invalid
    }

    @OptIn(KspExperimental::class)
    internal fun generateComponentAssistedFactory(klass: KSClassDeclaration) {
        val packageName = klass.containingFile!!.packageName.asString()
        val className = "${klass.simpleName.asString()}_AssistedFactory"
        val contributionAnnotations = klass.getKSAnnotationsByType(HanekokoroInject.ContributesComponent::class)
        val primaryConstructor = klass.primaryConstructor!!
        val assistedParamters = primaryConstructor.parameters.filter { it.isAnnotationPresent(Assisted::class) }

        if (assistedParamters.size != 2) {
            error(
                "${klass.qualifiedName?.asString()} must have a primary constructor with exactly 2 @Assisted paramters," +
                    "current: ${assistedParamters.size}",
            )
        }

        assistedParamters[0].let {
            if (it.name?.asString() != "context") {
                error(
                    "${klass.qualifiedName?.asString()}'s first primary constructor param must named 'context'.",
                )
            }
        }

        assistedParamters[1].let {
            if (it.name?.asString() != "plugins") {
                error(
                    "${klass.qualifiedName?.asString()}'s third primary constructor param must named 'plugins'.",
                )
            }
        }

        FileSpec
            .builder(packageName, className)
            .addType(
                TypeSpec
                    .interfaceBuilder(className)
                    .apply {
                        addSuperinterface(Symbols.Names.ComponentFactory.plusParameter(assistedParamters[0].type.toTypeName()))
                        addAnnotation(AnnotationSpec.builder(Origin::class).addMember("%T::class", klass.toClassName()).build())
                        addAnnotation(
                            AnnotationSpec.builder(Symbols.Names.ComponentKey).addMember("%T::class", klass.toClassName()).build(),
                        )
                        contributionAnnotations.forEach { annotation ->
                            val scope = annotation.arguments.single { it.name?.asString() == "scope" }.value as KSType

                            addAnnotation(
                                AnnotationSpec
                                    .builder(ContributesIntoMap::class)
                                    .addMember("scope = %T::class", scope.toTypeName())
                                    .addMember("binding = %L", ComponentFactoryBindingAnnotation)
                                    .build(),
                            )
                        }

                        addAnnotation(AssistedFactory::class)

                        addFunction(
                            FunSpec
                                .builder("create")
                                .addParameters(
                                    listOf(
                                        ParameterSpec
                                            .builder(
                                                "context",
                                                assistedParamters[0].type.toTypeName(),
                                            ).build(),
                                        ParameterSpec
                                            .builder(
                                                "plugins",
                                                List::class.asClassName().plusParameter(Symbols.Names.Plugin),
                                            ).build(),
                                    ),
                                ).returns(klass.toClassName())
                                .addModifiers(KModifier.ABSTRACT, KModifier.OVERRIDE)
                                .build(),
                        )
                    }.build(),
            ).build()
            .writeTo(codeGenerator, dependencies = Dependencies(true, klass.containingFile!!))
    }

    private companion object {
        val ComponentFactoryBindingAnnotation = AnnotationSpec
            .builder(
                Symbols.Names.binding.plusParameter(
                    Symbols.Names.ComponentFactory.plusParameter(STAR),
                ),
            ).build()
    }
}
