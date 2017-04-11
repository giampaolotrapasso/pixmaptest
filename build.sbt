val libgdxVersion = "1.9.6"

scalaVersion := "2.11.7"


libraryDependencies ++= Seq(
  "com.badlogicgames.gdx" % "gdx-backend-lwjgl" % libgdxVersion,
  "com.badlogicgames.gdx" % "gdx-backend-headless" % libgdxVersion,
  "com.badlogicgames.gdx" % "gdx" % libgdxVersion,
  "com.badlogicgames.gdx" % "gdx-platform" % libgdxVersion classifier "natives-desktop"
)


lazy val root = (project in file("."))
  .settings(
    javaOptions += "-Xmx3g"
  )






