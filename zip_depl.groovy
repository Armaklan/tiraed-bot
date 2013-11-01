#!/usr/bin/env groovy
def FILENAME = "tiraed.zip"

try {
  new File(FILENAME).delete()  
} catch (Exception e) {
  println "Pas d archive a supprimer"
}

def ant = new AntBuilder()
ant.zip( destFile: FILENAME ) {
  fileset( dir: './' ) {
    include( name: "Bot.groovy" )
    include( name: "MyBot.groovy" )
    include( name: "Tiraed.groovy" )
    include( name: "Game.groovy" )
  }
}