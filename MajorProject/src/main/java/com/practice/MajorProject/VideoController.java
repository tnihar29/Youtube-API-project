package com.practice.MajorProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VideoController {

    @Autowired
    VideoRepository videoRepository;
    @GetMapping("/getVideos")
    public List<Video> getV(@RequestParam("query") String query,
                     @RequestParam(value = "pageNo",required = false,defaultValue = "1")int pageNo,
                     @RequestParam(value = "pageSize",required = false,defaultValue = "10")int pageSize){
        return videoRepository.getVideos(query,pageNo,pageSize);
    }
}
