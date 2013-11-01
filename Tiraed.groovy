/*
	Tiraed Bot 
    Chaque planète (mini 8 vaisseaux) va envoyer une flotte 
    de la moitié de ses unités sur une planète choisit selon 3 critères :
        - Force (priorité aux plus faible)
        - Type (priorité aux économiques)
        - Distance (priorité aux plus proche)
*/
class Tiraed extends Bot {
    
	def doTurn(data) {
        game.start_turn(data)
        game.planets.my_military().findAll{it.num_ships > 8}.each{ source ->
        	def target = getWeakestScoreWithDistancePlanet(source)
        	sendHalfShipInPlanet(source, target)
        };
        game.finish_turn()
    }

}