package android.gajju45.AdvanceNews

object ColorPicker{
    val colors = arrayOf("#ffcbcb","#70adb5","#ffcb8e","#28df99")
    var colorIndex =  1
    fun getColor():String{
        return colors[colorIndex++ % colors.size]
    }
}
