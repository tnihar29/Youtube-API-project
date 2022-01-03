package com.practice.MajorProject;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class VideoService {
    private YouTube youTube;

    @Value("${google.api.key}")
    private String api_key;

    @Autowired
    VideoRepository videoRepository;
    @Autowired
    MongoOperations mongoOperations;

    public VideoService(){
        HttpTransport ht = new NetHttpTransport();
        JsonFactory js = new GsonFactory();
        this.youTube = new YouTube.Builder(ht, js, new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest httpRequest) throws IOException {

            }
        }).build();
    }
    public void fetchVideos(List<String> keywords){
        for(int i=0;i<keywords.size();i++){
            VideoThread thread = new VideoThread(keywords.get(i),this.api_key,this.youTube,this.videoRepository);
            thread.start();
        }
    }
    public void initDB(){
        mongoOperations.indexOps(Video.class).ensureIndex(new Index().on("videoId", Sort.Direction.ASC).unique().background());
    }

}
