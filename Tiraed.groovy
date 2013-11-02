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
        game.planets.my_military().findAll{it.num_ships > 8}.each{ source ->
           
            def targets = getWeakestVirtualScorePlanet(source)

            def source_ship = source.num_ships
            targets.each{ target -> 
                if(source_ship > 8) {
                    if (target.id != source.id) {
                        def sendShip = [(source_ship / 2).toInteger(), 20].min()
                        orders.add(new Order(
                            source: source,
                            target: target,
                            ships: sendShip
                        ));
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