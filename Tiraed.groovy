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
        def orders = prepareOrders(game);
        sendOrders(orders);
        game.finish_turn()
    }

    def prepareOrders(game) {
        def orders = []
        game.planets.military.my().findAll{it.num_ships > 10}.each{ source ->
           
            def targets = getWeakestVirtualScorePlanet(source)

            def source_ship = source.num_ships
            targets.each{ target -> 
                if(source_ship > 6) {
                    if (target.id != source.id) {
                        def sendShip = [(source_ship / 1.5).toInteger(), target.num_ships + 10].min()
                        if(sendShip < 0 ) {
                            sendShip = 5
                        }
                        if ( ( target.isEnnemy() || target.num_ships < 40)){

                            orders.add(new Order(
                                source: source,
                                target: target,
                                ships: sendShip
                            ))
                            game.fleets.add(
                                new Fleet(
                                    owner: Constant.MY_ID,
                                    num_ships: sendShip,
                                    sourcePlanet: source.id,
                                    destinationPlanet: target.id,
                                    totalTripLength: 20,
                                    turnsRemaining: 0,
                                    military: true
                                )
                            )
                        }
                        source_ship -= sendShip
                    }
                }
            }
           
        };
        return orders;
    }

    def sendOrders(orders) {
        orders.each{ order -> sendShipInPlanet(order.source, order.target, order.ships) };
    }

}