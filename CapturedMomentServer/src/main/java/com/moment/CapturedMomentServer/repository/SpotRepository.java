package com.moment.CapturedMomentServer.repository;

import com.moment.CapturedMomentServer.domain.Spot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpotRepository extends JpaRepository<Spot, Long> {

    @Query(value="SELECT *, ( 6371 * acos (cos ( radians(?1) )* cos( radians( latitude ) )* cos( radians( longitude ) - radians(?2) ) + sin ( radians(?1) ) * sin( radians( latitude ) ))) AS distance FROM spot HAVING distance < 5000; ",
            nativeQuery=true)
    public List<Spot> datas(@Param("latitude") double source_lat,
                            @Param("longitude") double source_lon);

    @Query(value="SELECT * FROM spot", nativeQuery = true)
    public List<Spot> getAllData();

    boolean existsByLatitudeAndLongitude(Double latitude, Double longitude);

    @Query(value = "SELECT id FROM capturedmoment.spot WHERE latitude=?1 AND longitude=?2",
    nativeQuery = true)
    public Long spotIdGet(double source_lat, double source_lon);


}
