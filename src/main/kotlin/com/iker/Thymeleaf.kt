package com.iker

import com.fasterxml.jackson.databind.ObjectMapper
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import com.fasterxml.jackson.module.kotlin.readValue

class Thymeleaf {
    fun main(){
        //Configurar resolver
        val templateResolver = ClassLoaderTemplateResolver()
            .apply {
                prefix = "templates"
                suffix = ".html"
            }
        //Configurar motor plantillas

        val templateEngine = TemplateEngine()
            .apply {
                setTemplateResolver(templateResolver)
            }

        //Contexto
        val tipos = loadTypesFromJson("src/main/resources/Json/JsonDep.json")
        val context = Context()
            .apply {
            setVariable("types",tipos)
        }
        val contenidoHTML = templateEngine.process("templateINDEX",context)
        writeHTML("/src/main/resources/html/index.html",contenidoHTML)

        for (tipo in Type){

        }
    }

    private fun writeHTML(fileName:String, content: String) {
        try {
            BufferedWriter(FileWriter(fileName)).use { writer ->
                // Write the HTML content to the file
                writer.write(content)
            }
        } catch (e: IOException) {
            // Print the stack trace if an error occurs
            e.printStackTrace()
        }
    }

    fun loadTypesFromJson(filePath: String): List<Type> {
        val mapper = ObjectMapper()
        val data: Data = mapper.readValue(File(filePath))
        return data.types
    }


}