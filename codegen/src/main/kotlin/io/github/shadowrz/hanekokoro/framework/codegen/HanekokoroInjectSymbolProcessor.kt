package io.github.shadowrz.hanekokoro.framework.codegen

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.validate
import io.github.shadowrz.hanekokoro.framework.annotations.HanekokoroInject

class HanekokoroInjectSymbolProcessor(
    private val codeGenerator: CodeGenerator,
) : SymbolProcessor {
    private val componentProcessor = ContributesComponentSymbolProcessor(codeGenerator = codeGenerator)
    private val rendererProcessor = ContributesRendererSymbolProcessor(codeGenerator = codeGenerator)

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver
            .getSymbolsWithAnnotation(HanekokoroInject::class.qualifiedName!!)
        val (valid, invalid) = symbols.partition { it.validate() }

        valid.forEach {
            if (it is KSClassDeclaration) generateForClass(it)
            if (it is KSFunctionDeclaration) generateForFunction(it, Symbols(resolver))
        }

        return invalid + componentProcessor.process(resolver) + rendererProcessor.process(resolver)
    }

    private fun generateForClass(klass: KSClassDeclaration) {
        if (klass.asStarProjectedType().extendsClass(Symbols.Names.Component)) {
            componentProcessor.generateComponentAssistedFactory(klass)
        }
        if (klass.asStarProjectedType().extendsClass(Symbols.Names.Renderer)) {
            rendererProcessor.generateRendererBindingContainer(klass)
        }
    }

    private fun generateForFunction(
        function: KSFunctionDeclaration,
        symbols: Symbols,
    ) {
        rendererProcessor.generateRendererClass(function, symbols)
    }
}
