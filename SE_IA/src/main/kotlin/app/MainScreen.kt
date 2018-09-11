package app

import inference.InferenceEngine
import inference.JustificationModule
import javafx.scene.Parent
import tornadofx.*
import utils.TextFileHandler
import java.util.ArrayList

class MainScreen : View("Sistema experto"){
    val fileHandler = TextFileHandler()

    val backgroundText = textarea {

    }

    val knowledgeText = textarea {
        runAsync {
            fileHandler.loadFile()
        }ui { loadedtext ->
            this.text = loadedtext
        }
    }

    val resultLabel = label {  }

    val justificationLabel = label {

    }

    override val root = vbox {
        add(text("Ingresar los géneros antecedentes"))

        add(backgroundText)
        vbox {
            button("Consulta") {
                action {
                    val list= ArrayList<String>(backgroundText.text.lines())
                    runAsync {
                        InferenceEngine.getInstance().init(list)
                        JustificationModule.getInstance().toString()
                    }ui { s: String -> justificationLabel.text = s}
                }
            }
            add(resultLabel)
            add(justificationLabel)
        }

        add(text("Actualizacion de la base de conocimiento [id (Número), antecedente(Texto), consecuentes(Texto) (separados por espacios)]"))

        add(knowledgeText)

        button("Cargar en la base de conocimiento") {
            action {
                fileHandler.saveText(knowledgeText.text)
            }
        }
    }
}