def FILENAME = "run.conf"

def maps = [
'maps\\4\\map67.txt'
,'maps\\4\\map70.txt'
,'maps\\4\\map75.txt'
,'maps\\4\\map84.txt'
,'maps\\4\\map85.txt'
,'maps\\4\\map69.txt'
,'maps\\3\\map35.txt'
,'maps\\3\\map49.txt'
,'maps\\3\\map52.txt'
,'maps\\3\\map42.txt'
,'maps\\3\\map58.txt'
,'maps\\3\\map66.txt'
,'maps\\3\\map60.txt'
,'maps\\3\\map53.txt'
,'maps\\2\\map11.txt'
,'maps\\2\\map18.txt'
,'maps\\2\\map26.txt'
,'maps\\2\\map30.txt'
,'maps\\2\\map32.txt'
,'maps\\2\\map9.txt'
,'maps\\2\\map5.txt'
]

def mapIndex = new Random().nextInt(maps.size())

def map = 'map=' + maps[mapIndex]
def bot_cmd='bot_cmd=groovy -cp ../ ../MyBot.groovy -v'

try {
  new File(FILENAME).delete()  
} catch (Exception e) {
  
}
new File("run.conf").withWriter { out ->
	out.println map
	out.println bot_cmd
}