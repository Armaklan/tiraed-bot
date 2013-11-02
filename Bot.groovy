/*
 Created on 31 juil. 2013
     The do_turn function is where your code goes. The Game object
     contains the state of the game, including information about all planets
     and fleets that currently exist. Inside this function, you issue orders
     using the Game.issueOrder function. For example, to send 10 ships from
     planet 3 to planet 8, you would say Game.issueOrder(3, 8, 10).
     
     There is already a basic strategy in place here. You can use it as a
     starting point, or you can throw it out entirely and replace it with
     your own.
 @author: Zuberl
*/

class Bot {

    def game

    def initGame(game_data) {
        game = game_data
    }

    def doTurn(data) {
        game.start_turn(data)
        game.finish_turn()
    }

    def getMyStrongestPlanet() {
        return game.planets.my_military().max({ a,b ->
            a.num_ships <=> b.num_ships
        })
    } 

    def getMyWeakestPlanet() {
        return game.planets.my_all().min({ a,b ->
            a.num_ships <=> b.num_ships
        })
    }

    def getOpposantWeakestPlanet() {
        return game.planets.others_all().min({ a,b ->
            a.num_ships <=> b.num_ships
        })
    }

    def getOthersWeakestScorePlanet() {
        return game.planets.others_all().min({ a,b ->
            a.num_ships * a.poids <=> b.num_ships * b.poids
        })
    }

    def getWeakestScorePlanet() {
        return game.planets.all().min({ a,b ->
            a.num_ships * a.poids <=> b.num_ships * b.poids
        })
    }

    def getDistanceScore(source, destination) {
        game.distance(source, destination) * 0.8
    }

    def getWeakestScoreWithDistancePlanet(source) {
        return game.planets.all().sort({ a,b ->
            (a.num_ships + getDistanceScore(source, a) * 1.3 )* a.poids <=> (b.num_ships + getDistanceScore(source, b) * 1.3) * b.poids
        })
    }

    /*
    Compte les flottes en cours de trajet
    */
    def getWeakestVirtualScorePlanet(source) {
        return game.planets.all().sort({ a,b ->
            game.fleets.to_id(a.id).each{ fleet ->
                if(fleet.owner == a.owner) a.num_ships += fleet.num_ships
                else a.num_ships -= fleet.num_ships
            }

            game.fleets.to_id(b.id).each{ fleet ->
                if(fleet.owner == b.owner) b.num_ships += fleet.num_ships
                else b.num_ships -= fleet.num_ships
            }

            (a.num_ships + getDistanceScore(source, a) * 2) * a.poids <=> (b.num_ships + getDistanceScore(source, b) * 2) * b.poids
        })
    }

    def sendHalfShipInPlanet(source, dest) {
        if ((source != null) && (dest != null)) {
            def num_ships = (source.num_ships / 2).toInteger()
            game.issue_order(source.id, dest.id, num_ships)
        }
    }

    def sendShipInPlanet(source, dest, ships) {
        if ((source != null) && (dest != null)) {
            game.issue_order(source.id, dest.id, ships)
        }
    }

}
