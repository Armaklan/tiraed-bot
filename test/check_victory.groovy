import groovy.json.JsonSlurper;

String fileContents = new File('visu/replay.js').text
def isFirst = true;
fileContents.eachLine{ line ->
	if (!isFirst) {
		def slurper = new JsonSlurper()
 		def result = slurper.parseText(line)
 		def status = result.get('status')
 		def game_length = result.get('game_length')
 		def ranks = result.get('rank')

 		if(ranks[0] == '1') {
 			println "Vous avez gagné la partie en $game_length tour."
 		} else {
 			println "Vous avez perdu la partie en $game_length tour."
 		}

 		println("Ranks final : $ranks ")
 		println("Status final : $ranks ")


	}
	isFirst = false
}