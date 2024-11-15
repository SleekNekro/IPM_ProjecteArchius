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
    // Configure template resolver
    val templateResolver = ClassLoaderTemplateResolver().apply {
        prefix = "templates/"
        suffix = ".html"
    }

    // Configure template engine
    val templateEngine = TemplateEngine().apply {
        setTemplateResolver(templateResolver)
    }

    // Load data
    val data = loadTypesFromJson("src/main/resources/Json/JsonDep.json")

    // Generate main index HTML
    println("Types: ${data.types}")  // Debugging line
    val context = Context().apply {
        if (data.types.isNotEmpty()) {
            setVariable("types", data.types)
        } else {
            println("No types found in data!")
            setVariable("types", emptyList<Type>())  // Use an empty list if no types are found
        }
    }


    val writer = StringWriter()
    templateEngine.process("templateINDEX.html", context, writer)
    writeHTML("src/main/resources/html/index.html", writer.toString())

    // Generate individual type HTML pages
    for (tipo in data.types) {
        println(tipo.name)

        val contextType = Context().apply {
            setVariable("tipo", tipo)
            val matchingPokemon = data.pokemon.filter { it.type == tipo.name }
            setVariable("pokemon", matchingPokemon)
        }

        val tipoHTML: String = templateEngine.process("templatePKMN", contextType)
        val fileNameTipo = "src/main/resources/html/Tipos/tipo_${tipo.name}.html"

        writeHTML(fileNameTipo, tipoHTML)
        println("Generated page for type: ${tipo.name}")
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
    val mapper = ObjectMapper()
    val data = mapper.readValue(File(filePath), Data::class.java)
    println("Loaded data: $data")  // This will print the deserialized object
    return data
}


