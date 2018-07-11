import sbt.AutoPlugin

object Common extends AutoPlugin {

  object autoImport {
    val CompileAndTest:String = "compile->compile;test->test"
  }

}
