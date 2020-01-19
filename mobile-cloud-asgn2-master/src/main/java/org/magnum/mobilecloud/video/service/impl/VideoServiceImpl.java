package org.magnum.mobilecloud.video.service.impl;

import org.magnum.mobilecloud.video.exceptions.FileNotFoundException;
import org.magnum.mobilecloud.video.exceptions.LikingMoreThanOnceException;
import org.magnum.mobilecloud.video.repository.Video;
import org.magnum.mobilecloud.video.repository.VideoRepository;
import org.magnum.mobilecloud.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;

    //@Autowired
    //private UserVideoLikesRepository userVideoLikesRepository;

    @Override
    public Collection<Video> getVideoList() {

        List<Video> videoList = new ArrayList<>();
        videoRepository.findAll().forEach(videoList::add);

        return videoList;
    }

    @Override
    public Video addVideo(Video video) {

        assert (video != null);

        return videoRepository.save(video);
    }

    @Override
    public Video getVideoById(long idVideo) throws IOException {
        return videoRepository.findOne(idVideo);
    }

//    @Override
//    public VideoStatus saveVideoFile(long id, byte[] video) throws IOException, FileNotFoundException {
//
//        Video videoMetaData = videoRepository.findOne(id);
//
//        if (Objects.isNull(videoMetaData)) throw new FileNotFoundException("Video metadata not found");
//
//        videoFileManager.saveVideoData(videoMetaData, new ByteArrayInputStream(video));
//        return new VideoStatus(VideoStatus.VideoState.READY);
//    }

//    public Byte[] getVideoById(long id) throws IOException, FileNotFoundException {
//        Video videoMetaData = videoRepository.findOne(id);
//
//        if (Objects.isNull(videoMetaData)) throw new FileNotFoundException("Video metadata not found");
//
//        videoFileManager.copyVideoData(videoMetaData, new ByteArrayOutputStream());
//
//        return new Byte[0];
//    }

//    public void getVideoById(long id, OutputStream outputStream) throws IOException {
//        Video videoMetaData = videoRepository.getVideoById(id);
//
//        if (Objects.isNull(videoMetaData)) throw new FileNotFoundException("Video metadata not found");
//        videoFileManager.copyVideoData(videoMetaData, outputStream);
//
//    }

    @Override
    public Collection<Video> getVideoByTitle(String title) {
        return videoRepository.findByNameLike(title);
    }

    @Override
    public Collection<Video> getVideoByDurationLessThan(long duration) {
        return videoRepository.findByDurationLessThan(duration);
    }

    @Override
    public void likeVideo(long idVideo, Principal p) throws FileNotFoundException {
        Video video = videoRepository.findOne(idVideo);

        if (Objects.isNull(video)) throw new FileNotFoundException("Video metadata not found");

        if (video.getLikedBy().contains(p.getName())) {
            throw new LikingMoreThanOnceException("Video already liked by the user: " + p.getName());
        } else {
            videoRepository.save(addLike(video, p.getName()));
        }

    }

    private Video addLike(Video video, String name) {
        video.setLikes(video.getLikes() + 1);
        video.getLikedBy().add(name);
        return video;
    }

    @Override
    public void unLikeVideo(long idVideo, Principal p) throws FileNotFoundException {
        Video video = videoRepository.findOne(idVideo);

        if (Objects.isNull(video)) throw new FileNotFoundException("Video metadata not found");

        if (video.getLikedBy().contains(p.getName())) {
            videoRepository.save(removeLikeVideo(video,p.getName()));
        } else {
            videoRepository.save(addLike(video, p.getName()));
        }
    }

    private Video removeLikeVideo(Video video, String name) {
        video.setLikes(video.getLikes() - 1);
        video.getLikedBy().remove(name);

        return video;
    }
}
