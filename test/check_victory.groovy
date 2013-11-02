import groovy.json.JsonSlurper;

String fileContents = new File('visu/replay.js').text
def isFirst = true;
fileContents.eachLine{ line ->
	if (!isFirst) {
		def slurper = new JsonSlurper()
 		def result = slurper.parseText(line)
 		def game_length = result.game_length

 		def resultLine = "Player : \t";
 		result.playernames.each{player -> resultLine += "| $player \t"}
 		println(resultLine)

 		resultLine = "Result : \t"
 		result.rank.each{player -> resultLine += "| $player \t \t"}
 		println(resultLine)

 		resultLine = "Status : \t"
 		result.status.each{player -> resultLine += "| $player \t \t"}
 		println(resultLine)

 		if(result.rank[0] ==1) {
 			println "Vous avez gagné la partie en $game_length tour."
 		} else {
 			println "Vous avez perdu la partie en $game_length tour."
 		}

	}
	isFirst = false
}