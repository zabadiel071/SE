package utils

import java.io.File
import java.io.FileWriter

class TextFileHandler(var path:String = "RULES.txt"){
    var file = File(path)

    fun getTitle() : String{
        return file.name
    }

    fun loadFile() : String{
        return file.readText()
    }

    fun saveText(s:String) : Boolean{
        var fwriter = FileWriter(this.file,false)
        fwriter.write(s)
        fwriter.close()
        return true
    }
}
