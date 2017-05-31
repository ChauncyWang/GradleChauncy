import scala.io.Source

/**
  * Created by 13969 on 2017/5/26.
  */
object Main extends App {
	Source.fromFile("foc_hu/src/Main.scala").foreach((c:Char)=>print(c.hashCode()))
}
