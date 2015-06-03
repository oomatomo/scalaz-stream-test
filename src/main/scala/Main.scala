package com.oomatomo.scalaz.stream.test.scalazstreamtest

import scalaz.stream._
import scalaz.concurrent.Task

object Main {
  def main(args: Array[String]) {
    println("Hello World!!!")
    
    // シンプル
    val converter: Task[Unit] =
      io.linesR("testdata/hoge.txt")
        .filter(s => !s.trim.isEmpty && !s.startsWith("//"))
        .map(line => s"ttt ${line}")
        .intersperse("\n")
        .pipe(text.utf8Encode)
        .to(io.fileChunkW("testdata/fuga.txt"))
        .run

    converter.run
    
    //上書き
    val updateTask: Task[Unit] =
      io.linesR("testdata/updateBefore.txt")
        .filter(s => !s.trim.isEmpty)
        .intersperse("\n")
        .pipe(text.utf8Encode)
        .to(io.fileChunkW("testdata/update.txt"))
        .run
    updateTask.run
    
    val updateTask2: Task[Unit] =
      io.linesR("testdata/update.txt")
        .filter(s => !s.trim.isEmpty && !s.startsWith("//"))
        .intersperse("\n")
        .pipe(text.utf8Encode)
        .to(io.fileChunkW("testdata/update.txt"))
        .run
    updateTask2.run
    
    val updateTask3: Task[Unit] =
      io.linesR("testdata/update.txt")
        .filter(s => !s.trim.isEmpty && s.startsWith("lastUpdate"))
        .filter(s => s.split(":").length == 1)
        // 取得したい要素
        .take(1)
        .intersperse("\n")
        .pipe(text.utf8Encode)
        .to(io.fileChunkW("testdata/update.txt"))
        .run
    updateTask3.run

  }
}

