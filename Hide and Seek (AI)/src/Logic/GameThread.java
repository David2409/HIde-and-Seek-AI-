package Logic;

import Network.Network;

public class GameThread extends Thread{
	private Game game;
	private Network network;
	private int trys;
	
	public GameThread(Game iGame, Network iNetwork, int iMaxTrys){
		network = iNetwork;
		game = iGame;
		trys = iMaxTrys;
	}
	
	@Override
	public void run() {
		network.fitness = 0;
		for(long thistry = trys; thistry > 0; thistry--){
			network.Simulate(game.GetInput());
			if(game.Move(GetHighest(Floor(network.GetLastLayer())))){
				network.fitness += thistry * game.GetCockie();
				break;
			}
			network.fitness += thistry * game.GetCockie();
		}
		/*System.out.println(network.fitness);
		System.out.println("-------------------------------");*/
	}
	
	private static int GetHighest(float[] array){
		int highest = 0;
		for (int i = 1; i < array.length; i++) {
			if(array[i] > array[highest]){
				highest = i;
			}
		}
		return highest;
	}
	
	private static float[] Floor(float[] input){
		for (int i = 0; i < input.length; i++) {
			input[i] = (float) Math.floor(input[i]);
		}
		return input;
	}
}