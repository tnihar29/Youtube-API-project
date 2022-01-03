package com.practice.MajorProject;

import com.mongodb.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VideoRepository {

    @Autowired
    MongoOperations mongoOperations;

    public void saveOne(Video video) throws Exception {
        try{
        mongoOperations.save(video);}
        catch(DuplicateKeyException e){e.printStackTrace();}
        catch (Exception e){throw new Exception("An error Occurred");}
    }
    public void save(List<Video> videos) throws Exception {
        try {
            mongoOperations.insertAll(videos);
        }catch (DuplicateKeyException e){
            for(Video video:videos)
                saveOne(video);
        }
    }
    public List<Video> getVideos(String keyword,int pageNo,int pageSize){
        Criteria criteria = Criteria.where("tag").is(keyword);
        Query query = new Query();
        query.addCriteria(criteria).skip((pageNo-1)*pageSize).limit(pageSize);
        return mongoOperations.find(query,Video.class);
    }
}
