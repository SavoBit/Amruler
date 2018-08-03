import sbt.ExclusionRule

name := "private_workspace"

version := "1.0"

scalaVersion := "2.12.4"


val testDependencies = Seq(
  "org.scalatest"  %% "scalatest"    % "3.0.3",
  "org.mockito"    %  "mockito-core" % "1.10.19" ,
  "org.scalacheck" %% "scalacheck"   % "1.13.5"
).map(_ % "test")


libraryDependencies ++= testDependencies