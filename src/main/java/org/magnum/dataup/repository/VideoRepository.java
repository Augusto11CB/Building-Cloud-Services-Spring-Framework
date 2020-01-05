package org.magnum.dataup.repository;

import org.magnum.dataup.model.Video;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class VideoRepository {

    private static final AtomicLong currentId = new AtomicLong(0L);

    private Map<Long, Video> videos = new HashMap<Long, Video>();

    public Map<Long, Video> getVideoList() {
        return videos;
    }

    public Video getVideoById(long id) {
        return videos.get(id);
    }

    public Video save(Video entity) {
        checkAndSetId(entity);
        entity.setDataUrl(this.getDataUrl(entity.getId()));
        videos.put(entity.getId(), entity);
        return entity;
    }

    private void checkAndSetId(Video entity) {
        if (entity.getId() == 0) {
            entity.setId(currentId.incrementAndGet());
        }
    }

    private String getDataUrl(long videoId) {
        String url = getUrlBaseForLocalServer() + "/video/" + videoId + "/data";
        return url;
    }

    private String getUrlBaseForLocalServer() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String base =
                "http://" + request.getServerName()
                        + ((request.getServerPort() != 80) ? ":" + request.getServerPort() : "");
        System.out.println("--------------------------- Video URL: " + base + "---------------------------");
        return base;
    }
}
