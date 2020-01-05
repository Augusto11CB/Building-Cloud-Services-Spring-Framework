package org.magnum.dataup.service.impl;

import org.magnum.dataup.VideoFileManager;
import org.magnum.dataup.exceptions.FileNotFoundException;
import org.magnum.dataup.model.Video;
import org.magnum.dataup.model.VideoStatus;
import org.magnum.dataup.repository.VideoRepository;
import org.magnum.dataup.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Objects;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private VideoFileManager videoFileManager;


    @Override
    public Collection<Video> getVideoList() {
        return videoRepository.getVideoList().values();
    }

    @Override
    public Video addVideo(Video video) {

        assert (video != null);

        videoRepository.save(video);

        return video;
    }

    @Override
    public VideoStatus saveVideoFile(long id, byte[] video) throws IOException, FileNotFoundException {

        Video videoMetaData = videoRepository.getVideoById(id);

        if (Objects.isNull(videoMetaData)) throw new FileNotFoundException("Video metadata not found");

        videoFileManager.saveVideoData(videoMetaData, new ByteArrayInputStream(video));
        return new VideoStatus(VideoStatus.VideoState.READY);
    }

    @Override
    public Byte[] getVideoFile(long id) throws IOException, FileNotFoundException {
        Video videoMetaData = videoRepository.getVideoById(id);

        if (Objects.isNull(videoMetaData)) throw new FileNotFoundException("Video metadata not found");

        videoFileManager.copyVideoData(videoMetaData, new ByteArrayOutputStream());

        return new Byte[0];
    }

    @Override
    public void getVideoFile(long id, OutputStream outputStream) throws IOException {
        Video videoMetaData = videoRepository.getVideoById(id);

        if (Objects.isNull(videoMetaData)) throw new FileNotFoundException("Video metadata not found");
        videoFileManager.copyVideoData(videoMetaData, outputStream);

    }
}
