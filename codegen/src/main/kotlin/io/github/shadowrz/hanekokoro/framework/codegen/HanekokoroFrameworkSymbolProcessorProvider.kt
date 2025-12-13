package io.github.shadowrz.hanekokoro.framework.codegen

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

class HanekokoroFrameworkSymbolProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor =
        HanekokoroInjectSymbolProcessor(codeGenerator = environment.codeGenerator)
}
