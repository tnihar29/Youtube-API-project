package com.practice.MajorProject;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MajorProjectApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MajorProjectApplication.class, args);
	}
	public List<String> keywords = Arrays.asList("cricket","football","bollywood","cooking","fashion","technology");
	@Autowired
	VideoService videoService;
	@Override
	public void run(String... args) throws Exception, ParseException {
//		videoService.initDB();
//		videoService.fetchVideos(keywords);
	}
}
