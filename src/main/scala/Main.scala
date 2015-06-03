package com.oomatomo.scalaz.stream.test.scalazstreamtest

import scalaz.stream._
import scalaz.concurrent.Task

object Main {
  def main(args: Array[String]) {
    println("Hello World!!!")
    val converter: Task[Unit] =
      io.linesR("testdata/hoge.txt")
        .filter(s => !s.trim.isEmpty && !s.startsWith("//"))
        .map(line => s"ttt ${line}")
        .intersperse("\n")
        .pipe(text.utf8Encode)
        .to(io.fileChunkW("testdata/fuga.txt"))
        .run

    converter.run
  }
}

