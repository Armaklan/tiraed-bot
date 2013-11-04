import spock.lang.*

class PlanetTest extends spock.lang.Specification {
    def "Planet parsing"() {
    	given: "Initialize game"
    	def game = new Game("")

        expect: "Type is correctly parse"
        Planet.parse(tokens, game.planets).isMilitary() == military
        Planet.parse(tokens, game.planets).isEconomic() == economic

        and: "Coordonate is parse"
        Planet.parse(tokens, game.planets).x == x
        Planet.parse(tokens, game.planets).y == y

        where:
        tokens					| military 	| economic 	| x		| y
        ['M', 5, 8, 1, 12]		| true 		| false		| 5		| 8
        ['E', 10, 3, 1, 5, 6]	| false		| true		| 10	| 3
    }

    def "Calcul distance between to planet"() {
    	given: "Initialize game"
    	def game = new Game("")
    	def first = Planet.parse(firstTokens, game.planets)
    	def second = Planet.parse(secondTokens, game.planets)

        expect: "Check distance correctly calcul"
        Game.distance(first, second) == result

        where:
        firstTokens											| secondTokens 												| result
        ['E',14.607868701123053,16.142563056352653,0,0,2]	| ['E',19.60609476210437,13.972250406328175,0,18,2] 		| 6.0
        ['E',14.607868701123053,16.142563056352653,0,0,2]	| ['M',0.5495124796968334,10.88556823190577,3,14]	 		| 16.0
        ['E',9.904971573452196,4.426803509261771,0,58,2]	| ['E',19.60609476210437,13.972250406328175,0,18,2] 		| 14.0
    }

    def "Calcul score between to planet"() {
    	given: "Initialize game"
    	def game = new Game("")
    	def first = Planet.parse(firstTokens, game.planets)
    	def second = Planet.parse(secondTokens, game.planets)

        expect: "Check distance correctly calcul"
        first.getScoreTo(second) == result

        where:
        firstTokens											| secondTokens 												| result
        ['E',14.607868701123053,16.142563056352653,0,0,2]	| ['E',19.60609476210437,13.972250406328175,0,18,2] 		| 36
        ['E',14.607868701123053,16.142563056352653,0,0,2]	| ['M',0.5495124796968334,10.88556823190577,3,14]	 		| 46
        ['E',9.904971573452196,4.426803509261771,0,58,2]	| ['E',19.60609476210437,13.972250406328175,0,18,2] 		| 56
    }

    def "Sort by score"() {
    	given: "Initialize game"
    	def game = new Game("")
    	def bot = new Bot()
    	def p1 = Planet.parse(p1Tokens, game.planets)
    	game.planets.add(p1)
    	def p2 = Planet.parse(p2Tokens, game.planets) 
    	game.planets.add(p2)
    	def p3 = Planet.parse(p3Tokens, game.planets) 
    	game.planets.add(p3)
    	def p4 = Planet.parse(p4Tokens, game.planets) 
    	game.planets.add(p4)
    	bot.initGame(game)
    	def sortedPlanets = bot.getWeakestScoreWithDistancePlanet(p1)

    	expect:"Correctly sort by weakest"
    	sortedPlanets[0] == p3
    	sortedPlanets[1] == p2
    	sortedPlanets[2] == p4

    	where:
    	p1Tokens 											| p3Tokens											| p2Tokens											| p4Tokens
    	['E',14.607868701123053,16.142563056352653,0,0,2]   | ['E',19.60609476210437,13.972250406328175,2,18,2]	| ['M',0.5495124796968334,10.88556823190577,3,14]	| ['E',9.904971573452196,4.426803509261771,2,58,2] 

    }

}
