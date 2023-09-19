package com.itoxi.petnuri.domain.point;

import com.itoxi.petnuri.domain.point.service.PointService;
import com.itoxi.petnuri.global.common.customValid.valid.ValidId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author         : matrix
 * date           : 2023-09-19
 * description    :
 */
@RequestMapping("/point")
@RestController
@RequiredArgsConstructor
public class PointColtroller {

    private final PointService pointService;

    @GetMapping("get/daily/{challengeId}/{userId}")
    public ResponseEntity getPoint(@PathVariable @ValidId Long challengeId, @PathVariable @ValidId Long userId) {
        Long memberPoint = pointService.getPoint(challengeId, userId);
        return new ResponseEntity<>(memberPoint, HttpStatus.CREATED);
    }
}