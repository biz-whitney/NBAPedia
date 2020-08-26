package Biz.NBAPedia.service;

import Biz.NBAPedia.entity.Players;
import Biz.NBAPedia.repository.PlayerGameStatsRepository.CareerStats;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.clustering.*;


@Service
public class RecommendationService {
  @Autowired
  PlayerGameStatsService playerGameStatsService;
  @Autowired
  PlayersService playersService;
  @Autowired
  JavaSparkContext sc;

  private static final String TRAINING_DATA_PATH  = System.getProperty("user.dir") + "/src/main"
      + "/resources/KMeansModelResources/trainingData.csv";
  private static final String MODEL_PATH  = System.getProperty("user.dir") + "/src/main"
      + "/resources/KMeansModelResources/Model";
  private Map<Integer, List<Integer>> clusterMap = null;
  private KMeansModel model = null;

  private void getClusterMap() {
    JavaRDD<String> data = sc.textFile(TRAINING_DATA_PATH);
    JavaRDD<Vector> parsedData = data.map(s -> {
      String[] sarray = s.split(",");
      double[] values = new double[sarray.length];
      for (int i = 0; i < sarray.length; i++) {
        values[i] = Double.parseDouble(sarray[i]);
      }
      return Vectors.dense(values);
    });
    parsedData.cache();


//    // Cluster the data into two classes using KMeans
//    int numClusters = 1000;
//    int numIterations = 20;
//    KMeansModel clusters = KMeans.train(parsedData.rdd(), numClusters,
//        numIterations);
//
//    clusters.save(sc.sc(), MODEL_PATH);


    model = KMeansModel.load(sc.sc(), MODEL_PATH);

    List<Vector> vectorList = parsedData.collect();
    clusterMap = new HashMap<>();
    for (Vector vector: vectorList) {
      int playerId = (int) vector.toArray()[0];
      int clusterGroup = model.predict(vector);
      if (clusterMap.containsKey(clusterGroup)) {
        clusterMap.get(clusterGroup).add(playerId);
      } else {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(playerId);
        clusterMap.put(clusterGroup, arrayList);
      }
    }
  }

  public List<Players> getSimilarPlayers(int playerId) {
    if (clusterMap == null) {
      getClusterMap();
    }
    CareerStats stats = playerGameStatsService.findPlayerCareerStats(playerId);
    double[] array = new double[]{stats.getId(), stats.getFG(), stats.getFGP(), stats.getFG3(),
        stats.getFG3P(), stats.getFT(), stats.getFTP(), stats.getAST(), stats.getSTL(),
        stats.getTRB(), stats.getBLK(), stats.getPTS()};

    Vector vector = Vectors.dense(array);
    int clusterId = model.predict(vector);
    List<Integer> playerIdList = clusterMap.get(clusterId);
    System.out.println(playerIdList.size());
    List<Players> playersList = new ArrayList<>();
    for (int id: playerIdList) {
      Players player = playersService.findPlayerById(id);
      playersList.add(player);
    }
    return playersList;
  }
}
