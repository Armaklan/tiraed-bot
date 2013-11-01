/*
	Basic example Bot.
	This bot :
		- Get your strongest military planet (Source)
		- Get the weakest other planet (Destination)
		- Send of source fleet to destination, only if fleet have ten ship
*/

class SaveBot extends Bot {

	def doTurn(data) {
        game.start_turn(data)
       
        game.planets.my_military().findAll{it.num_ships > 8}.each{ source ->
        	def target = getWeakestScoreWithDistancePlanet(source)
        	sendHalfShipInPlanet(source, target)
        };
        game.finish_turn()
    }

}