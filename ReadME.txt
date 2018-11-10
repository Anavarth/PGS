This project is about running an pokemon game simulation with the help of hadoop map reduce for fetching a large data of about 800 pokemon and retrive 5 best pokemon.
Then with those 5 pokemon, we also fetch 5 pokemon at random.
We then simulate the game between those sets.
Our objective here is to fetch the best 5 pokemon so that it has the high probability of winning.
We have select with the help of assignment algorithm considering the HP, Attack, Defence, Special Attack, Special Defence of each pokemon provided in the data set.
Pokemon.csv is the dataset.
CAT is the java code for running the hadoop map reduce.
It has to be executed as a hadoop code.
The code generates an file abc.txt with the vest 5 pokemon.
abc1.txt is user created file with 5 pokemon selected at random.
Simulation runs these two files to run the game.