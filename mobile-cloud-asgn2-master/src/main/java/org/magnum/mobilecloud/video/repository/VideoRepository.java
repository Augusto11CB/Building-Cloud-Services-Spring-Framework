package org.magnum.mobilecloud.video.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface VideoRepository extends CrudRepository<Video, Long>{

    Collection<Video> findByDurationLessThan(long duration);

    Collection<Video> findByNameLike(String title);
}