package app

import tornadofx.App
import tornadofx.UIComponent
import tornadofx.importStylesheet
import kotlin.reflect.KClass

class MainApp : App() {
    override val primaryView = MainScreen::class

    init {
        importStylesheet(Styles::class)
    }
}