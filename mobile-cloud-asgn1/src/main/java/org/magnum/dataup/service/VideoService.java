package org.magnum.dataup.service;

import org.magnum.dataup.model.Video;
import org.magnum.dataup.model.VideoStatus;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

public interface VideoService {

    Collection<Video> getVideoList();

    Video addVideo(Video video);

    VideoStatus saveVideoFile(long id, byte[] video) throws IOException;

    Byte[] getVideoFile(long id) throws IOException;

    void getVideoFile(long id, OutputStream outputStream) throws IOException;
}
