#!/usr/bin/env groovy

def excludes="**/*.swp, somefile.txt, somedir/**"

def ant = new AntBuilder()
ant.zip( destFile: "tiraed.zip" ) {
  fileset( dir: './' ) {
    include( name: "Bot.groovy" )
    include( name: "MyBot.groovy" )
    include( name: "Tiraed.groovy" )
    include( name: "Game.groovy" )
  }
}