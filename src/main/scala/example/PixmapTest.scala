package example

import java.awt.Point
import java.awt.image.{BufferedImage, DataBufferByte, Raster}
import java.io.File
import java.nio.ByteBuffer
import javax.imageio.ImageIO

import com.badlogic.gdx.backends.headless._
import com.badlogic.gdx.graphics.Pixmap.{Blending, Format}
import com.badlogic.gdx.graphics.{Color, Pixmap}

import scala.util.Random

object PixmapTest extends App {

  val colors = List(
    Color.BLUE,
    Color.RED,
    Color.ROYAL,
    Color.GREEN,
    Color.YELLOW).map(c => c.sub(0.0f, 0.0f, 0.0f, 0.5f))

  def randomPixmap(width: Int, height: Int): Pixmap = {
    val pixmap = new Pixmap(width, height, Format.RGB888)
    pixmap.setColor(Color.BLACK)
    pixmap.fill()

    pixmap.setBlending(Blending.SourceOver)

    Range(1, 10).foreach { i =>
      val c = colors(Random.nextInt(colors.length))
      pixmap.setColor(c)
      val x = Random.nextInt(width)
      val y = Random.nextInt(height)
      val r = Random.nextInt(256)

      pixmap.fillCircle(x, y, r)
    }
    pixmap
  }


  val config = new HeadlessApplicationConfiguration();
  val app = new HeadlessApplication(new ServerApp, config);

  val p = randomPixmap(512, 512)

  val l: ByteBuffer = p.getPixels
  val dst = Array.ofDim[Byte](p.getWidth * p.getHeight * 3)
  l.get(dst)

  val img = new BufferedImage(512, 512, BufferedImage.TYPE_3BYTE_BGR)
  img.setData(Raster.createRaster(img.getSampleModel, new DataBufferByte(dst, dst.length), new Point))

  ImageIO.write(img, "png", new File("newimage.png"))
  app.exit()


}




