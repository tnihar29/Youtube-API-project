package com.practice.MajorProject;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class VideoThread extends Thread{
    private Logger logger= LoggerFactory.getLogger(VideoThread.class);
    private String keyword;
    private String api_key;
    private YouTube youTube;
    VideoRepository videoRepository;
    private String pageToken;

    public VideoThread(String keyword, String api_key, YouTube youTube, VideoRepository videoRepository) {
        this.keyword = keyword;
        this.api_key = api_key;
        this.youTube = youTube;
        this.videoRepository = videoRepository;
    }

    @Override
    public void run() {
        try {
            while(true){
            YouTube.Search.List searchList = this.youTube.search().list(Arrays.asList("id", "snippet"));
            searchList.setKey(this.api_key);
            searchList.setQ(this.keyword);
            searchList.setMaxResults(5L);
            searchList.setPageToken(this.pageToken);

            SearchListResponse response = searchList.execute();
            List<SearchResult> items = response.getItems();
            List<Video> videos = new ArrayList<>();
            for (SearchResult item : items) {
                if((!item.getId().getKind().equals("youtube#video"))) continue;
                SearchResultSnippet snippet = item.getSnippet();
                String videoId = item.getId().getVideoId();
                String title = snippet.getTitle();
                String channelId = snippet.getChannelId();
                String channelTitle = snippet.getChannelTitle();
                String description = snippet.getDescription();
                JSONObject thumbnails = (JSONObject) JSONValue.parseWithException(snippet.getThumbnails().toString());
                Date publishedAt = new Date(snippet.getPublishedAt().getValue());
                Video video = new Video(channelId, channelTitle, description, thumbnails, publishedAt, title, videoId,this.keyword);
                videos.add(video);
            }
            videoRepository.save(videos);
            this.pageToken = response.getNextPageToken();
            logger.warn("saved video in the thread - {}",currentThread().getName()+" for "+this.keyword);
            Thread.sleep(10000);
        }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
