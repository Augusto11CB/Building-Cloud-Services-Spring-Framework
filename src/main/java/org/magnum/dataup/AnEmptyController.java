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
package org.magnum.dataup;

import org.magnum.dataup.model.Video;
import org.magnum.dataup.model.VideoStatus;
import org.magnum.dataup.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import retrofit.http.Part;
import retrofit.mime.TypedFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

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

    @Autowired
    VideoService videoService;

    @RequestMapping(method = RequestMethod.GET, value = "/video")
    public Collection<Video> getVideoList() {
        return videoService.getVideoList();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/video")
    public Video addVideo(@RequestBody Video v) {
        videoService.addVideo(v);
        return v;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/video/{id}/data"/*, consumes = MediaType.MULTIPART_FORM_DATA_VALUE*/)
    public VideoStatus setVideoData(@PathVariable("id") long id,/* @Part("data") *//*TypedFile typedFile*/ @RequestPart("data") MultipartFile videoData) throws IOException {
//        byte[] bytesArray = new byte[(int) typedFile.file().length()];
//        FileInputStream fis = new FileInputStream(typedFile.file());
//        fis.read(bytesArray); //read file into bytes[]
//        fis.close();
//        return videoService.saveVideoFile(id, bytesArray);
        return videoService.saveVideoFile(id, videoData.getBytes());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/video/{id}/data")
    public void getData(@PathVariable("id") long id, HttpServletResponse response) throws IOException {
        OutputStream outputStream = response.getOutputStream();

        videoService.getVideoFile(id, outputStream);
        response.setStatus(200);
        outputStream.flush();

    }
}
