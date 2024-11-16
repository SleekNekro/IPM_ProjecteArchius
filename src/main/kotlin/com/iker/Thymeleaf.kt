package com.iker

import com.fasterxml.jackson.databind.ObjectMapper
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.io.StringWriter
import com.fasterxml.jackson.module.kotlin.readValue
import com.iker.Type as Type

fun main() {
    // Configurar el template engine
    val templateResolver = ClassLoaderTemplateResolver().apply {
        prefix = "templates/"
        suffix = ".html"
    }

    val templateEngine = TemplateEngine().apply {
        setTemplateResolver(templateResolver)
    }

    // Cargar datos del JSON
    val data = loadTypesFromJson("src/main/resources/Json/JsonDep.json")

    // Generar la página principal (index.html)
    val indexContext = Context().apply {
        setVariable("types", data.types)
    }

    val indexWriter = StringWriter()
    templateEngine.process("templateINDEX.html", indexContext, indexWriter)
    writeHTML("src/main/resources/html/index.html", indexWriter.toString())
    println("Página principal generada: src/main/resources/html/index.html")

    // Generar páginas individuales para cada tipo
    for (tipo in data.types) {
        println("Generando página para tipo: ${tipo.name}")

        // Filtrar los Pokémon que corresponden al tipo actual
        val matchingPokemon = data.pokemon.filter { it.type.equals(tipo.name, ignoreCase = true) }

        // Crear contexto para el tipo
        val typeContext = Context().apply {
            setVariable("tipo", tipo)
            setVariable("pokemon", matchingPokemon)
        }

        // Generar HTML para la página del tipo
        val typeHTML = templateEngine.process("templatePKMN.html", typeContext)
        val fileNameTipo = "src/main/resources/html/Tipos/tipo_${tipo.name}.html"

        // Escribir el archivo
        writeHTML(fileNameTipo, typeHTML)
        println("Página generada para tipo ${tipo.name}: $fileNameTipo")
    }
}


private fun writeHTML(fileName: String, content: String) {
    try {
        BufferedWriter(FileWriter(fileName)).use { writer ->
            writer.write(content)
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

fun loadTypesFromJson(filePath: String): Data {
    val mapper = ObjectMapper().apply {
        findAndRegisterModules()
    }
    return mapper.readValue(File(filePath),Data::class.java)
}


