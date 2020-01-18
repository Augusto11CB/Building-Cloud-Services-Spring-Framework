/*
 *
 * Copyright 2014 Jules White
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.magnum.mobilecloud.video;

import org.magnum.mobilecloud.video.repository.Video;
import org.magnum.mobilecloud.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Principal;
import java.util.Collection;
import java.util.Collections;

@RestController
public class AnEmptyController {

    /**
     * You will need to create one or more Spring controllers to fulfill the
     * requirements of the assignment. If you use this file, please rename it
     * to something other than "AnEmptyController"
     * <p>
     * <p>
     * ________  ________  ________  ________          ___       ___  ___  ________  ___  __
     * |\   ____\|\   __  \|\   __  \|\   ___ \        |\  \     |\  \|\  \|\   ____\|\  \|\  \
     * \ \  \___|\ \  \|\  \ \  \|\  \ \  \_|\ \       \ \  \    \ \  \\\  \ \  \___|\ \  \/  /|_
     * \ \  \  __\ \  \\\  \ \  \\\  \ \  \ \\ \       \ \  \    \ \  \\\  \ \  \    \ \   ___  \
     * \ \  \|\  \ \  \\\  \ \  \\\  \ \  \_\\ \       \ \  \____\ \  \\\  \ \  \____\ \  \\ \  \
     * \ \_______\ \_______\ \_______\ \_______\       \ \_______\ \_______\ \_______\ \__\\ \__\
     * \|_______|\|_______|\|_______|\|_______|        \|_______|\|_______|\|_______|\|__| \|__|
     */


    public static final String TITLE_PARAMETER = "title";

    public static final String DURATION_PARAMETER = "duration";

    public static final String TOKEN_PATH = "/oauth/token";

    public static final String VIDEO_SVC_PATH = "/video";

    public static final String VIDEO_TITLE_SEARCH_PATH = VIDEO_SVC_PATH + "/search/findByName";

    public static final String VIDEO_DURATION_SEARCH_PATH = VIDEO_SVC_PATH + "/search/findByDurationLessThan";


    @Autowired
    private VideoService videoService;

    @RequestMapping(method = RequestMethod.GET, value = VIDEO_SVC_PATH)
    public Collection<Video> getVideoList() {
        return videoService.getVideoList();
        //return Collections.emptyList();
    }

    @RequestMapping(method = RequestMethod.GET, value = VIDEO_SVC_PATH + "/{id}")
    public Video getVideoById(@PathVariable("id") long id, HttpServletResponse response) throws IOException {
        return videoService.getVideoById(id);

    }

    @RequestMapping(method = RequestMethod.POST, value = VIDEO_SVC_PATH)
    public Video addVideo(@RequestBody Video v) {
        return videoService.addVideo(v);
    }

    @RequestMapping(method = RequestMethod.POST, value = VIDEO_SVC_PATH + "/{id}/like")
    public void likeVideo(@PathVariable("id") long id, Principal p) throws FileNotFoundException {
        videoService.likeVideo(id, p);
        String username = p.getName();
        // Maybe you want to add this users name to
        // the list of people who like a video
    }


    @RequestMapping(method = RequestMethod.POST, value = VIDEO_SVC_PATH + "/{id}/unlike")
    public void unlikeVideo(@PathVariable("id") long id, Principal p) throws FileNotFoundException {
        videoService.unLikeVideo(id, p);
    }

    @RequestMapping(method = RequestMethod.GET, value = VIDEO_TITLE_SEARCH_PATH)
    public Collection<Video> findByTitle(@RequestParam(TITLE_PARAMETER) String title) {
        return videoService.getVideoByTitle(title);
    }

    @RequestMapping(method = RequestMethod.GET, value = VIDEO_DURATION_SEARCH_PATH)
    public Collection<Video> findByDurationLessThan(@RequestParam(DURATION_PARAMETER) long duration) {
        return videoService.getVideoByDurationLessThan(duration);
    }


    @RequestMapping(value = "/go", method = RequestMethod.GET)
    public @ResponseBody
    String goodLuck() {
        return "Good Luck!";
    }

}
