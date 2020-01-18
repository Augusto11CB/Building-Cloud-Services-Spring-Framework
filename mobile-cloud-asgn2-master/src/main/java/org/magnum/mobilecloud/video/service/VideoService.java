package org.magnum.mobilecloud.video.service;


import org.magnum.mobilecloud.video.repository.Video;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Principal;
import java.util.Collection;

public interface VideoService {

    Collection<Video> getVideoList();

    Video addVideo(Video video);

    Video getVideoById(long id) throws IOException;

//    Byte[] getVideoById(long id) throws IOException;

//    void getVideoById(long id, OutputStream outputStream) throws IOException;

    Collection<Video> getVideoByTitle(String title);

    Collection<Video> getVideoByDurationLessThan(long duration);

    void likeVideo(long idVideo, Principal p) throws FileNotFoundException;

    void unLikeVideo(long idVideo, Principal p) throws FileNotFoundException;
}
