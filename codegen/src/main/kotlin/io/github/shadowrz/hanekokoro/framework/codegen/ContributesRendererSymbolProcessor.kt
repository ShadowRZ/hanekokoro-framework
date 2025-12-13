package io.github.shadowrz.hanekokoro.framework.codegen

import com.google.devtools.ksp.getAllSuperTypes
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSType
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.plusParameter
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.STAR
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.toTypeName
import com.squareup.kotlinpoet.ksp.writeTo
import dev.zacsweers.metro.BindingContainer
import dev.zacsweers.metro.Binds
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.IntoMap
import dev.zacsweers.metro.Origin
import io.github.shadowrz.hanekokoro.framework.annotations.HanekokoroInject

class ContributesRendererSymbolProcessor(
    private val codeGenerator: CodeGenerator,
) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver
            .getSymbolsWithAnnotation(HanekokoroInject.ContributesRenderer::class.qualifiedName!!)
        val (valid, invalid) = symbols.partition { it.validate() }

        if (valid.isEmpty()) return invalid

        val resolveSymbols = Symbols(resolver)

        valid.forEach {
            if (it is KSClassDeclaration) generateRendererBindingContainer(it)
            if (it is KSFunctionDeclaration) generateRendererClass(it, resolveSymbols)
        }

        return invalid
    }

    internal fun generateRendererClass(
        function: KSFunctionDeclaration,
        symbols: Symbols,
    ) {
        if (!function.isAnnotatedWith(Symbols.Names.Composable)) {
            error(
                "${function.qualifiedName?.asString()} must be annotated with @Composable.",
            )
        }
        val packageName = function.containingFile!!.packageName.asString()
        val className = "${function.simpleName.asString()}_ComposeRenderer"

        val componentType = function.parameters[0].type.toTypeName()
        val contributionAnnotations = function.getKSAnnotationsByType(HanekokoroInject.ContributesRenderer::class)

        function.parameters[1].let {
            if (it.name?.asString() != "modifier") {
                error(
                    "${function.qualifiedName?.asString()}'s second paramter must named 'modifier'.",
                )
            }

            val resolvedType = it.type.resolve()
            if (!symbols.modifier.isAssignableFrom(resolvedType)) {
                error(
                    "${function.qualifiedName?.asString()}'s second paramter must be of type 'Modifier'.",
                )
            }
        }

        FileSpec
            .builder(packageName, className)
            .addType(
                TypeSpec
                    .classBuilder(className)
                    .apply {
                        addSuperinterface(Symbols.Names.Renderer.plusParameter(componentType))
                        addAnnotation(
                            AnnotationSpec.builder(Origin::class).addMember(Symbols.Placeholders.CLASS_PLACEHOLDER, componentType).build(),
                        )
                        addAnnotation(
                            AnnotationSpec.builder(
                                Symbols.Names.ComponentKey,
                            ).addMember(Symbols.Placeholders.CLASS_PLACEHOLDER, componentType).build(),
                        )
                        contributionAnnotations.forEach { annotation ->
                            val scope = annotation.arguments.single { it.name?.asString() == "scope" }.value as KSType

                            addAnnotation(
                                AnnotationSpec
                                    .builder(ContributesIntoMap::class)
                                    .addMember("scope = %T::class", scope.toTypeName())
                                    .addMember("binding = %L", RendererBindingAnnotation)
                                    .build(),
                            )
                        }

                        addAnnotation(Inject::class)

                        addFunction(
                            FunSpec
                                .builder("Content")
                                .addModifiers(KModifier.OVERRIDE)
                                .addAnnotation(Symbols.Names.Composable)
                                .addParameters(
                                    listOf(
                                        ParameterSpec.builder("component", componentType).build(),
                                        ParameterSpec.builder("modifier", Symbols.Names.Modifier).build(),
                                    ),
                                ).addStatement(
                                    "return %M(component = component, modifier = modifier)",
                                    MemberName(packageName, function.simpleName.getShortName()),
                                ).build(),
                        )
                    }.build(),
            ).build()
            .writeTo(codeGenerator, dependencies = Dependencies(true, function.containingFile!!))
    }

    internal fun generateRendererBindingContainer(klass: KSClassDeclaration) {
        val componentType = componentType(klass)

        val rendererClass = klass.toClassName()
        val packageName = klass.containingFile!!.packageName.asString()
        val className = "${klass.simpleName.asString()}_RendererBindingContainer"
        val contributionAnnotations = klass.getKSAnnotationsByType(HanekokoroInject.ContributesRenderer::class)

        FileSpec.builder(packageName, className)
            .addType(
                TypeSpec
                    .interfaceBuilder(className)
                    .apply {
                        addAnnotation(AnnotationSpec.builder(BindingContainer::class).build())
                        addAnnotation(
                            AnnotationSpec.builder(Origin::class).addMember(Symbols.Placeholders.CLASS_PLACEHOLDER, rendererClass).build(),
                        )
                        contributionAnnotations.forEach { annotation ->
                            val scope = annotation.arguments.single { it.name?.asString() == "scope" }.value as KSType

                            addAnnotation(
                                AnnotationSpec
                                    .builder(ContributesTo::class)
                                    .addMember("scope = %T::class", scope.toTypeName())
                                    .build(),
                            )
                        }
                        addProperty(
                            PropertySpec
                                .builder("bindRenderer", Symbols.Names.Renderer.parameterizedBy(STAR))
                                .addAnnotation(Binds::class)
                                .addAnnotation(IntoMap::class)
                                .addAnnotation(
                                    AnnotationSpec.builder(
                                        Symbols.Names.ComponentKey,
                                    ).addMember(Symbols.Placeholders.CLASS_PLACEHOLDER, componentType.toClassName()).build(),
                                )
                                .receiver(rendererClass)
                                .build(),
                        )
                    }.build(),
            ).build()
            .writeTo(codeGenerator, dependencies = Dependencies(true, klass.containingFile!!))
    }

    private fun componentType(klass: KSClassDeclaration): KSClassDeclaration {
        val components = klass.getAllSuperTypes().flatMap { superType ->
            superType.arguments.filter {
                it.type?.resolve()?.extendsComponent()
                    ?: false
            }
        }
            .mapNotNull { it.type?.resolve()?.declaration as? KSClassDeclaration }
            .distinctBy { it.requireQualifiedName() }
            .toList()

        check(components.size == 1) {
            buildString {
                append("Couldn't determine the Component this renderer is for. ")
                if (components.size > 1) {
                    append("Found: ")
                    append(components.joinToString { it.requireQualifiedName() })
                }
            }
        }

        return components.first()
    }

    private fun KSType.extendsComponent(): Boolean = this.extendsClass(Symbols.Names.Component)

    private companion object {
        val RendererBindingAnnotation = AnnotationSpec
            .builder(
                Symbols.Names.binding.plusParameter(
                    Symbols.Names.Renderer.plusParameter(STAR),
                ),
            ).build()
    }
}
